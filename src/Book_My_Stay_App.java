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
        System.out.println("           Version 4.0");
        System.out.println("=================================\n");


        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();


        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(single.name, 5);
        inventory.registerRoom(doubleRoom.name, 3);
        inventory.registerRoom(suite.name, 0); // Suite currently unavailable


        System.out.println("Available Rooms for Guests:\n");

        if (inventory.getAvailability(single.name) > 0) {
            single.displayDetails();
            System.out.println("Available: " + inventory.getAvailability(single.name) + "\n");
        }

        if (inventory.getAvailability(doubleRoom.name) > 0) {
            doubleRoom.displayDetails();
            System.out.println("Available: " + inventory.getAvailability(doubleRoom.name) + "\n");
        }

        if (inventory.getAvailability(suite.name) > 0) {
            suite.displayDetails();
            System.out.println("Available: " + inventory.getAvailability(suite.name) + "\n");
        }

        System.out.println("Room Search Complete. Inventory remains unchanged.");
    }
}