# IoT Backpressure Simulator

> A real-time distributed system simulator demonstrating backpressure, load balancing, and queue-based stream processing in IoT environments.
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

## 2. Translation

```bash
javac -d out src/main/java/**/*.java
```


## 3. Run

```bash
java -cp out Main
```
 
---
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


# License

This project is intended for educational, research, and demonstration purposes only.

It was created to explore and showcase concepts in distributed systems, including load balancing, asynchronous processing, queue-based buffering, and backpressure mechanisms.

--- 

# Vietnames Version

# IoT Backpressure Simulator

Mô phỏng hệ thống IoT xử lý dữ liệu thời gian thực theo kiến trúc phân tán, minh họa cơ chế Backpressure, Load Balancing và Queue-based Stream Processing.

---

# Giới thiệu

Dự án mô phỏng một hệ thống IoT xử lý dữ liệu thời gian thực theo kiến trúc phân tán.

Mục tiêu:

* Nghiên cứu cách dữ liệu được phân phối đến nhiều Node xử lý.
* Quan sát hành vi của hệ thống khi tải tăng cao.
* Minh họa cơ chế Backpressure trong môi trường thực tế.
* Đánh giá khả năng mở rộng và chịu lỗi của hệ thống.

---

# Kiến trúc hệ thống

Hệ thống gồm:

* Producer sinh dữ liệu cảm biến.
* Load Balancer phân phối dữ liệu.
* Các Node xử lý độc lập.
* Queue giới hạn kích thước.
* Worker xử lý bất đồng bộ.
* Bộ lưu trữ dữ liệu.

## Phân phối dữ liệu đến các Node

| Node   | Phạm vi Sensor ID |
| ------ | ----------------- |
| Node A | 1 – 100           |
| Node B | 101 – 200         |
| Node C | 201 – 300         |

Mỗi cảm biến sẽ được định tuyến đến một Node cụ thể dựa trên giá trị `SensorID`.

---

# Quy trình hoạt động

## Bước 1: Sinh dữ liệu

Producer liên tục tạo dữ liệu cảm biến bao gồm:

* SensorID
* Timestamp
* Temperature
* Humidity
* Pressure

## Bước 2: Cân bằng tải

Load Balancer nhận dữ liệu từ Producer và phân phối đến các Node dựa trên SensorID.

## Bước 3: Đưa vào hàng đợi

Mỗi Node duy trì một hàng đợi giới hạn kích thước để lưu trữ dữ liệu tạm thời trước khi xử lý.

## Bước 4: Xử lý bất đồng bộ

Worker chạy ở luồng nền liên tục lấy dữ liệu từ Queue và thực hiện xử lý.

## Bước 5: Lưu trữ

Dữ liệu sau khi xử lý được ghi vào file CSV hoặc cơ sở dữ liệu.

---

# Các tính năng chính

## Hệ thống phân tán

* Nhiều Node hoạt động độc lập.
* Mỗi Node chạy trên một cổng riêng.
* Dễ dàng mở rộng theo chiều ngang.

### Các cổng mặc định

| Node   | Port |
| ------ | ---- |
| Node A | 8001 |
| Node B | 8002 |
| Node C | 8003 |

---

## Bộ đệm dựa trên hàng đợi

Mỗi Node sử dụng:

```java
ArrayBlockingQueue<>(5)
```

### Đặc điểm

* Dung lượng giới hạn.
* Ngăn tràn bộ nhớ.
* Hấp thụ các đợt tăng tải ngắn hạn.

---

## Cơ chế Backpressure

### Khi Queue đầy

* Không nhận thêm dữ liệu mới.
* Trả về HTTP 503.
* Hoặc yêu cầu Producer giảm tốc độ gửi dữ liệu.

### Lợi ích

* Bảo vệ hệ thống khỏi quá tải.
* Tránh sập Node.
* Duy trì tính ổn định.

---

## Worker xử lý bất đồng bộ

Mỗi Node có Worker riêng.

### Nhiệm vụ

* Lấy dữ liệu từ Queue.
* Mô phỏng xử lý dữ liệu.
* Ghi dữ liệu vào bộ nhớ lưu trữ.

---

## Theo dõi chỉ số hệ thống

Mỗi Node ghi nhận:

* Số lượng yêu cầu nhận được.
* Số lượng dữ liệu đã xử lý.
* Số lượng yêu cầu bị từ chối.
* Kích thước hàng đợi hiện tại.

---

## Lưu trữ dữ liệu

Kết quả xử lý được lưu vào:

* File CSV.
* Cơ sở dữ liệu quan hệ.

### Ví dụ

```csv
sensorId,timestamp,temperature,humidity,pressure
125,1717311200,31.5,78.2,1008.6
```

---

# Công nghệ sử dụng

| Công nghệ          | Mục đích                 |
| ------------------ | ------------------------ |
| Java 17+           | Ngôn ngữ lập trình chính |
| HttpServer         | Xây dựng API             |
| ArrayBlockingQueue | Hàng đợi giới hạn        |
| ExecutorService    | Quản lý luồng            |
| Java HTTP Client   | Gửi yêu cầu HTTP         |
| Multithreading     | Xử lý đồng thời          |

