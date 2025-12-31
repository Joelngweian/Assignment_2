public class Customer implements Comparable<Customer> { // 1. 实现 Comparable 接口
    protected String customerID;
    protected String customerName;
    protected int quantity;
    protected String shippingMethod;

    // 构造函数
    public Customer(String customerID, String customerName, int quantity) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.quantity = quantity;
        determineShippingMethod();
    }

    // 为了配合 Main.java，添加这个方法
    public String getId() {
        return customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return customerName;
    }

    // 2. 实现 compareTo 方法 (用于 Main 中的 Collections.sort)
    @Override
    public int compareTo(Customer other) {
        return this.customerID.compareToIgnoreCase(other.customerID);
    }

    // 根据 Figure 1.0 设定单价
    public double getUnitPrice() {
        if (quantity < 48) {
            return 2.85;
        } else if (quantity <= 107) {
            return 2.63;
        } else if (quantity <= 215) {
            return 2.27;
        } else {
            return 2.00;
        }
    }

    private void determineShippingMethod() {
        if (quantity < 48) {
            this.shippingMethod = "Truck";
        } else if (quantity <= 107) {
            this.shippingMethod = "Rail";
        } else if (quantity <= 215) {
            this.shippingMethod = "Ship";
        } else {
            this.shippingMethod = "Customer arranges";
        }
    }

    public double getShippingCharge() {
        if (quantity < 48) {
            return 0.20;
        } else if (quantity <= 107) {
            return 0.18;
        } else if (quantity <= 215) {
            return 0.12;
        } else {
            return 0.00;
        }
    }

    public double getTotalPrice() {
        return quantity * getUnitPrice();
    }

    public double getTotalShipping() {
        return quantity * getShippingCharge();
    }

    public double getShippingDiscount() {
        return 0; // 默认为 0，PremiumCustomer 可以重写这个方法
    }

    public double getGrandTotal() {
        return getTotalPrice() + getTotalShipping() - getShippingDiscount();
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    // 3. 修复 toString 的格式化语法
    @Override
    public String toString() {
        return String.format(
                "Customer ID:           %s\n" +
                        "Customer Name:         %s\n" +
                        "Quantity:              %d\n" +
                        "Shipping Method:       %s\n" +
                        "Total Price:           $%.2f\n" +
                        "Total Shipping:        $%.2f\n" +
                        "Total Shipping Discount: -$%.2f\n" +
                        "Grand Total:           $%.2f\n",
                customerID, customerName, quantity, shippingMethod,
                getTotalPrice(), getTotalShipping(), getShippingDiscount(), getGrandTotal()
        );
    }
}