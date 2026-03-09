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

public class Book_My_Stay_App {
    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("      Hotel Booking System");
        System.out.println("           Version 2.1");
        System.out.println("=================================\n");


        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();


        int availableSingleRooms = 5;
        int availableDoubleRooms = 3;
        int availableSuites = 2;


        single.displayDetails();
        System.out.println("Available: " + availableSingleRooms + "\n");

        doubleRoom.displayDetails();
        System.out.println("Available: " + availableDoubleRooms + "\n");

        suite.displayDetails();
        System.out.println("Available: " + availableSuites + "\n");

        System.out.println("Room Initialization Complete.");
    }
}
