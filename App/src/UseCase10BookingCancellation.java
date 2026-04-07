public class UseCase10BookingCancellation {
}
/**
 * Author: Malakonda Chaitanya Bhargav
 * Project: Book My Stay App
 * Use Case 10: Booking Cancellation & Inventory Rollback
 */

import java.util.*;

// Custom Exception
class InvalidCancellationException extends Exception {
    public InvalidCancellationException(String message) {
        super(message);
    }
}

// Booking Model
class Booking {
    String bookingId;
    String roomType;
    String roomId;
    boolean isActive;

    public Booking(String bookingId, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }
}

// Inventory Manager
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Stack<String>> roomPool = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);

        roomPool.put("Single", new Stack<>());
        roomPool.put("Double", new Stack<>());

        // Preload room IDs
        roomPool.get("Single").push("S1");
        roomPool.get("Single").push("S2");

        roomPool.get("Double").push("D1");
        roomPool.get("Double").push("D2");
    }

    public boolean isValidRoomType(String type) {
        return inventory.containsKey(type);
    }

    public String allocateRoom(String type) throws Exception {
        if (!isValidRoomType(type)) {
            throw new Exception("Invalid room type");
        }

        if (inventory.get(type) <= 0) {
            throw new Exception("No rooms available");
        }

        String roomId = roomPool.get(type).pop();
        inventory.put(type, inventory.get(type) - 1);

        return roomId;
    }

    public void releaseRoom(String type, String roomId) {
        roomPool.get(type).push(roomId);
        inventory.put(type, inventory.get(type) + 1);
    }

    public void displayInventory() {
        System.out.println("\nInventory Status:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// Booking Service
class BookingService {
    private Map<String, Booking> bookings = new HashMap<>();
    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void createBooking(String bookingId, String roomType) throws Exception {
        String roomId = inventory.allocateRoom(roomType);
        bookings.put(bookingId, new Booking(bookingId, roomType, roomId));
        System.out.println("Booking Confirmed → ID: " + bookingId + ", Room: " + roomId);
    }

    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }

    public void updateBooking(Booking booking) {
        bookings.put(booking.bookingId, booking);
    }
}

// Cancellation Service
class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();

    public void cancelBooking(String bookingId, BookingService bookingService, RoomInventory inventory)
            throws InvalidCancellationException {

        Booking booking = bookingService.getBooking(bookingId);

        // Validation
        if (booking == null) {
            throw new InvalidCancellationException("Booking does not exist.");
        }

        if (!booking.isActive) {
            throw new InvalidCancellationException("Booking already cancelled.");
        }

        // Step 1: Record for rollback
        rollbackStack.push(booking.roomId);

        // Step 2: Restore inventory
        inventory.releaseRoom(booking.roomType, booking.roomId);

        // Step 3: Update booking state
        booking.isActive = false;
        bookingService.updateBooking(booking);

        System.out.println("Cancellation Successful → Booking ID: " + bookingId);
    }

    public void displayRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);
        CancellationService cancellationService = new CancellationService();

        try {
            // Create bookings
            bookingService.createBooking("B1", "Single");
            bookingService.createBooking("B2", "Double");

            // Cancel booking
            cancellationService.cancelBooking("B1", bookingService, inventory);

            // Invalid cancellation (already cancelled)
            cancellationService.cancelBooking("B1", bookingService, inventory);

        } catch (InvalidCancellationException e) {
            System.out.println("Cancellation Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        inventory.displayInventory();
        cancellationService.displayRollbackStack();
    }
}
