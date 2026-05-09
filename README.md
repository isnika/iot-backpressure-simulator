# iot-backpressure-simulator
A real-time distributed system simulator that models IoT sensor data overload and demonstrates backpressure mechanisms in stream processing pipelines.


# Backpressure Simulator: IoT Sensor Overload

## Project Overview

This project simulates a distributed IoT sensor data processing system using queue-based backpressure control.

The system is designed to evaluate how backpressure mechanisms help maintain stability when incoming sensor traffic exceeds processing capacity.

Project Category:
Category 12 – Stream Processing & Real-Time Distributed Databases

---

# Objectives

- Simulate real-time IoT sensor traffic
- Build a distributed multi-node processing system
- Implement queue-based backpressure
- Measure system performance under overload conditions
- Compare:
    - System without backpressure
    - System with backpressure

---

# System Architecture

```text
Sensor Producer
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

---

#  Technologies Used

- Java
- Spring Boot
- REST API
- ExecutorService
- SQLite / H2 Database
- Jackson JSON

---

# Project Structure

```bash
project-root/
│
├── producer/
├── load-balancer/
├── node-a/
├── node-b/
├── node-c/
├── dataset/
├── docs/
├── README.md
└── pom.xml
```

---

# Dataset

Synthetic IoT sensor data generated automatically.

## Dataset Schema

- SensorID
- Timestamp
- Temperature
- Humidity
- Pressure

## Dataset Size

- 200,000 – 500,000 records

---

# Backpressure Mechanism

Each node maintains a processing queue.

When queue length exceeds a threshold:
- The node rejects new requests
  OR
- Sends a slowdown signal to the producer

The producer dynamically adjusts sending speed based on node feedback.

This feedback loop helps prevent overload and stabilizes the distributed system.

---

# How to Run

## 1. Clone Repository

```bash
git clone <repository-url>
cd project-root
```

---

## 2. Run Node A

```bash
cd node-a
mvn spring-boot:run
```

---

## 3. Run Node B

```bash
cd node-b
mvn spring-boot:run
```

---

## 4. Run Node C

```bash
cd node-c
mvn spring-boot:run
```

---

## 5. Run Load Balancer

```bash
cd load-balancer
mvn spring-boot:run
```

---

## 6. Run Producer

```bash
cd producer
mvn spring-boot:run
```

---

#  Failure Simulation

## 1. Burst Traffic

Suddenly increase producer sending rate.

## 2. Node Overload

Add processing delay to queue consumers.

## 3. Node Failure

Stop one node during processing.

---

#  Metrics Collected

- Throughput
- Latency
- Queue Length
- Drop Rate

---

#  Demo Scenarios

The demo video demonstrates:
- Normal traffic processing
- Traffic overload
- Backpressure activation
- Queue growth
- Node failure handling
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