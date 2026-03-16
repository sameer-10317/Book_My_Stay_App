import java.util.*;

class Book_My_Stay_App {

    static class Reservation {
        String reservationId;
        String guestName;
        String roomType;
        String roomId;
        boolean cancelled = false;

        Reservation(String reservationId, String guestName, String roomType, String roomId) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
            this.roomId = roomId;
        }
    }

    static class InventoryManager {

        Map<String, Integer> inventory = new HashMap<>();

        InventoryManager() {
            inventory.put("Standard", 2);
            inventory.put("Deluxe", 2);
            inventory.put("Suite", 1);
        }

        void restoreRoom(String roomType) {
            inventory.put(roomType, inventory.get(roomType) + 1);
        }

        void showInventory() {
            System.out.println("\nCurrent Inventory:");
            for (String type : inventory.keySet()) {
                System.out.println(type + " : " + inventory.get(type));
            }
        }
    }

    static class BookingHistory {

        Map<String, Reservation> bookings = new HashMap<>();

        void addReservation(Reservation r) {
            bookings.put(r.reservationId, r);
        }

        Reservation getReservation(String id) {
            return bookings.get(id);
        }
    }

    static class CancellationService {

        Stack<String> rollbackStack = new Stack<>();

        void cancelBooking(String reservationId,
                           BookingHistory history,
                           InventoryManager inventory) {

            Reservation r = history.getReservation(reservationId);

            if (r == null) {
                System.out.println("Cancellation Failed: Reservation does not exist.");
                return;
            }

            if (r.cancelled) {
                System.out.println("Cancellation Failed: Booking already cancelled.");
                return;
            }

            rollbackStack.push(r.roomId);

            inventory.restoreRoom(r.roomType);

            r.cancelled = true;

            System.out.println("Booking cancelled successfully for Reservation ID: " + reservationId);
        }

        void showRollbackStack() {
            System.out.println("\nRollback Stack (Released Room IDs): " + rollbackStack);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        InventoryManager inventory = new InventoryManager();
        BookingHistory history = new BookingHistory();
        CancellationService cancelService = new CancellationService();

        System.out.print("Enter Reservation ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Guest Name: ");
        String guest = sc.nextLine();

        System.out.print("Enter Room Type: ");
        String roomType = sc.nextLine();

        System.out.print("Enter Allocated Room ID: ");
        String roomId = sc.nextLine();

        Reservation reservation = new Reservation(id, guest, roomType, roomId);
        history.addReservation(reservation);

        System.out.println("Booking confirmed for " + guest);

        System.out.print("\nEnter Reservation ID to cancel: ");
        String cancelId = sc.nextLine();

        cancelService.cancelBooking(cancelId, history, inventory);

        cancelService.showRollbackStack();

        inventory.showInventory();
    }
}