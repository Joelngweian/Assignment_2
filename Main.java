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

    // 1. Add Order [cite: 23]
    public static void addOrder() {
        System.out.print("Enter Type (R for Regular, P for Premium): ");
        char type = scanner.next().toUpperCase().charAt(0);

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

    // 4. Display Sorted [cite: 26]
    public static void displaySortedOrders() {
        System.out.println("Sort by: 1. CustomerID  2. Shipping Method");
        int sortType = scanner.nextInt();

        if (sortType == 1) {
            Collections.sort(orderList); // 使用 Comparable 默认排序
        } else {
            // 使用 Comparator 按 Shipping Method 排序
            orderList.sort(Comparator.comparing(Customer::getShippingMethod));
        }

        // 打印表头
        System.out.println("ID         Name            Qty        Method                    Total");
        for (Customer c : orderList) {
            System.out.println(c.toString());
        }
    }
}