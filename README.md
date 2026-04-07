# Book My Stay App – Use Case 10: Booking Cancellation & Inventory Rollback

## 📌 Overview
This module introduces booking cancellation functionality with safe rollback mechanisms. It ensures that inventory and booking states remain consistent after reversing a confirmed booking.

---

## 🎯 Objective
- Enable safe cancellation of bookings
- Restore system state reliably
- Maintain inventory consistency

---

## 👤 Actors
- **Guest**: Initiates cancellation
- **Cancellation Service**: Handles validation and rollback

---

## 🔄 Flow
1. Guest requests cancellation
2. System validates booking existence
3. Room ID is pushed to rollback stack
4. Inventory is restored
5. Booking marked inactive

---

## 🧠 Key Concepts

### 1. Stack (LIFO)
Used to track rollback operations:
- Last booked → First cancelled

### 2. State Reversal
Undo allocation safely without breaking system

### 3. Controlled Mutation
Steps executed in strict order:
- Record → Restore → Update

### 4. Validation
Prevents:
- Duplicate cancellations
- Invalid booking IDs

---

## ✅ Features
- Booking creation
- Safe cancellation
- Inventory restoration
- Rollback tracking using stack

---

## ⚠️ Previous Limitation
Use Case 9 handled validation but lacked rollback capability.

---

## ▶️ How to Run

### Compile
```bash
javac UseCase10BookingCancellation.java