# Hotel Booking Management System – Use Case 6

## Use Case 6: Reservation Confirmation & Room Allocation

### Goal
Confirm booking requests by assigning rooms safely while ensuring inventory consistency and preventing double-booking under all circumstances.

### Actors
- **Booking Service** – processes queued booking requests and performs room allocation.
- **Inventory Service** – maintains and updates room availability state.

### Flow
1. Booking request is dequeued from the request queue.
2. The system checks availability for the requested room type.
3. A unique room ID is generated and assigned.
4. The room ID is recorded to prevent reuse.
5. Inventory count is decremented immediately.
6. Reservation is confirmed.

### Key Concepts
- **Set Data Structure:** Stores allocated room IDs to prevent duplicates.
- **FIFO Queue:** Requests processed in arrival order.
- **Atomic Operations:** Allocation + inventory update occurs together.
- **Single Source of Truth:** Inventory immediately reflects current availability.

### Key Requirements
- Retrieve booking requests from the queue in FIFO order.
- Generate and assign a unique room ID for each confirmed reservation.
- Prevent reuse of room IDs across all allocations.
- Update inventory immediately after successful allocation.
- Ensure allocation logic maintains system consistency.

### Key Benefits
- Guaranteed uniqueness of room assignments
- Immediate synchronization between booking and inventory
- Elimination of double-booking scenarios

### Drawbacks of Previous Use Case
- Use Case 5 handled request ordering but did not confirm bookings.
- Without allocation and uniqueness enforcement, queued requests could still result in conflicting assignments.

### How to Run
1. Compile the Java file:

```bash
javac UseCase6HotelBookingApp.java