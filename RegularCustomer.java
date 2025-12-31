public class RegularCustomer extends Customer {
    public RegularCustomer(String id, String name, int qty) {
        super(id, name, qty);
    }

    @Override
    public double getShippingDiscount() {
        double totalPrice = getTotalPrice();
        double shippingCost = getTotalShipping();
        double discountRate = 0.0;

        if (totalPrice < 400)
        {
            discountRate = 0.0;
        }
        else if (totalPrice>=400 && totalPrice < 1200)
        {
            discountRate = 0.10;
        }
        else
        {
            discountRate = 0.20;
        }
        return shippingCost * discountRate;
    }
}