import java.util.LinkedList;
import java.util.Queue;

/**
 * HotelBookingApp
 *
 * Demonstrates booking request intake using FIFO queue
 * to handle multiple guest requests fairly.
 *
 * @version 5.1
 */
public class HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        Book My Stay App         ");
        System.out.println("    Hotel Booking System v5.1    ");
        System.out.println("=================================");

        // Initialize the booking request queue
        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Sample guest booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");
        Reservation r4 = new Reservation("Diana", "Double Room");

        // Submit requests (added in arrival order)
        requestQueue.submitRequest(r1);
        requestQueue.submitRequest(r2);
        requestQueue.submitRequest(r3);
        requestQueue.submitRequest(r4);

        // Display queued requests
        System.out.println("\n--- Booking Requests Queue (FIFO) ---\n");
        requestQueue.displayQueue();

        System.out.println("\nBooking request intake completed successfully.");
    }
}


/**
 * Reservation
 *
 * Represents a guest's booking request.
 *
 * @version 5.0
 */
public class Reservation {

    private String guestName;
    private String requestedRoomType;

    public Reservation(String guestName, String requestedRoomType) {
        this.guestName = guestName;
        this.requestedRoomType = requestedRoomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRequestedRoomType() {
        return requestedRoomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + requestedRoomType);
    }
}


/**
 * BookingRequestQueue
 *
 * Manages booking requests in arrival order (FIFO).
 *
 * @version 5.0
 */
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Submit a new booking request
     */
    public void submitRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Received booking request from " + reservation.getGuestName() +
                " for " + reservation.getRequestedRoomType());
    }

    /**
     * Display all queued booking requests in FIFO order
     */
    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("No booking requests in the queue.");
            return;
        }

        for (Reservation r : queue) {
            r.displayReservation();
        }
    }

    /**
     * Retrieve and remove the next request (optional for allocation stage)
     */
    public Reservation pollNextRequest() {
        return queue.poll();
    }

    /**
     * Peek at the next request without removing
     */
    public Reservation peekNextRequest() {
        return queue.peek();
    }

    /**
     * Check if the queue is empty
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}