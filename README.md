# Hotel Booking Management System – Use Case 5

## Use Case 5: Booking Request (First-Come-First-Served)

### Goal
Handle multiple booking requests fairly by introducing a request intake mechanism that preserves arrival order, reflecting real-world booking behavior during peak demand.

### Actors
- **Reservation** – represents a guest’s intent to book a room.
- **Booking Request Queue** – manages and orders incoming booking requests.

### Flow
1. Guest submits a booking request.
2. The request is added to the booking queue.
3. Requests are stored in arrival order.
4. Queued requests wait for processing by the allocation system.
5. No inventory mutation occurs at this stage.

### Key Concepts
- **Queue Data Structure:** FIFO ensures fairness for simultaneous requests.
- **First-Come-First-Served (FIFO):** Earliest request is processed first.
- **Decoupling Request Intake from Allocation:** Intake is separated from allocation logic.
- **Fairness:** All guests are treated equally based on request arrival time.

### Key Requirements
- Accept booking requests from guests.
- Store requests in a queue structure.
- Preserve the order in which requests arrive.
- No room allocation or inventory updates occur at this stage.
- Prepare requests for subsequent processing.

### Key Benefits
- Fair and deterministic booking request handling.
- Predictable system behavior under peak load.
- Simplified request coordination before allocation.

### Drawbacks of Previous Use Case
- Use Case 4 allowed room visibility but did not handle booking intent.
- Without a request intake mechanism, simultaneous booking attempts could not be managed fairly.

### How to Run
1. Compile the Java file:

```bash
javac UseCase5HotelBookingApp.java