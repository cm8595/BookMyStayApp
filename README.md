# Hotel Booking Management System – Use Case 4

## Use Case 4: Room Search & Availability Check

### Goal
Enable guests to view available rooms and their details without modifying system state, reinforcing safe data access and clear separation of responsibilities.

### Actors
- **Guest** – initiates a search to view available room options.
- **Search Service** – handles read-only access to inventory and room information.

### Flow
1. Guest initiates a room search request.
2. The system retrieves availability data from the centralized inventory.
3. Room details and pricing are obtained from room objects.
4. Unavailable room types are filtered out.
5. Available room types and their details are displayed.
6. System state remains unchanged.

### Key Concepts
- **Read-Only Access:** Search operations do not alter inventory data.
- **Defensive Programming:** Only valid and available room types are displayed.
- **Separation of Concerns:** Search functionality is isolated from booking and inventory update logic.
- **Inventory as State Holder:** Inventory stores current availability counts; no updates occur during searches.
- **Domain Model Usage:** Room objects provide descriptive information without duplicating data in the inventory.
- **Validation Logic:** Room types with zero availability are excluded.

### Key Requirements
- Retrieve room availability from the centralized inventory.
- Display only room types with availability greater than zero.
- Show room details and pricing using room domain objects.
- Ensure inventory data is not modified during search operations.
- Maintain a clear boundary between search logic and booking logic.

### Key Benefits
- Accurate availability visibility without state mutation.
- Reduced risk of accidental inventory corruption.
- Clear separation between read-only and write operations.

### Drawbacks of Previous Use Case
- Use Case 3 introduced centralized inventory but did not differentiate between read and write access.
- Without explicit separation, inventory could be accidentally modified during non-booking operations.

### How to Run
1. Compile the Java file:

```bash
javac UseCase4HotelBookingApp.java