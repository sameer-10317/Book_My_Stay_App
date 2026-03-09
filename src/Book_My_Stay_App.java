import java.util.Queue;
import java.util.LinkedList;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayRequest() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

public class Book_My_Stay_App {
    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("      Hotel Booking System");
        System.out.println("           Version 5.0");
        System.out.println("=================================\n");


        Queue<Reservation> bookingQueue = new LinkedList<>();


        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));
        bookingQueue.add(new Reservation("Diana", "Single Room"));

        System.out.println("Booking Requests Received (First-Come-First-Served):\n");


        for (Reservation request : bookingQueue) {
            request.displayRequest();
        }

        System.out.println("\nAll booking requests are stored in the queue and await processing.");
    }
}