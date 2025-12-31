public class Customer  {
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

    // 根据 Figure 1.0 设定单价 [cite: 18]
    public double getUnitPrice() {
        if (quantity < 48)
        {
            return 2.85;
        }
        else if (quantity>= 48 && quantity <= 107)
        {
            return 2.63;
        }
        else if (quantity >= 108 && quantity <= 215) {
            return 2.27;
        }
        else
        {
            return 2.00;
        }
    }

    private void determineShippingMethod() {
        if (quantity < 48)
        {
            this.shippingMethod = "Truck";
        }
        else if (quantity >=48 && quantity <= 107)
        {
            this.shippingMethod = "Rail";
        }
        else if ( quantity>=108 && quantity <= 215)
        {
            this.shippingMethod = "Ship";
        }
        else
        {
            this.shippingMethod = "Customer arranges shipping";
        }
    }

    public double getShippingCharge() {
        if (quantity < 48)
        {
            return 0.20;
        }
        else if (quantity <= 107) {
            return 0.18;
        }
        else if (quantity <= 215)
        {
            return 0.12;
        }
        else
        {
            return 0.00;
        }
    }
    public double getTotalPrice() {
        return quantity * getUnitPrice();
    }

    public double getTotalShipping() {
        return quantity * getShippingCharge();
    }

    public double getShippingDiscount()
    {
        return 0;
    }

    public double getGrandTotal() {
        return getTotalPrice() + getTotalShipping() - getShippingDiscount();
    }

    public String getCustomerID()
    {
        return customerID;
    }
    public String getShippingMethod()
    {
        return shippingMethod;
    }

    public String toString()
    {
        return "Customer ID:           " + customerID +
                "Customer Name:         " + customerName +
                "Quantity:              " + quantity +
                "Shipping Method:       " + shippingMethod +
                "Total Price:           $%.2f\n" + getTotalPrice() +
                "Total Shipping:        $%.2f\n" + getTotalShipping() +
                "Total Shipping Discount: -$%.2f\n"+ getShippingDiscount() +
                "Grand Total:           $%.2f\n" + getGrandTotal();
    }
}