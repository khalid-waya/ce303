package Helpers;

public class Order {

    private final String customerName; private String order_name;

    public Order(String customerName)
    {   this.customerName = customerName;

    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }


}
