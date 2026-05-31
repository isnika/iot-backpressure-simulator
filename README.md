# IoT Backpressure Simulator

A real-time distributed system simulator demonstrating backpressure, load balancing, and queue-based stream processing in IoT environments.
# Backpressure Simulator: IoT Sensor Overload

## Project Overview

This project simulates a distributed IoT sensor data processing system with a backpressure mechanism.

It demonstrates how modern streaming systems handle:

- High-frequency IoT sensor ingestion
- Distributed load balancing
- Queue-based buffering
- Backpressure (overload protection)
- Asynchronous processing workers
- Persistent storage (CSV / Database)

Project Category:
Category 12 – Stream Processing & Real-Time Distributed Databases

---

# System Architecture

```text
IoT Sensor ProducerProducer
         ↓
    Load Balancer
         ↓
 ┌───────┬───────┬
 ↓       ↓       ↓
NodeA   NodeB   NodeC
 ↓       ↓       ↓
Queue   Queue   Queue
 ↓       ↓       ↓
Worker  Worker  Worker
          ↓
      CSV Storage
```

## Node Distribution

| Node | SensorID Range |
|------|----------------|
| Node A | 1 – 100 |
| Node B | 101 – 200 |
| Node C | 201 – 300 |

## System Behavior
- Producer generates continuous sensor data
- Load balancer routes requests by sensorId
- Each node processes data independently
- Workers consume queue asynchronously


## Key Features

Distributed System


- Multiple independent nodes (8001, 8002, 8003)
- Horizontal workload distribution 

Queue-based Buffering
- Each node uses bounded queue (capacity = 5)
- Prevents memory overflow

Backpressure Mechanism
- Queue full → request rejected (HTTP 503)
- Protects system stability under high load

Asynchronous Workers
- Background threads process queue 
- Simulated delay mimics real bottlenecks

### Metrics Tracking

Each node monitors:

- Received requests
- Processed messages
- Rejected requests
- Queue size


Data Persistence
- Processed results stored in data.csv or database.
---

#  Technologies Used

- Java 17+
- HTTP Server (com.sun.net.httpserver)
- BlockingQueue (ArrayBlockingQueue)
- Multithreading (Thread / Runnable)
- Java HTTP Client
- ExecutorService

---

# Project Structure

```bash
src/main/java/
├── Main.java
├── model/
│   └── SensorData.java
├── producer/
│   └── Producer.java
├── loadbalancer/
│   └── LoadBalancer.java
└── node/
    ├── NodeServer.java
    ├── QueueWorker.java
    ├── Metrics.java
    └── CsvStorage.java
```

---

# Dataset
Synthetic IoT sensor data generated automatically. 

Schema:
- SensorID
- Timestamp
- Temperature
- Humidity
- Pressure

Scale: configurable
Typical demo: 10,000+ records
## Backpressure Mechanism

Each node maintains a bounded queue.

When queue is full:
- Request is rejected → HTTP 503
- OR producer slows down (feedback loop)

System Feedback Loop: Producer → Load → Queue → Backpressure Signal → Producer Adjustment

## How to Run

## 1. Clone Repository

```bash
git clone https://github.com/isnika/iot-backpressure-simulator.git
cd iot-backpressure-simulator
```

---

## 2. Run 

```bash
javac -d out src/main/java/**/*.java
java -cp out Main
```
 
---
## Failure Simulation
1. Burst Traffic

Increase producer rate suddenly

2. Node Overload

Add artificial delay in worker

3. Node Failure

Stop one node during runtime
---
## Metrics Collected
- Throughput
- Latency
- Queue Length
- Drop Rate

### Metrics Example

```text
Node A
---------
Received Requests : 1250
Processed Messages: 1210
Rejected Requests : 40
Queue Size        : 0

Node B
---------
Received Requests : 1180
Processed Messages: 1160
Rejected Requests : 20
Queue Size        : 0

Node C
---------
Received Requests : 1320
Processed Messages: 1285
Rejected Requests : 35
Queue Size        : 0
```
### Demo Output

```text
Node started on port 8001
Node started on port 8002
Node started on port 8003

Processed ID: 162 | queue=5
Processed ID: 99  | queue=5

[BACKPRESSURE] Sensor 161 slowed down

Processed ID: 203 | queue=4
Processed ID: 230 | queue=4
```
---
## Key Concepts Demonstrated
- Backpressure in distributed systems
- Queue-based buffering strategy
- Asynchronous processing model
- Load balancing
- Fault tolerance
- Real-time stream simulation
---
## Why This Project Matters
This project simulates real-world systems used in:

- Kafka-like streaming pipelines
- Microservices architectures
- IoT telemetry systems
- High-throughput backend systems

It demonstrates production-level system design thinking.
---
## Demo Scenarios
- Normal traffic processing
- System overload
- Backpressure activation
- Queue buildup visualization
- Node failure handling
- Performance comparison
---
## Achievements

- Simulated 10,000+ IoT events
- Implemented bounded-queue backpressure mechanism
- Demonstrated overload protection using HTTP 503 responses
- Distributed workload across multiple processing nodes
- Collected throughput, latency, queue length and drop-rate metrics

#  Team Information

## Team Name
NIKA

## Team Member
Nguyễn Khánh Huyền

---

# References

- Özsu, M. Tamer & Valduriez, Patrick
  Principles of Distributed Database Systems

- Spring Boot Documentation
- Java Concurrency Documentation