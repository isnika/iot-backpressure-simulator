package producer;

import loadbalancer.LoadBalancer;
import model.SensorData;

import java.util.Random;

public class Producer {

    private final LoadBalancer lb;
    private final Random rand = new Random();

    public Producer(LoadBalancer lb) {
        this.lb = lb;
    }

    public void start() throws Exception {

        while (true) {

            SensorData data = new SensorData(
                    rand.nextInt(300) + 1,
                    20 + rand.nextDouble() * 10,
                    50 + rand.nextDouble() * 20,
                    1000 + rand.nextDouble() * 50
            );

            boolean accepted = lb.route(data);

            if (!accepted) {
                System.out.println(
                        "[BACKPRESSURE] Sensor "
                                + data.sensorId
                                + " slowed down"
                );

                Thread.sleep(200);
            } else {
                Thread.sleep(1);
            }
        }
    }
}