import java.util.Random;

public class Sensor {

    public static void main(String[] args) throws Exception {
        Random rand = new Random();

        while (true) {
            String data = generateData(rand);

            String response = LoadBalancer.forward(data);

            if ("BACKPRESSURE".equals(response)) {
                System.out.println("⚠️ Backpressure → slowing down...");
                Thread.sleep(500);
            } else {
                Thread.sleep(10);
            }
        }
    }

    private static String generateData(Random rand) {
        int id = rand.nextInt(300);
        double temp = 20 + rand.nextDouble() * 10;
        double humidity = 40 + rand.nextDouble() * 20;

        return String.format(
                "{\"sensorId\":%d,\"temp\":%.2f,\"humidity\":%.2f}",
                id, temp, humidity
        );
    }
}