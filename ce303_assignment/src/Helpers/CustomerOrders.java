package Helpers;

import java.util.HashMap;
import java.util.Set;

public class CustomerOrders {

    private static HashMap<String, Order> orders;

    public CustomerOrders() {
        this.orders = new HashMap<>();
    }

    public void addOrderForCustomer(String customer, Order order) {
        orders.put(customer, order);
    }

    public static Order getOrderForCustomer(String customer) {
        return orders.get(customer);
    }

    public Set<String> getAllCustomers() {
        return orders.keySet();
    }

}