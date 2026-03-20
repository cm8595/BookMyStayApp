package UseCase__2;
/**
 * MAIN CLASS - UseCase2HotelBookingApp
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This class demonstrates room types and static availability.
 * Room objects are created and their details are displayed.
 *
 * @author Chaitanya Bhargav Malakonda
 * @version 2.1
 */
public class UseCase2HotelBookingApp {

    public static void main(String[] args) {

        // Static availability
        int singleRoomsAvailable = 5;
        int doubleRoomsAvailable = 3;
        int suiteRoomsAvailable = 2;

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new doubleRoom();
        Room suite = new SuiteRoom();

        // Display room details and availability
        single.displayRoomDetails();
        System.out.println("Available: " + singleRoomsAvailable + "\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomsAvailable + "\n");

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteRoomsAvailable + "\n");
    }
}