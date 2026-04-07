# Book My Stay App – Use Case 9: Error Handling & Validation

## 📌 Overview
This project demonstrates how to implement robust error handling and validation in a Hotel Booking Management System using Core Java.

The focus is on ensuring system reliability by validating user inputs and preventing invalid operations such as incorrect room types, negative bookings, or overbooking.

---

## 🎯 Objective
To strengthen system stability by:
- Validating inputs before processing
- Preventing invalid system states
- Using custom exceptions for clarity
- Implementing fail-fast mechanisms

---

## 👤 Actors
- **Guest**: Provides booking input
- **Booking Validator**: Validates input and system constraints

---

## 🔄 Flow
1. Guest provides booking input
2. System validates input
3. If validation fails → Exception is thrown
4. Error message is displayed
5. System continues safely without crashing

---

## 🧠 Key Concepts Used

### 1. Input Validation
Ensures:
- Room type is valid
- Quantity is positive
- Requested rooms are available

### 2. Custom Exceptions
- `InvalidBookingException`
- Provides meaningful error messages

### 3. Fail-Fast Design
- Stops execution immediately when invalid input is detected

### 4. Guarding System State
- Prevents negative inventory
- Avoids inconsistent data

### 5. Graceful Failure Handling
- Errors are handled using try-catch
- System does not crash

---

## ✅ Features
- Validates room types
- Prevents negative bookings
- Avoids overbooking
- Displays clear error messages
- Maintains system stability

---

## ⚠️ Previous Use Case Limitation
Earlier implementations assumed valid inputs, which could lead to:
- Data corruption
- Incorrect reports
- System instability

---

## ▶️ How to Run

### Step 1: Compile
```bash
javac UseCase9ErrorHandlingValidation.java