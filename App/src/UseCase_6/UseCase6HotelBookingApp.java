package UseCase_6;

import java.util.*;

/**
 * MAIN CLASS - UseCase6HotelBookingApp
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class simulates confirming booking requests from a queue,
 * assigning rooms safely, and updating inventory immediately
 * while preventing double-booking.
 *
 * Key Concepts:
 * - FIFO booking request processing
 * - Unique room IDs using Set
 * - Room allocation tracked per room type
 * - Inventory decremented upon successful allocation
 *
 * @author Chaitanya Bhargav Malakonda
 * @version 6.0
 */
public class UseCase6HotelBookingApp {

    /**
     * Represents a guest's booking request.
     */
    static class Reservation {
        private String guestName;
        private String roomType;
        private int nights;

        public Reservation(String guestName, String roomType, int nights) {
            this.guestName = guestName;
            this.roomType = roomType;
            this.nights = nights;
        }

        public String getGuestName() { return guestName; }
        public String getRoomType() { return roomType; }
        public int getNights() { return nights; }

        @Override
        public String toString() {
            return "Reservation{" +
                    "guestName='" + guestName + '\'' +
                    ", roomType='" + roomType + '\'' +
                    ", nights=" + nights +
                    '}';
        }
    }

    /**
     * Room inventory service for managing availability counts.
     */
    static class RoomInventory {
        private Map<String, Integer> availability;

        public RoomInventory() {
            availability = new HashMap<>();
        }

        public void addRoomType(String roomType, int count) {
            availability.put(roomType, count);
        }

        public boolean isAvailable(String roomType) {
            return availability.getOrDefault(roomType, 0) > 0;
        }

        public void decrement(String roomType) {
            availability.put(roomType, availability.get(roomType) - 1);
        }

        public int getAvailability(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }

        public void displayInventory() {
            System.out.println("Current Inventory:");
            for (Map.Entry<String, Integer> entry : availability.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    /**
     * Booking service to process reservations and allocate rooms.
     */
    static class BookingService {
        private Queue<Reservation> requestQueue;
        private RoomInventory inventory;
        private Map<String, Set<String>> allocatedRooms;

        public BookingService(Queue<Reservation> requestQueue, RoomInventory inventory) {
            this.requestQueue = requestQueue;
            this.inventory = inventory;
            this.allocatedRooms = new HashMap<>();
        }

        /**
         * Confirms bookings in FIFO order.
         */
        public void confirmBookings() {
            while (!requestQueue.isEmpty()) {
                Reservation reservation = requestQueue.poll();
                String roomType = reservation.getRoomType();

                if (inventory.isAvailable(roomType)) {
                    // Generate unique room ID
                    String roomID = roomType + "_" + UUID.randomUUID().toString().substring(0, 8);

                    allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                    allocatedRooms.get(roomType).add(roomID);

                    inventory.decrement(roomType);

                    System.out.println("Confirmed " + reservation.getGuestName() +
                            " for " + roomType + " -> Room ID: " + roomID);
                } else {
                    System.out.println("No available rooms for " + reservation.getGuestName() +
                            " (" + roomType + ")");
                }
            }
        }

        /**
         * Display all allocated rooms grouped by type.
         */
        public void displayAllocatedRooms() {
            System.out.println("\nAllocated Rooms:");
            for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("SingleRoom", 2);
        inventory.addRoomType("DoubleRoom", 1);
        inventory.addRoomType("SuiteRoom", 1);

        // Prepare booking requests
        Queue<Reservation> requests = new LinkedList<>();
        requests.add(new Reservation("Alice", "SingleRoom", 2));
        requests.add(new Reservation("Bob", "SingleRoom", 1));
        requests.add(new Reservation("Charlie", "DoubleRoom", 3));
        requests.add(new Reservation("David", "SuiteRoom", 2));
        requests.add(new Reservation("Eve", "SingleRoom", 1)); // Will exceed availability

        // Confirm bookings
        BookingService bookingService = new BookingService(requests, inventory);
        bookingService.confirmBookings();

        // Display final state
        bookingService.displayAllocatedRooms();
        inventory.displayInventory();
    }
}