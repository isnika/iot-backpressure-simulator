package node;

import model.SensorData;

import java.util.concurrent.BlockingQueue;

public class QueueWorker implements Runnable {

    private final BlockingQueue<SensorData> queue;
    private final CsvStorage storage;
    private final Metrics metrics;

    public QueueWorker(
            BlockingQueue<SensorData> queue,
            CsvStorage storage,
            Metrics metrics
    ) {
        this.queue = queue;
        this.storage = storage;
        this.metrics = metrics;
    }

    @Override
    public void run() {

        while (true) {

            try {

                SensorData data = queue.take();

                Thread.sleep(100);

                storage.save(data);

                metrics.processed.incrementAndGet();

                System.out.println(
                        "Processed ID: "
                                + data.sensorId
                                + " | queue="
                                + queue.size()
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}