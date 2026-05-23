# IoT Backpressure Simulator (Distributed Queue System)

![Java](https://img.shields.io/badge/Java-17+-orange)
![Architecture](https://img.shields.io/badge/Type-Distributed%20System-blue)
![Status](https://img.shields.io/badge/Status-Simulation-green)

---

## 📖 Overview

This project simulates a **distributed IoT data processing system** with a **backpressure mechanism**.

It demonstrates how modern streaming systems handle:

- High-frequency IoT sensor ingestion
- Distributed load balancing
- Queue-based buffering
- Backpressure (overload protection)
- Asynchronous processing workers
- Persistent storage (CSV)

---

## ⚙️ System Architecture

### High-Level Flow

```mermaid
flowchart TD
    A[IoT Sensor Producer] --> B[Load Balancer]
    B --> C[Node A]
    B --> D[Node B]
    B --> E[Node C]

    C --> C1[Queue]
    D --> D1[Queue]
    E --> E1[Queue]

    C1 --> C2[Worker]
    D1 --> D2[Worker]
    E1 --> E2[Worker]

    C2 --> F[(CSV Storage)]
    D2 --> F
    E2 --> F
    
🧠 Core Design
🔹 Node Distribution
Node	        Sensor ID Range
Node A	        1 – 100
Node B	        101 – 200
Node C	        201 – 300

🔹 System Behavior
- Producer generates continuous sensor data
- Load balancer routes requests by sensorId
- Each node processes data independently
- Workers consume queue asynchronously

🚀 Key Features
    🟢 Distributed System
        - Multiple independent nodes (8001, 8002, 8003)
        - Horizontal workload distribution
    🟡 Queue-based Buffering
        - Each node uses bounded queue (capacity = 5)
        - Prevents memory overflow

🔴 Backpressure Mechanism
- Queue full → request rejected (HTTP 503)
- Protects system stability under high load

⚙️ Asynchronous Workers
- Background threads process queue
- Simulated delay mimics real bottlenecks

📊 Metrics Tracking
Each node monitors:
- Received requests
- Processed messages
- Rejected requests
- Queue size

💾 Data Persistence
- Processed results stored in data.csv

🧱 Technologies Used
- Java 17+
- HTTP Server (com.sun.net.httpserver)
- BlockingQueue (ArrayBlockingQueue)
- Multithreading (Thread / Runnable)
- Java HTTP Client

📂 Project Structure
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
    
▶️ How to Run
    1.  Compile
        javac -d out $(find . -name "*.java")
    2. Start System
        java -cp out Main

🧪 Runtime Behavior
📡 Producer
- Continuously generates IoT sensor data

⚖️ Load Balancer Routing
- 0–100 → Node 8001
- 101–200 → Node 8002
- 201–300 → Node 8003

🧩 Node States
State	Meaning
ACCEPTED	        Request added to queue
REJECTED (503)	    Queue full → backpressure triggered
PROCESSED	        Worker consumed data

📊 Example Metrics
[NODE 8001] Received=120 Processed=110 Rejected=10 Queue=5

💥 Key Concepts Demonstrated
- Backpressure → prevents system overload collapse
- Distributed Processing → multiple independent nodes
- Asynchronous Execution → decoupled producer & consumer
- Fault Tolerance → system continues even if node fails

📌 Why This Project Matters
This project models real-world distributed systems used in:
- Kafka-like streaming pipelines
- Microservices architectures
- IoT telemetry systems
- High-throughput backend systems

📈 Possible Improvements
- Add node health-check endpoints
- Retry mechanism in load balancer
- Auto-scaling workers
- Replace CSV with database (PostgreSQL / MongoDB)
- Add Grafana-style monitoring dashboard

👨‍💻 Author
Student Project – Distributed Systems / IoT Simulation
Focus: Backpressure, Queueing, Load Balancing