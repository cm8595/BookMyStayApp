# Use Case 2 – Basic Room Types & Static Availability

## Overview
This use case introduces object-oriented domain modeling using abstract classes and inheritance.  
The system demonstrates basic room types (Single, Double, Suite) and their static availability.

---

## Goal
- Introduce object modeling through abstraction
- Define common attributes for all room types
- Display room details and availability
- Lay the foundation for future inventory management

---

## Features
- Abstract `Room` class defines shared attributes
- Concrete room classes (`SingleRoom`, `DoubleRoom`, `SuiteRoom`) extend the abstract class
- Room objects created in the application entry point
- Availability stored as simple variables
- Room details and availability printed to the console

---

## Key Concepts
- **Abstraction:** `Room` abstract class models general properties
- **Inheritance:** Concrete room types reuse common behavior
- **Polymorphism:** Room objects referenced using the abstract type
- **Encapsulation:** Room attributes are protected
- **Static Availability Representation:** Availability stored as separate variables

---

## File Structure

src/
├─ Room.java
├─ SingleRoom.java
├─ DoubleRoom.java
├─ SuiteRoom.java
└─ UseCase2HotelBookingApp.java
README.md


---

## How to Run

1. Compile all files:
```bash
javac *.java

Run the application:

java UseCase2HotelBookingApp

Output will show room details and availability.

Author

Chaitanya Bhargav Malakonda