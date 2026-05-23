import node.NodeServer;
import producer.Producer;

public class Main {

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try { new NodeServer(8001).start(); }
            catch (Exception e) { e.printStackTrace(); }
        }).start();

        new Thread(() -> {
            try { new NodeServer(8002).start(); }
            catch (Exception e) { e.printStackTrace(); }
        }).start();

        new Thread(() -> {
            try { new NodeServer(8003).start(); }
            catch (Exception e) { e.printStackTrace(); }
        }).start();

        Thread.sleep(1000);

        new Producer().start();
    }
}