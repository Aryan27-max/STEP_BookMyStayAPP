import java.util.HashMap;
import java.util.Map;

/**
 * UseCase4RoomSearch
 *
 * Demonstrates room search functionality using centralized
 * inventory while ensuring read-only access to system state.
 *
 * @version 4.1
 */
public class HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        Book My Stay App         ");
        System.out.println("    Hotel Booking System v4.1    ");
        System.out.println("=================================");

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        System.out.println("\n--- Available Rooms ---\n");

        // Perform search (read-only)
        searchService.searchAvailableRooms(new Room[]{singleRoom, doubleRoom, suiteRoom});

        System.out.println("\nSearch completed successfully.");
    }
}


/**
 * Abstract Room class representing common room properties.
 *
 * @version 4.0
 */
abstract class Room {

    protected String roomType;
    protected int numberOfBeds;
    protected int roomSize;
    protected double pricePerNight;

    public Room(String roomType, int numberOfBeds, int roomSize, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.roomSize = roomSize;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + roomSize + " sq.ft");
        System.out.println("Price per Night: $" + pricePerNight);
    }
}


/**
 * Single Room implementation
 *
 * @version 4.0
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }
}


/**
 * Double Room implementation
 *
 * @version 4.0
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 180);
    }
}


/**
 * Suite Room implementation
 *
 * @version 4.0
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500, 300);
    }
}


/**
 * RoomInventory
 *
 * Centralized storage for room availability.
 * Uses HashMap for fast lookup and updates.
 *
 * @version 4.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // Example: suite currently unavailable
    }

    /**
     * Read-only method to get availability
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}


/**
 * RoomSearchService
 *
 * Handles read-only search operations for guests.
 *
 * @version 4.0
 */
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Displays only rooms with availability > 0
     */
    public void searchAvailableRooms(Room[] rooms) {

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {

                room.displayRoomDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println("-----------------------------------");

            }
        }
    }
}