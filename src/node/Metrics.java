package node;

import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {

    public final AtomicInteger received = new AtomicInteger();
    public final AtomicInteger processed = new AtomicInteger();
    public final AtomicInteger rejected = new AtomicInteger();

}