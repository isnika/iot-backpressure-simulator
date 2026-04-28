import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.Queue;

public class Node {

    private static Queue<String> queue = new LinkedList<>();
    private static final int MAX_QUEUE = 100;

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]); // truyền port khi chạy

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ingest", new Handler());
        server.setExecutor(null);
        server.start();

        System.out.println("Node running on port " + port);

        // Thread xử lý queue
        new Thread(() -> {
            while (true) {
                if (!queue.isEmpty()) {
                    String data = queue.poll();
                    process(data);
                }

                try {
                    Thread.sleep(50); // tốc độ xử lý
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response;

            if (queue.size() >= MAX_QUEUE) {
                response = "BACKPRESSURE";
            } else {
                InputStream is = exchange.getRequestBody();
                String data = new String(is.readAllBytes());
                queue.add(data);
                response = "OK";
            }

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private static void process(String data) {
        System.out.println("Processed: " + data);
    }
}