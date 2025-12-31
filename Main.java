import java.util.*;

public class Main {
    static ArrayList<Customer> orderList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- TheZone Golf Ball System ---");
            System.out.println("1. Add an order");
            System.out.println("2. Remove an order");
            System.out.println("3. Find an order");
            System.out.println("4. Display sorted orders");
            System.out.println("5. Print orders (Filter by Type/Method)");
            System.out.println("6. Quit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: addOrder(); break;
                case 2: removeOrder(); break;
                case 3: findOrder(); break;
                case 4: displaySortedOrders(); break;
                case 5: printSpecificOrders(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid input!");
            }
        } while (choice != 6);
    }

    // 1. Add Order
    public static void addOrder() {
        System.out.print("Enter Type (R for Regular, P for Premium): ");
        String input = scanner.next();
        if (input.isEmpty()) return;
        char type = input.toUpperCase().charAt(0);

        System.out.print("Enter Customer ID (5 chars): ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Quantity (>0): ");
        int qty = scanner.nextInt();

        if (qty <= 0) {
            System.out.println("Error: Quantity must be > 0");
            return;
        }

        if (type == 'R') {
            orderList.add(new RegularCustomer(id, name, qty));
        } else if (type == 'P') {
            orderList.add(new PremiumCustomer(id, name, qty));
        } else {
            System.out.println("Invalid Customer Type!");
        }
    }

    // 2. Remove Order (Fixed: Moved outside addOrder and made static)
    public static void removeOrder() {
        System.out.print("Enter Customer ID to remove: ");
        String id = scanner.next();

        // Java 8 approach to remove if ID matches
        boolean removed = orderList.removeIf(c -> c.getCustomerID().equalsIgnoreCase(id));

        if (removed) {
            System.out.println("Order removed successfully.");
        } else {
            System.out.println("Customer ID not found.");
        }
    }

    // 3. Find Order (Added this as it was missing)
    public static void findOrder() {
        System.out.print("Enter Customer ID to search: ");
        String id = scanner.next();
        boolean found = false;

        for (Customer c : orderList) {
            if (c.getCustomerID().equalsIgnoreCase(id)) {
                System.out.println("Order Found:");
                System.out.println(c.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Customer ID not found.");
        }
    }

    // 4. Display Sorted
    public static void displaySortedOrders() {
        System.out.println("Sort by: 1. CustomerID  2. Shipping Method");
        int sortType = scanner.nextInt();

        if (sortType == 1) {
            Collections.sort(orderList);
        } else {
            orderList.sort(Comparator.comparing(Customer::getShippingMethod));
        }

        System.out.println("ID         Name            Qty        Method                    Total");
        for (Customer c : orderList) {
            System.out.println(c.toString());
        }
    }

    // 5. Print Specific Orders (Added this as it was missing)
    public static void printSpecificOrders() {
        System.out.println("Filter by: 1. Regular  2. Premium");
        int filterChoice = scanner.nextInt();
        boolean foundAny = false;

        for (Customer c : orderList) {
            if ((filterChoice == 1 && c instanceof RegularCustomer) ||
                    (filterChoice == 2 && c instanceof PremiumCustomer)) {
                System.out.println(c.toString());
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("No records found for this type.");
        }
    }
}