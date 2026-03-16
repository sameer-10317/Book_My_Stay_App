import java.io.*;
import java.util.*;

class Book_My_Stay_App {

    static class Reservation implements Serializable {
        String reservationId;
        String guestName;
        String roomType;

        Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    static class SystemState implements Serializable {
        List<Reservation> bookingHistory = new ArrayList<>();
        Map<String, Integer> inventory = new HashMap<>();
    }

    static class PersistenceService {

        static void saveState(SystemState state, String fileName) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
                out.writeObject(state);
                out.close();
                System.out.println("System state saved successfully.");
            } catch (Exception e) {
                System.out.println("Error saving system state.");
            }
        }

        static SystemState loadState(String fileName) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
                SystemState state = (SystemState) in.readObject();
                in.close();
                System.out.println("System state restored successfully.");
                return state;
            } catch (Exception e) {
                System.out.println("No previous state found. Starting fresh.");
                return new SystemState();
            }
        }
    }

    public static void main(String[] args) {

        String fileName = "system_state.dat";

        SystemState state = PersistenceService.loadState(fileName);

        if (state.inventory.isEmpty()) {
            state.inventory.put("Standard", 2);
            state.inventory.put("Deluxe", 1);
            state.inventory.put("Suite", 1);
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Reservation ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Guest Name: ");
        String guest = sc.nextLine();

        System.out.print("Enter Room Type: ");
        String room = sc.nextLine();

        Reservation r = new Reservation(id, guest, room);
        state.bookingHistory.add(r);

        if (state.inventory.containsKey(room)) {
            int count = state.inventory.get(room);
            if (count > 0) {
                state.inventory.put(room, count - 1);
                System.out.println("Booking confirmed for " + guest);
            } else {
                System.out.println("No rooms available for " + room);
            }
        } else {
            System.out.println("Invalid room type.");
        }

        System.out.println("\nCurrent Booking History:");
        for (Reservation res : state.bookingHistory) {
            System.out.println(res.reservationId + " - " + res.guestName + " - " + res.roomType);
        }

        System.out.println("\nCurrent Inventory:");
        for (String type : state.inventory.keySet()) {
            System.out.println(type + " : " + state.inventory.get(type));
        }

        PersistenceService.saveState(state, fileName);
    }
}