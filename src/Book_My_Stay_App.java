import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private HashMap<String, Integer> availability;
    private HashMap<String, Set<String>> allocatedRooms;

    public RoomInventory() {
        availability = new HashMap<>();
        allocatedRooms = new HashMap<>();
    }

    public void registerRoom(String roomType, int count) {
        availability.put(roomType, count);
        allocatedRooms.put(roomType, new HashSet<>());
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }


    public String allocateRoom(String roomType) {
        int available = getAvailability(roomType);
        if (available <= 0) {
            return null; // No rooms available
        }
        Set<String> assigned = allocatedRooms.get(roomType);
        String roomID = roomType.substring(0, 2).toUpperCase() + (assigned.size() + 1);
        assigned.add(roomID);
        availability.put(roomType, available - 1); // Decrement inventory
        return roomID;
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : availability.keySet()) {
            System.out.println(roomType + " : " + availability.get(roomType) + " available | Allocated IDs: " + allocatedRooms.get(roomType));
        }
    }
}

public class Book_My_Stay_App {
    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("      Hotel Booking System");
        System.out.println("           Version 6.0");
        System.out.println("=================================\n");


        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Single Room", 5);
        inventory.registerRoom("Double Room", 3);
        inventory.registerRoom("Suite Room", 2);


        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));
        bookingQueue.add(new Reservation("Diana", "Single Room"));
        bookingQueue.add(new Reservation("Eve", "Suite Room")); // Last suite

        System.out.println("Processing Booking Requests:\n");

        while (!bookingQueue.isEmpty()) {
            Reservation request = bookingQueue.poll(); // FIFO
            String roomID = inventory.allocateRoom(request.roomType);

            if (roomID != null) {
                System.out.println("Reservation Confirmed: Guest: " + request.guestName + " | Room: " + request.roomType + " | Room ID: " + roomID);
            } else {
                System.out.println("Reservation Failed: Guest: " + request.guestName + " | Room: " + request.roomType + " (No availability)");
            }
        }

        System.out.println("\nFinal Inventory State:\n");
        inventory.displayInventory();
    }
}