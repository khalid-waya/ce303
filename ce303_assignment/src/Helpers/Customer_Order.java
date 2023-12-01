package Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Customer_Order {

    private final Map<Integer, Order> orders = new TreeMap<>();

    public void createOrder( int orderID, String customerName, String order_name )
    {   Order order = new Order( orderID, customerName );
        order.setOrder_name(order_name);
        orders.put(orderID, order);
    }

    public List<Integer> getListOfOrders(int orderID )
    {   List<Integer> retrievedOrders = new ArrayList<Integer>();
        for (Order order : orders.values())
        {
            if (order.getOrderID() == orderID)
        { retrievedOrders.add( order.getOrderID() );}

        }
        return retrievedOrders;
    }
}
