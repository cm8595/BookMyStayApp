/**
 * Author: Monarch
 * Project: Book My Stay App
 * Use Case 12: Data Persistence & System Recovery
 */

import java.io.*;
import java.util.*;

// Booking Model (Serializable)
class Booking implements Serializable {
    String bookingId;
    String roomType;

    public Booking(String bookingId, String roomType) {
        this.bookingId = bookingId;
        this.roomType = roomType;
    }
}

// System State (Serializable)
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    Map<String, Booking> bookings;

    public SystemState(Map<String, Integer> inventory, Map<String, Booking> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Inventory Manager
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
    }

    public void loadState(Map<String, Integer> savedInventory) {
        inventory = savedInventory;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public boolean allocateRoom(String type) {
        if (!inventory.containsKey(type)) return false;

        int available = inventory.get(type);
        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }

    public void displayInventory() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// Booking Service
class BookingService {
    private Map<String, Booking> bookings = new HashMap<>();

    public void loadState(Map<String, Booking> savedBookings) {
        bookings = savedBookings;
    }

    public Map<String, Booking> getBookings() {
        return bookings;
    }

    public void createBooking(String id, String type, RoomInventory inventory) {
        if (inventory.allocateRoom(type)) {
            bookings.put(id, new Booking(id, type));
            System.out.println("Booking successful → " + id);
        } else {
            System.out.println("Booking failed → No availability");
        }
    }

    public void displayBookings() {
        System.out.println("\nBookings:");
        for (Booking b : bookings.values()) {
            System.out.println(b.bookingId + " → " + b.roomType);
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // Save State
    public static void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("\nSystem state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load State
    public static SystemState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("System state restored successfully.");
            return (SystemState) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous state found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Corrupted data. Starting fresh.");
        }
        return null;
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService();

        // Load previous state
        SystemState savedState = PersistenceService.load();

        if (savedState != null) {
            inventory.loadState(savedState.inventory);
            bookingService.loadState(savedState.bookings);
        }

        // Simulate bookings
        bookingService.createBooking("B1", "Single", inventory);
        bookingService.createBooking("B2", "Double", inventory);

        inventory.displayInventory();
        bookingService.displayBookings();

        // Save state before shutdown
        SystemState currentState = new SystemState(
                inventory.getInventory(),
                bookingService.getBookings()
        );

        PersistenceService.save(currentState);
    }
}