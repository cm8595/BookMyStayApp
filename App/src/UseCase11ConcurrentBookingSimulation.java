/**
 * Author: Chaitanya Bhargav Malakonda
 * Project: Book My Stay App
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 */

import java.util.*;

// Booking Request Model
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Thread-safe Room Inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
    }

    // Critical Section (Thread Safe)
    public synchronized boolean allocateRoom(String roomType) {

        if (!inventory.containsKey(roomType)) {
            System.out.println("Invalid room type: " + roomType);
            return false;
        }

        int available = inventory.get(roomType);

        if (available > 0) {
            inventory.put(roomType, available - 1);

            System.out.println(Thread.currentThread().getName()
                    + " → Booking SUCCESS for " + roomType
                    + " | Remaining: " + (available - 1));

            return true;
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " → Booking FAILED for " + roomType + " (No rooms)");

            return false;
        }
    }

    public void displayInventory() {
        System.out.println("\nFinal Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// Shared Booking Queue
class BookingQueue {
    private Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.add(request);
        notifyAll(); // wake waiting threads
    }

    public synchronized BookingRequest getRequest() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
    }
}

// Worker Thread (Booking Processor)
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(String name, BookingQueue queue, RoomInventory inventory) {
        super(name);
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        for (int i = 0; i < 3; i++) { // process limited requests
            BookingRequest request = queue.getRequest();
            inventory.allocateRoom(request.roomType);

            try {
                Thread.sleep(500); // simulate processing delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Add booking requests (simulating multiple users)
        queue.addRequest(new BookingRequest("User1", "Single"));
        queue.addRequest(new BookingRequest("User2", "Single"));
        queue.addRequest(new BookingRequest("User3", "Single"));
        queue.addRequest(new BookingRequest("User4", "Double"));
        queue.addRequest(new BookingRequest("User5", "Double"));
        queue.addRequest(new BookingRequest("User6", "Double"));

        // Create multiple threads
        Thread t1 = new BookingProcessor("Thread-1", queue, inventory);
        Thread t2 = new BookingProcessor("Thread-2", queue, inventory);

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        inventory.displayInventory();
    }
}