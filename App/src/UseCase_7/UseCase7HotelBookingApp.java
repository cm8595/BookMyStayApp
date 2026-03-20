package UseCase_7;

import java.util.*;

/**
 * MAIN CLASS - UseCase7HotelBookingApp
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class simulates adding optional services to existing reservations
 * without modifying core booking or inventory logic.
 *
 * Key Concepts:
 * - Map from reservation ID to List of selected services
 * - Flexible one-to-many association between reservations and services
 * - Composition over inheritance for service extension
 * - Cost aggregation for add-on features
 *
 * @version 7.0
 */
public class UseCase7HotelBookingApp {

    /**
     * Represents a guest's booking with a unique reservation ID.
     */
    static class Reservation {
        private String reservationID;
        private String guestName;
        private String roomType;

        public Reservation(String reservationID, String guestName, String roomType) {
            this.reservationID = reservationID;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getReservationID() { return reservationID; }
        public String getGuestName() { return guestName; }
        public String getRoomType() { return roomType; }

        @Override
        public String toString() {
            return "Reservation{" +
                    "reservationID='" + reservationID + '\'' +
                    ", guestName='" + guestName + '\'' +
                    ", roomType='" + roomType + '\'' +
                    '}';
        }
    }

    /**
     * Represents an add-on service with name and cost.
     */
    static class Service {
        private String name;
        private double cost;

        public Service(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        public String getName() { return name; }
        public double getCost() { return cost; }

        @Override
        public String toString() {
            return name + " ($" + cost + ")";
        }
    }

    /**
     * Manages mapping between reservations and selected add-on services.
     */
    static class AddOnServiceManager {
        private Map<String, List<Service>> reservationServices = new HashMap<>();

        /**
         * Adds a service to a reservation.
         */
        public void addService(String reservationID, Service service) {
            reservationServices.putIfAbsent(reservationID, new ArrayList<>());
            reservationServices.get(reservationID).add(service);
        }

        /**
         * Calculates total additional cost for a reservation.
         */
        public double calculateTotalCost(String reservationID) {
            List<Service> services = reservationServices.getOrDefault(reservationID, Collections.emptyList());
            return services.stream().mapToDouble(Service::getCost).sum();
        }

        /**
         * Displays all add-on services per reservation.
         */
        public void displayServices() {
            System.out.println("\nAdd-On Services by Reservation:");
            for (Map.Entry<String, List<Service>> entry : reservationServices.entrySet()) {
                System.out.println("Reservation " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        // Sample reservations
        Reservation r1 = new Reservation("R001", "Alice", "SingleRoom");
        Reservation r2 = new Reservation("R002", "Bob", "DoubleRoom");

        // Available services
        Service breakfast = new Service("Breakfast", 15.0);
        Service airportPickup = new Service("Airport Pickup", 30.0);
        Service spaPackage = new Service("Spa Package", 50.0);

        // Manage add-on services
        AddOnServiceManager manager = new AddOnServiceManager();

        // Alice selects Breakfast and Spa
        manager.addService(r1.getReservationID(), breakfast);
        manager.addService(r1.getReservationID(), spaPackage);

        // Bob selects Airport Pickup
        manager.addService(r2.getReservationID(), airportPickup);

        // Display all selected services
        manager.displayServices();

        // Show total additional cost
        System.out.println("\nTotal Additional Cost:");
        System.out.println(r1.getGuestName() + ": $" + manager.calculateTotalCost(r1.getReservationID()));
        System.out.println(r2.getGuestName() + ": $" + manager.calculateTotalCost(r2.getReservationID()));
    }
}
