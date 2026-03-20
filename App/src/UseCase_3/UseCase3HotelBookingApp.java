import java.util.HashMap;
import java.util.Map;

/**
 * ABSTRACT CLASS - Room
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This class hierarchy represents hotel rooms.
 * Room characteristics (price, size, beds) remain part of the domain model.
 *
 * Inventory is no longer scattered as variables; it will be managed
 * separately in RoomInventory.
 *
 * @version 3.0
 */
abstract class Room {

    /** Number of beds available in the room. */
    protected int numberOfBeds;

    /** Total size of the room in square feet. */
    protected int squareFeet;

    /** Price charged per night for this room type. */
    protected double pricePerNight;

    /**
     * Constructor used by child classes to initialize common room attributes.
     *
     * @param numberOfBeds number of beds in the room
     * @param squareFeet total room size
     * @param pricePerNight cost per night
     */
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /** Displays room details. */
    public void displayRoomDetails() {
        System.out.println("Room Type: " + this.getClass().getSimpleName());
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size (sqft): " + squareFeet);
        System.out.println("Price per Night: $" + pricePerNight);
        System.out.println("----------------------------------");
    }
}

/**
 * Concrete room types extending Room
 */
class SingleRoom extends Room {
    public SingleRoom() { super(1, 120, 50.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super(2, 200, 90.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super(3, 350, 200.0); }
}

/**
 * RoomInventory - Manages centralized room availability using HashMap
 */

class RoomInventory {

    /** Maps room type names to available counts */
    private Map<String, Integer> inventory;

    /** Constructor initializes the inventory */
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    /** Adds a new room type with available count */
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    /** Retrieves current availability for a room type */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /** Updates availability for a room type */
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    /** Displays full inventory */
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " available");
        }
        System.out.println("----------------------------------");
    }
}

/**
 * MAIN CLASS - UseCase3HotelBookingApp
 *
 * Description:
 * Demonstrates centralized room inventory management using HashMap.
 *
 * @author Chaitanya Bhargav Malakonda
 * @version 3.0
 */
public class UseCase3HotelBookingApp {

    public static void main(String[] args) {

        // Create rooms
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details
        single.displayRoomDetails();
        doubleRoom.displayRoomDetails();
        suite.displayRoomDetails();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("SingleRoom", 10);
        inventory.addRoomType("DoubleRoom", 5);
        inventory.addRoomType("SuiteRoom", 2);

        // Display current inventory
        inventory.displayInventory();

        // Update inventory
        inventory.updateAvailability("SingleRoom", 9);
        inventory.updateAvailability("SuiteRoom", 1);

        // Display updated inventory
        inventory.displayInventory();
    }
}