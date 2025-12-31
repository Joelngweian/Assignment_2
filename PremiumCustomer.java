public class PremiumCustomer extends Customer {
    public PremiumCustomer(String id, String name, int qty) {
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
        else if ( totalPrice>= 400 && totalPrice < 1200)
        {
            discountRate = 0.15; // 15%
        }
        else
        {
            discountRate = 0.25; // 25%
        }
        return shippingCost * discountRate;
    }
}