# Book My Stay App – Use Case 11: Concurrent Booking Simulation

## 📌 Overview
This module demonstrates how concurrent booking requests are handled safely using thread synchronization in Java.

It simulates multiple users booking rooms simultaneously and ensures that inventory updates remain consistent.

---

## 🎯 Objective
- Simulate concurrent booking requests
- Prevent race conditions
- Ensure thread-safe inventory updates

---

## 👤 Actors
- **Multiple Guests**: Generate booking requests
- **Booking Processor Threads**: Process requests concurrently

---

## 🔄 Flow
1. Booking requests are added to a shared queue
2. Multiple threads fetch requests
3. Inventory updates happen in synchronized blocks
4. System prevents overbooking

---

## 🧠 Key Concepts

### 1. Race Condition
Occurs when multiple threads modify shared data simultaneously.

### 2. Thread Safety
Ensures correct behavior under concurrent access.

### 3. Shared Mutable State
Queue + Inventory are shared resources.

### 4. Critical Section
Inventory update is protected using `synchronized`.

### 5. Synchronization
Ensures only one thread updates inventory at a time.

---

## ✅ Features
- Multi-threaded booking simulation
- Shared request queue
- Thread-safe inventory management
- Prevention of double booking

---

## ⚠️ Previous Limitation
Earlier use cases assumed single-threaded execution.

---

## ▶️ How to Run

### Compile
```bash
javac UseCase11ConcurrentBookingSimulation.java