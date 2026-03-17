import java.util.*;

public class RoomAllocationService {

    // Stores all allocated room IDs (global uniqueness)
    private final Set<String> allocatedRoomIds;

    // Stores room IDs by room type
    private final Map<String, Set<String>> assignedRoomsByType;

    // Constructor
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    // Allocate room
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRequestedRoomType();

        // Check availability
        if (!inventory.isAvailable(roomType)) {
            System.out.println("No rooms available for type: " + roomType);
            return;
        }

        // Generate unique room ID
        String roomId = generateRoomId(roomType);

        // Store globally
        allocatedRoomIds.add(roomId);

        // Store by type
        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        // Update inventory
        inventory.decrement(roomType);

        // Print confirmation
        System.out.println("Booking confirmed for Guest: "
                + reservation.getGuestName()
                + ", Room ID: " + roomId);
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        return roomType + "-" + count;
    }
}