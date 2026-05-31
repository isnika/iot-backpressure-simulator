package loadbalancer;

import model.SensorData;
import node.NodeServer;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {

    private final List<NodeServer> nodes = new ArrayList<>();

    private int index = 0;

    public void addNode(NodeServer node) {
        nodes.add(node);
    }

    public boolean route(SensorData data) {

        if (nodes.isEmpty()) {
            return false;
        }

        NodeServer node = nodes.get(index);

        index = (index + 1) % nodes.size();

        return node.accept(data);
    }
}