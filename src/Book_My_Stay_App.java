import java.util.*;

class Book_My_Stay_App {

    static class Service {
        String name;
        double cost;

        Service(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    static class AddOnServiceManager {

        Map<String, List<Service>> reservationServices = new HashMap<>();

        void addService(String reservationId, Service service) {
            reservationServices.putIfAbsent(reservationId, new ArrayList<>());
            reservationServices.get(reservationId).add(service);
        }

        double calculateTotalCost(String reservationId) {
            double total = 0;

            if (reservationServices.containsKey(reservationId)) {
                for (Service s : reservationServices.get(reservationId)) {
                    total += s.cost;
                }
            }

            return total;
        }

        void displayServices(String reservationId) {

            if (!reservationServices.containsKey(reservationId)) {
                System.out.println("No services added.");
                return;
            }

            for (Service s : reservationServices.get(reservationId)) {
                System.out.println(s.name + " - " + s.cost);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AddOnServiceManager manager = new AddOnServiceManager();

        System.out.print("Enter Reservation ID: ");
        String reservationId = sc.nextLine();

        System.out.print("Enter number of services: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter service name: ");
            String name = sc.nextLine();

            System.out.print("Enter service cost: ");
            double cost = sc.nextDouble();
            sc.nextLine();

            Service service = new Service(name, cost);
            manager.addService(reservationId, service);
        }

        System.out.println("\nSelected Services:");
        manager.displayServices(reservationId);

        double total = manager.calculateTotalCost(reservationId);

        System.out.println("Total Add-On Cost: " + total);
    }
}