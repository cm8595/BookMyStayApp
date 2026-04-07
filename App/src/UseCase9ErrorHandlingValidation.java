/**
 * Author: Chaitanya Bhargav Malakonda
 * Project: Book My Stay App
 * Use Case 9: Error Handling & Validation
 */

import java.util.HashMap;
import java.util.Map;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Room Inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    public int getAvailableRooms(String roomType) {
        return inventory.get(roomType); // safe because validated before use
    }

    public void bookRoom(String roomType, int quantity) throws InvalidBookingException {
        int available = inventory.get(roomType);

        if (quantity <= 0) {
            throw new InvalidBookingException("Quantity must be greater than zero.");
        }

        if (available < quantity) {
            throw new InvalidBookingException("Not enough rooms available for " + roomType);
        }

        inventory.put(roomType, available - quantity);
    }

    public void displayInventory() {
        System.out.println("\nFinal Room Availability:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// Validator Class
class BookingValidator {

    public static void validate(String roomType, int quantity, RoomInventory inventory)
            throws InvalidBookingException {

        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty.");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (quantity <= 0) {
            throw new InvalidBookingException("Quantity must be greater than zero.");
        }

        if (inventory.getAvailableRooms(roomType) < quantity) {
            throw new InvalidBookingException("Requested rooms exceed availability.");
        }
    }
}

// Main Class (ONLY public class)
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        String[] roomTypes = {"Single", "Suite", "Double", "Luxury"};
        int[] quantities = {2, 3, -1, 1};

        for (int i = 0; i < roomTypes.length; i++) {

            String roomType = roomTypes[i];
            int quantity = quantities[i];

            System.out.println("\nBooking Request → " + roomType + " x " + quantity);

            try {
                // Step 1: Validate (Fail-Fast)
                BookingValidator.validate(roomType, quantity, inventory);

                // Step 2: Book
                inventory.bookRoom(roomType, quantity);

                System.out.println("✅ Booking Successful");

            } catch (InvalidBookingException e) {
                System.out.println("❌ Booking Failed: " + e.getMessage());
            }
        }

        inventory.displayInventory();
    }
}
