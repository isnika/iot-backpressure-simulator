package node;

import com.sun.net.httpserver.HttpServer;
import model.SensorData;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NodeServer {

    private final int port;

    private final BlockingQueue<SensorData> queue = new ArrayBlockingQueue<>(5);
    private final Metrics metrics = new Metrics();
    private final CsvStorage storage = new CsvStorage();

    public NodeServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {

        // ================= WORKER =================
        new Thread(new QueueWorker(queue, storage, metrics)).start();

        // ================= METRICS LOGGER =================
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);

                    System.out.println(
                            "[NODE " + port + "] " +
                                    "Received=" + metrics.received +
                                    " Processed=" + metrics.processed +
                                    " Rejected=" + metrics.rejected +
                                    " Queue=" + queue.size()
                    );

                } catch (Exception ignored) {}
            }
        }).start();

        // ================= HTTP SERVER =================
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/sensor-data", exchange -> {

            if (!"POST".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            byte[] body = exchange.getRequestBody().readAllBytes();
            String json = new String(body);

            SensorData data = parseJson(json);

            metrics.received.incrementAndGet();

            boolean accepted = queue.offer(data);

            String msg;
            int code;

            if (!accepted) {
                metrics.rejected.incrementAndGet();
                msg = "QUEUE FULL - REJECTED";
                code = 503;
            } else {
                msg = "ACCEPTED";
                code = 200;
            }

            exchange.sendResponseHeaders(code, msg.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(msg.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Node started on port " + port);
    }

    // ================= SIMPLE JSON PARSER =================
    private SensorData parseJson(String json) {

        SensorData data = new SensorData();

        try {
            data.sensorId = extractInt(json, "sensorId");
            data.timestamp = System.currentTimeMillis();
            data.temperature = extractDouble(json, "temperature");
            data.humidity = extractDouble(json, "humidity");
            data.pressure = extractDouble(json, "pressure");
        } catch (Exception e) {
            System.out.println("Parse error: " + json);
        }

        return data;
    }

    private int extractInt(String json, String key) {
        String pattern = "\"" + key + "\":";
        return Integer.parseInt(
                json.split(pattern)[1].split(",")[0].trim()
        );
    }

    private double extractDouble(String json, String key) {
        String pattern = "\"" + key + "\":";
        return Double.parseDouble(
                json.split(pattern)[1].split(",|}")[0].trim()
        );
    }
}