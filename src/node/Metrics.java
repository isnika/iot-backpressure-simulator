package node;

import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {
    public AtomicInteger received = new AtomicInteger(0);
    public AtomicInteger processed = new AtomicInteger(0);
    public AtomicInteger rejected = new AtomicInteger(0);
}