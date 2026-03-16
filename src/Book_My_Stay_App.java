import java.util.*;

class Book_My_Stay_App {

    static class BookingRequest {
        String guestName;
        String roomType;

        BookingRequest(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    static class InventoryManager {

        private Map<String, Integer> inventory = new HashMap<>();

        InventoryManager() {
            inventory.put("Standard", 2);
            inventory.put("Deluxe", 1);
            inventory.put("Suite", 1);
        }

        public synchronized boolean allocateRoom(String roomType, String guestName) {

            int available = inventory.getOrDefault(roomType, 0);

            if (available > 0) {
                inventory.put(roomType, available - 1);
                System.out.println(Thread.currentThread().getName() +
                        " allocated " + roomType + " room to " + guestName);
                return true;
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " failed to allocate " + roomType + " room for " + guestName);
                return false;
            }
        }

        void showInventory() {
            System.out.println("\nFinal Inventory State:");
            for (String type : inventory.keySet()) {
                System.out.println(type + " : " + inventory.get(type));
            }
        }
    }

    static class BookingQueue {

        private Queue<BookingRequest> queue = new LinkedList<>();

        synchronized void addRequest(BookingRequest req) {
            queue.add(req);
        }

        synchronized BookingRequest getRequest() {
            return queue.poll();
        }
    }

    static class BookingProcessor extends Thread {

        BookingQueue queue;
        InventoryManager inventory;

        BookingProcessor(String name, BookingQueue queue, InventoryManager inventory) {
            super(name);
            this.queue = queue;
            this.inventory = inventory;
        }

        public void run() {

            while (true) {

                BookingRequest req;

                synchronized (queue) {
                    req = queue.getRequest();
                }

                if (req == null)
                    break;

                inventory.allocateRoom(req.roomType, req.guestName);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();
        InventoryManager inventory = new InventoryManager();

        queue.addRequest(new BookingRequest("Alice", "Standard"));
        queue.addRequest(new BookingRequest("Bob", "Standard"));
        queue.addRequest(new BookingRequest("Charlie", "Standard"));
        queue.addRequest(new BookingRequest("David", "Suite"));
        queue.addRequest(new BookingRequest("Eve", "Deluxe"));

        BookingProcessor t1 = new BookingProcessor("Thread-1", queue, inventory);
        BookingProcessor t2 = new BookingProcessor("Thread-2", queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
        }

        inventory.showInventory();
    }
}