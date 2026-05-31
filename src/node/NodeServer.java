package node;

import model.SensorData;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NodeServer {

    private static final int MAX_QUEUE = 5;

    private final int port;

    private final BlockingQueue<SensorData> queue =
            new ArrayBlockingQueue<>(MAX_QUEUE);

    private final Metrics metrics = new Metrics();

    public NodeServer(int port) {

        this.port = port;

        CsvStorage storage = new CsvStorage();

        new Thread(
                new QueueWorker(
                        queue,
                        storage,
                        metrics
                )
        ).start();

        startMonitor();

        System.out.println(
                "Node started on port "
                        + port
        );
    }

    public boolean accept(SensorData data) {

        metrics.received.incrementAndGet();

        if (queue.remainingCapacity() == 0) {

            metrics.rejected.incrementAndGet();

            return false;
        }

        queue.offer(data);

        return true;
    }

    private void startMonitor() {

        Thread monitor = new Thread(() -> {

            while (true) {

                try {

                    Thread.sleep(5000);

                    System.out.println(
                            "[NODE "
                                    + port
                                    + "] Received="
                                    + metrics.received.get()
                                    + " Processed="
                                    + metrics.processed.get()
                                    + " Rejected="
                                    + metrics.rejected.get()
                                    + " Queue="
                                    + queue.size()
                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        monitor.setDaemon(true);
        monitor.start();
    }
}