import loadbalancer.LoadBalancer;
import node.NodeServer;
import producer.Producer;

public class Main {

    public static void main(String[] args) throws Exception {

        LoadBalancer lb = new LoadBalancer();

        lb.addNode(new NodeServer(8001));
        lb.addNode(new NodeServer(8002));
        lb.addNode(new NodeServer(8003));

        Producer producer = new Producer(lb);

        producer.start();
    }
}