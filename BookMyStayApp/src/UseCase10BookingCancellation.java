import java.util.*;

// ===============================================================
// ROOM INVENTORY
// ===============================================================
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void increaseRoom(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    public int getAvailableRooms(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// ===============================================================
// CANCELLATION SERVICE
// ===============================================================
class CancellationService {

    // Stack for rollback (LIFO)
    private Stack<String> releasedRoomIds;

    // Reservation → Room Type mapping
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    // Register booking (simulate confirmed booking)
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    // Cancel booking
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        // Validate reservation
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation. Reservation not found.");
            return;
        }

        // Get room type
        String roomType = reservationRoomTypeMap.get(reservationId);

        // Push to rollback stack
        releasedRoomIds.push(reservationId);

        // Restore inventory
        inventory.increaseRoom(roomType);

        // Remove booking
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    // Show rollback history
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
        }
    }
}

// ===============================================================
// MAIN CLASS
// ===============================================================
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        // Initialize
        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate booking
        service.registerBooking("Single-1", "Single");

        // Cancel booking
        service.cancelBooking("Single-1", inventory);

        // Show rollback
        service.showRollbackHistory();

        // Show updated inventory
        System.out.println("\nUpdated Single Room Availability: "
                + inventory.getAvailableRooms("Single"));
    }
}