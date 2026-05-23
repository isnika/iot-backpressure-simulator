# iot-backpressure-simulator
A real-time distributed system simulator that demonstrates backpressure, load balancing, and queue-based stream processing in IoT environments.

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

IoT Sensor Producer
        ↓
 Load Balancer
        ↓
 ┌─────────────┐
 │ Distributed │
 │    Nodes    │
 └─────────────┘
   ↓    ↓    ↓
NodeA NodeB NodeC
   ↓    ↓    ↓
 Local Storage
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

Metrics Tracking: Each node monitors:
- Received requests
- Processed messages
- Rejected requests
- Queue size


Data Persistence
- Processed results stored in data.csv or databas
---

#  Technologies Used

- Java 17+
- HTTP Server (com.sun.net.httpserver)
- Spring Boot (optional version)
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

Scale: 200,000 – 500,000 records
## Backpressure Mechanism

Each node maintains a bounded queue.

When queue is full:
- Request is rejected → HTTP 503
- OR producer slows down (feedback loop)

System Feedback Loop: Producer → Load → Queue → Backpressure Signal → Producer Adjustment

## How to Run

## 1. Clone Repository

```bash
git clone <repository-url>
cd project-root
```

---

## 2. Start Nodes

```bash
    cd node-a && mvn spring-boot:run
    
    cd node-b && mvn spring-boot:run
    
    cd node-c && mvn spring-boot:run
```

## 3. Start System Core

```bash
    cd load-balancer && mvn spring-boot:run

    cd producer && mvn spring-boot:run
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
-Node failure handling
- Performance comparison
---

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