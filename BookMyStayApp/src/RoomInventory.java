import java.util.*;

/**
 * RoomInventory
 *
 * Manages the inventory of available rooms by type.
 */
public class RoomInventory {

    private final Map<String, Integer> roomCounts;

    public RoomInventory() {
        roomCounts = new HashMap<>();
    }

    /**
     * Add rooms to inventory
     */
    public void addRooms(String roomType, int count) {
        roomCounts.put(roomType, roomCounts.getOrDefault(roomType, 0) + count);
    }

    /**
     * Check if rooms are available for a given type
     */
    public boolean isAvailable(String roomType) {
        return roomCounts.getOrDefault(roomType, 0) > 0;
    }

    /**
     * Decrement room count for a given type
     */
    public void decrement(String roomType) {
        if (isAvailable(roomType)) {
            roomCounts.put(roomType, roomCounts.get(roomType) - 1);
        }
    }

    /**
     * Get available count for a room type
     */
    public int getAvailableCount(String roomType) {
        return roomCounts.getOrDefault(roomType, 0);
    }
}
