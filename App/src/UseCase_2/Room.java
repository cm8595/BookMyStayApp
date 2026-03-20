package UseCase_2;
/**
 * ABSTRACT CLASS - Room
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This abstract class represents a generic hotel room.
 *
 * It models attributes that are intrinsic to a room type
 * and remain constant regardless of availability.
 *
 * Inventory-related concerns are intentionally excluded.
 *
 * @version 2.1
 */
public abstract class Room {

    /** Number of beds available in the room. */
    protected int numberOfBeds;

    /** Total size of the room in square feet. */
    protected int squareFeet;

    /** Price charged per night for this room type. */
    protected double pricePerNight;

    /**
     * Constructor used by child classes to
     * initialize common room attributes.
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

    /** Displays room details to the console. */
    public void displayRoomDetails() {
        System.out.println("Room Type: " + this.getClass().getSimpleName());
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Size (sq ft): " + squareFeet);
        System.out.println("Price per Night: $" + pricePerNight);
        System.out.println("-----------------------------------");
    }
}
