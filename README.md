# Book My Stay App – Use Case 12: Data Persistence & System Recovery

## 📌 Overview
This module introduces persistence and recovery using file-based storage. It ensures that system state survives application restarts.

---

## 🎯 Objective
- Persist inventory and booking data
- Restore system state after restart
- Handle failures safely

---

## 👤 Actors
- **System**: Handles startup and shutdown
- **Persistence Service**: Manages save/load operations

---

## 🔄 Flow
1. System starts
2. Loads previous state (if exists)
3. Performs operations
4. Saves state before shutdown
5. Restores state on next run

---

## 🧠 Key Concepts

### 1. Serialization
Converts objects into byte stream for storage.

### 2. Deserialization
Reconstructs objects from stored data.

### 3. Persistence
Ensures data survives restarts.

### 4. Stateful Systems
Maintains continuity across executions.

### 5. Failure Handling
Gracefully handles:
- Missing file
- Corrupted data

---

## ✅ Features
- File-based persistence
- Booking + inventory recovery
- Safe startup fallback
- Durable system state

---

## ⚠️ Previous Limitation
Earlier use cases lost all data on restart.

---

## ▶️ How to Run

### Compile
```bash
javac UseCase12DataPersistenceRecovery.java