---

# Cấu trúc dự án

```text
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

# Bộ dữ liệu mô phỏng

Dữ liệu được sinh tự động nhằm mô phỏng môi trường IoT thực tế.

## Cấu trúc dữ liệu

* SensorID
* Timestamp
* Temperature
* Humidity
* Pressure

## Quy mô dữ liệu

Có thể cấu hình:

* 1.000 bản ghi
* 10.000 bản ghi
* 50.000 bản ghi
* 100.000 bản ghi

---

# Cơ chế Backpressure

## Luồng hoạt động

```text
Producer
    │
    ▼
Sinh dữ liệu
    │
    ▼
Load Balancer
    │
    ▼
Queue tại Node
    │
    ▼
Queue đầy?
    │
 ┌──┴──┐
 │ Có  │
 └──┬──┘
    ▼
Backpressure
    │
    ▼
HTTP 503
    │
    ▼
Producer giảm tốc độ
```

---

# Hướng dẫn chạy chương trình

## Bước 1: Clone Repository

```bash
git clone https://github.com/isnika/iot-backpressure-simulator.git
cd iot-backpressure-simulator
```

## Bước 2: Biên dịch

```bash
javac -d out src/main/java/**/*.java
```

## Bước 3: Chạy chương trình

```bash
java -cp out Main
```

---

# Mô phỏng sự cố

## Kịch bản 1: Lưu lượng tăng đột biến

Tăng tốc độ sinh dữ liệu của Producer.

### Mục tiêu

* Quan sát tốc độ tăng Queue.
* Kiểm tra khả năng xử lý của hệ thống.

---

## Kịch bản 2: Node quá tải

Thêm độ trễ xử lý:

```java
Thread.sleep(2000);
```

### Mục tiêu

* Kích hoạt Backpressure.
* Quan sát tỷ lệ dữ liệu bị từ chối.

---

## Kịch bản 3: Node gặp sự cố

Tắt một Node trong lúc hệ thống đang chạy.

### Mục tiêu

* Kiểm tra khả năng chịu lỗi.
* Quan sát ảnh hưởng tới thông lượng.

---

# Các chỉ số thu thập

| Chỉ số       | Ý nghĩa                   |
| ------------ | ------------------------- |
| Throughput   | Số bản tin xử lý mỗi giây |
| Latency      | Độ trễ xử lý              |
| Queue Length | Độ dài hàng đợi           |
| Drop Rate    | Tỷ lệ dữ liệu bị từ chối  |

---

# Ví dụ kết quả thống kê

## Node A

```text
Received Requests : 1250
Processed Messages: 1210
Rejected Requests : 40
Queue Size        : 0
```

## Node B

```text
Received Requests : 1180
Processed Messages: 1160
Rejected Requests : 20
Queue Size        : 0
```

## Node C

```text
Received Requests : 1320
Processed Messages: 1285
Rejected Requests : 35
Queue Size        : 0
```

---

# Kết quả chạy mẫu

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

# Các khái niệm được minh họa

* Backpressure trong hệ thống phân tán.
* Cân bằng tải.
* Hàng đợi giới hạn kích thước.
* Xử lý bất đồng bộ.
* Khả năng chịu lỗi.
* Mô phỏng luồng dữ liệu thời gian thực.

---

# Ý nghĩa của dự án

Dự án mô phỏng các kiến trúc được sử dụng trong thực tế như:

* Apache Kafka.
* Hệ thống Event Streaming.
* Kiến trúc Microservices.
* Hệ thống Telemetry cho IoT.
* Backend xử lý dữ liệu thông lượng cao.

Qua đó giúp hiểu rõ các nguyên lý thiết kế hệ thống phân tán hiện đại.

---

# Thành tựu đạt được

* Mô phỏng thành công hơn 10.000 sự kiện IoT.
* Xây dựng cơ chế Backpressure dựa trên hàng đợi giới hạn.
* Bảo vệ hệ thống bằng phản hồi HTTP 503 khi quá tải.
* Phân phối dữ liệu trên nhiều Node xử lý.
* Thu thập các chỉ số hiệu năng quan trọng.
* Mô phỏng thành công các tình huống lỗi và quá tải.

---

# Thông tin nhóm

## Tên nhóm

**NIKA**

## Thành viên

Nguyễn Khánh Huyền

---

# Tài liệu tham khảo

1. Özsu, M. Tamer & Valduriez, Patrick — Principles of Distributed Database Systems
2. Java Concurrency Documentation
3. Java HTTP Server Documentation
4. Spring Boot Documentation

---

# Giấy phép

Dự án này chỉ dành cho mục đích học tập, nghiên cứu và trình diễn.

Dự án được xây dựng nhằm tìm hiểu và minh họa các khái niệm trong hệ thống phân tán, bao gồm cân bằng tải (load balancing), xử lý bất đồng bộ (asynchronous processing), bộ đệm dựa trên hàng đợi (queue-based buffering) và cơ chế backpressure.

