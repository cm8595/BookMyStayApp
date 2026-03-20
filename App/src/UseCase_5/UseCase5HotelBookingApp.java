package UseCase_5;
import java.util.LinkedList;
import java.util.Queue;

/**
 * MAIN CLASS - UseCase5HotelBookingApp
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Description:
 * This class simulates the intake of booking requests in a
 * hotel booking management system.
 *
 * At this stage:
 * - Requests are stored in a queue in arrival order.
 * - FIFO (First-Come-First-Served) principle ensures fairness.
 * - No inventory updates are performed here.
 *
 * The goal is to handle booking requests fairly before allocation.
 *
 * @author Chaitanya Bhargav Malakonda
 * @version 5.0
 */
public class UseCase5HotelBookingApp {

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

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }

        public int getNights() {
            return nights;
        }

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
     * Queue to hold booking requests in arrival order.
     */
    private Queue<Reservation> bookingQueue;

    /**
     * Constructor initializes the queue.
     */
    public UseCase5HotelBookingApp() {
        bookingQueue = new LinkedList<>();
    }

    /**
     * Adds a new booking request to the queue.
     *
     * @param reservation Reservation object representing a booking request
     */
    public void addBookingRequest(Reservation reservation) {
        bookingQueue.add(reservation);
        System.out.println("Added booking request: " + reservation);
    }

    /**
     * Processes booking requests in FIFO order for demonstration.
     * In a real system, allocation would happen separately.
     */
    public void processRequests() {
        System.out.println("\nProcessing booking requests in arrival order:");
        while (!bookingQueue.isEmpty()) {
            Reservation current = bookingQueue.poll();
            System.out.println("Processing request: " + current);
        }
    }

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        UseCase5HotelBookingApp app = new UseCase5HotelBookingApp();

        // Simulate incoming booking requests
        app.addBookingRequest(new Reservation("Alice", "SingleRoom", 2));
        app.addBookingRequest(new Reservation("Bob", "DoubleRoom", 3));
        app.addBookingRequest(new Reservation("Charlie", "SuiteRoom", 1));

        // Process requests (FIFO)
        app.processRequests();
    }
}