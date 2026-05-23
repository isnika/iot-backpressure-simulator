package producer;

import loadbalancer.LoadBalancer;
import model.SensorData;

import java.util.Random;

public class Producer {

    private final LoadBalancer lb = new LoadBalancer();
    private final Random rand = new Random();

    public void start() throws Exception {

        while (true) {

            SensorData data = new SensorData(
                    rand.nextInt(300) + 1,
                    20 + rand.nextDouble() * 10,
                    50 + rand.nextDouble() * 20,
                    1000 + rand.nextDouble() * 50
            );

            lb.route(data);

            Thread.sleep(20); // overload
        }
    }
}