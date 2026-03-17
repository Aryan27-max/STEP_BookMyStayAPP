import java.util.*;

// ===============================================================
// CUSTOM EXCEPTION
// ===============================================================
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

// ===============================================================
// INVENTORY CLASS
// ===============================================================
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }
}

// ===============================================================
// VALIDATOR CLASS
// ===============================================================
class ReservationValidator {

    public void validate(String guestName,
                         String roomType,
                         RoomInventory inventory)
            throws InvalidBookingException {

        // Validate guest name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate room type (CASE SENSITIVE as per question)
        if (!(roomType.equals("Single") ||
              roomType.equals("Double") ||
              roomType.equals("Suite"))) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Check availability
        if (!inventory.isAvailable(roomType)) {
            throw new InvalidBookingException("No rooms available for selected type.");
        }
    }
}

// ===============================================================
// DUMMY QUEUE CLASS (for completeness)
// ===============================================================
class BookingRequestQueue {
    // Not used heavily in this UC, placeholder
}

// ===============================================================
// MAIN CLASS
// ===============================================================
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        // Display header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            // Take input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(guestName, roomType, inventory);

            // If no exception → success
            System.out.println("Booking validated successfully!");

        } catch (InvalidBookingException e) {

            // Handle validation error
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}