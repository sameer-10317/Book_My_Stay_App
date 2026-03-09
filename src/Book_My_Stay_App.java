import java.util.HashMap;

abstract class Room {
    protected String name;
    protected int beds;
    protected double pricePerNight;

    public Room(String name, int beds, double pricePerNight) {
        this.name = name;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
    }

    public void displayDetails() {
        System.out.println(name + " | Beds: " + beds + " | Price per Night: $" + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 90.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 150.0);
    }
}


class RoomInventory {
    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
    }


    public void registerRoom(String roomType, int count) {
        availability.put(roomType, count);
    }


    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }


    public void updateAvailability(String roomType, int change) {
        int current = availability.getOrDefault(roomType, 0);
        availability.put(roomType, current + change);
    }


    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : availability.keySet()) {
            System.out.println(roomType + " : " + availability.get(roomType) + " available");
        }
    }
}

public class Book_My_Stay_App {
    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("      Hotel Booking System");
        System.out.println("           Version 3.1");
        System.out.println("=================================\n");


        Room single = new SingleRoom();