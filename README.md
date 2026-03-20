# Use Case 3: Centralized Room Inventory Management

## Project Overview

This project presents the design and implementation of a Hotel Booking Management System, illustrating the practical application of Core Java and fundamental data structures in real-world scenarios. The system is developed incrementally, with each use case introducing a specific concept that addresses common software engineering challenges such as fair request handling, inventory consistency, and prevention of double-booking.

Use Case 3 introduces **centralized room inventory management** using a `HashMap` to maintain a single source of truth for room availability.

---

## Use Case Goal

- Replace scattered availability variables from previous use cases with a **centralized, consistent data structure**.
- Demonstrate how a `HashMap` provides fast lookup, updates, and a scalable solution for managing room inventory.
- Maintain separation of concerns: room characteristics remain part of the domain model, while inventory is managed in a dedicated class.

---

## Actor

- **RoomInventory** – Responsible for managing and exposing room availability across the system.

---

## Flow

1. The system initializes the inventory component.
2. Room types are registered with their available counts.
3. Availability is stored and retrieved from a centralized `HashMap`.
4. Updates to availability are performed through controlled methods.
5. The current inventory state is displayed when requested.

---

## Key Concepts Used

- **Problem of Scattered State:** Previous use case managed availability in separate variables, causing inconsistency and poor scalability.
- **HashMap:** `HashMap<String, Integer>` maps room types to their available counts, allowing O(1) average-time access and updates.
- **Single Source of Truth:** All availability data is centralized, eliminating discrepancies.
- **Encapsulation of Inventory Logic:** Inventory operations are accessed through dedicated methods, reducing coupling.
- **Separation of Concerns:** Room characteristics remain in the Room model; inventory logic is managed separately.
- **Scalability:** Adding new room types only requires adding a new entry to the map.

---

## Key Requirements

- Initialize room availability using a constructor.
- Store room availability using a `HashMap`.
- Provide methods to retrieve current availability.
- Support controlled updates to room availability.
- Ensure inventory state remains consistent across operations.

---

## Key Benefits

- Centralized, consistent room availability
- Fast lookup and updates using HashMap
- Improved scalability when introducing new room types
- Reduced risk of inconsistent system state

---

## Drawbacks of Previous Use Case

- Availability was managed using independent variables.
- This approach does not scale and increases the risk of inconsistent system state as complexity grows.