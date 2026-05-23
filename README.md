IoT Backpressure Simulator (Distributed Queue System)
📖 Overview

This project simulates a distributed IoT data processing system with backpressure mechanism.

It demonstrates how a system handles:

High-frequency data from sensors (Producer)
Load balancing across multiple nodes
Queue-based buffering
Backpressure (request rejection when overloaded)
Asynchronous processing via worker threads
Data persistence (CSV storage)
⚙️ System Architecture
Producer (IoT Sensors)
↓
Load Balancer
↓
┌───────────────┐
│   Node 1      │
│   Queue       │ → Worker → CSV
├───────────────┤
│   Node 2      │
│   Queue       │ → Worker → CSV
├───────────────┤
│   Node 3      │
│   Queue       │ → Worker → CSV
└───────────────┘
🚀 Key Features
✔ Distributed System
Multiple Node servers (8001, 8002, 8003)
LoadBalancer distributes requests based on sensorId
✔ Queue-based Processing
Each Node has bounded queue (capacity = 5)
Prevents system overload
✔ Backpressure Mechanism
When queue is full → request is rejected (HTTP 503)
Prevents system crash under high load
✔ Worker Processing
Background thread processes queued data
Simulated delay to demonstrate bottleneck
✔ Metrics Tracking

Each node tracks:

Received requests
Processed messages
Rejected messages
Queue size
✔ Data Persistence
Processed data is stored in data.csv
🧱 Technologies Used
Java 17+
Java HTTP Server (com.sun.net.httpserver)
BlockingQueue (ArrayBlockingQueue)
Multithreading (Thread, Runnable)
Java HTTP Client
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
├── └── NodeServer.java
├── └── QueueWorker.java
├── └── Metrics.java
└── └── CsvStorage.java
▶️ How to Run
1️⃣ Compile project
javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
2️⃣ Run system
java -cp out Main
🧪 How to Test

After running Main:

✔ Producer sends data continuously
Simulates IoT sensors generating data
✔ LoadBalancer distributes requests
Routes based on sensorId:
0–100 → Node 8001
101–200 → Node 8002
201–300 → Node 8003
✔ Node behavior

You will observe:

📌 Accepted requests
ACCEPTED
📌 Backpressure (queue full)
QUEUE FULL - REJECTED (503)
📌 Worker processing
Processed ID: 45 | Queue size: 3
📊 Metrics Example

Each node prints:

[NODE 8001] Received=120 Processed=110 Rejected=10 Queue=5
💥 Key Concepts Demonstrated
1. Backpressure

When system is overloaded:

Queue becomes full
New requests are rejected
System remains stable
2. Distributed Processing
   Multiple nodes process data independently
   Load is shared across nodes
3. Asynchronous Processing
   Producer ≠ Worker speed
   Queue buffers mismatch
4. Fault Tolerance (basic)
   System continues working even if one node stops
   📌 Why this project matters

This project demonstrates real-world system concepts used in:

Kafka-like systems
Microservices architecture
IoT data pipelines
High-throughput backend systems
📈 Possible Improvements
Add health check for nodes
Implement retry mechanism in LoadBalancer
Add dynamic scaling for workers
Replace CSV with database storage
Add monitoring dashboard

👨‍💻 Author
Student project – Distributed Systems / IoT Simulation
Focus: Backpressure, Queueing, Load Balancing