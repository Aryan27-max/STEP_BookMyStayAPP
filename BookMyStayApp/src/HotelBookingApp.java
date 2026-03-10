import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management
 * using a HashMap to store room availability.
 *
 * @author Student
 * @version 3.1
 */
public class HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        Book My Stay App         ");
        System.out.println("    Hotel Booking System v3.1    ");
        System.out.println("=================================");

        // Create room objects (domain model)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        System.out.println("\n--- Room Details ---\n");

        singleRoom.displayRoomDetails();
        System.out.println("-----------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("-----------------------------------");

        suiteRoom.displayRoomDetails();
        System.out.println("-----------------------------------");

        System.out.println("\n--- Current Room Inventory ---");
        inventory.displayInventory();

        System.out.println("\nApplication execution completed.");
    }
}


/**
 * Abstract Room class representing common attributes
 * of all room types.
 *
 * @version 3.0
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

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + roomSize + " sq.ft");
        System.out.println("Price per Night: $" + pricePerNight);
    }
}


/**
 * Concrete class representing Single Room
 *
 * @version 3.0
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }
}


/**
 * Concrete class representing Double Room
 *
 * @version 3.0
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 180);
    }
}


/**
 * Concrete class representing Suite Room
 *
 * @version 3.0
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500, 300);
    }
}


/**
 * RoomInventory
 *
 * Manages centralized room availability using HashMap.
 * Acts as the single source of truth for inventory state.
 *
 * @version 3.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    /**
     * Constructor initializes room availability
     */
    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    /**
     * Retrieve availability for a room type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Update availability for a room type
     */
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    /**
     * Display complete inventory
     */
    public void displayInventory() {

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}
