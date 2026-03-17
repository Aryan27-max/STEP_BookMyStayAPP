/**
 * Reservation
 *
 * Represents a guest's booking request.
 *
 * @version 5.0
 */
public class Reservation {

    private final String guestName;
    private final String requestedRoomType;

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
