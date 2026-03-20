package UseCase_4;
/**
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This application allows guests to view available rooms and their details
 * without modifying the system state. It demonstrates read-only access to
 * the inventory and separation of concerns.
 *
 * @version 4.0
 * @author Chaitanya Bhargav Malakonda
 */
import java.util.HashMap;
import java.util.Map;

// Abstract Room class
abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds +
                ", Size: " + squareFeet + " sqft" +
                ", Price: $" + pricePerNight);
    }
}

// Concrete room types
class SingleRoom extends Room {
    public SingleRoom() { super(1, 120, 50); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super(2, 200, 80); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super(3, 350, 150); }
}

// Centralized Room Inventory
class RoomInventory {
    private final Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("SingleRoom", 5);
        availability.put("DoubleRoom", 3);
        availability.put("SuiteRoom", 2);
    }

    public int getAvailableRooms(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }
}

// Guest Search Service - read-only access
class GuestSearchService {
    private final RoomInventory inventory;

    public GuestSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void showAvailableRooms(Map<String, Room> roomTypes) {
        System.out.println("Available Rooms:");
        for (String roomType : roomTypes.keySet()) {
            if (inventory.getAvailableRooms(roomType) > 0) {
                System.out.print(roomType + ": ");
                roomTypes.get(roomType).displayRoomDetails();
            }
        }
    }
}

// Main application entry
public class UseCase4HotelBookingApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        Map<String, Room> roomTypes = new HashMap<>();
        roomTypes.put("SingleRoom", new SingleRoom());
        roomTypes.put("DoubleRoom", new DoubleRoom());
        roomTypes.put("SuiteRoom", new SuiteRoom());

        GuestSearchService searchService = new GuestSearchService(inventory);
        searchService.showAvailableRooms(roomTypes);
    }
}