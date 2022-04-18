package com.exercise.waiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    private static final AtomicInteger ID_GEN = new AtomicInteger(0);
    private final Map<Integer, Table> tableToOrders = new HashMap<>();

    /**
     * Method initiates new {@link Table} object accordingly with input {@param sizeOfTable}.
     * @param sizeOfTable The size of newly created Table
     * @return The uniquely generated Table ID
     */
    public int initTable(int sizeOfTable) {
        var tableId = ID_GEN.incrementAndGet();
        tableToOrders.put(tableId, new Table(tableId, sizeOfTable));
        return tableId;
    }

    /**
     * Method processing Customer's order and attach it to particular table.
     * @param tableId The unique ID of the table
     * @param message Customer's order message
     */
    public void customerSays(int tableId, String message) {
        tableToOrders.get(tableId).processCustomersOrder(Customer.of(message));
    }

    /**
     * Method processing the final order.
     * @param tableId The unique ID of the table
     * @return String message with the names of ordered dishes
     */
    public String createOrder(int tableId) {
        var table = tableToOrders.get(tableId);
        var notResolvedSharedOrder = table.getUnresolvedSharedOrder();
        if (table.unservedExists()) {
            return "MISSING " + table.getLeftWithoutOrder();
        } else if (notResolvedSharedOrder.isPresent()) {
            return String.format(
                "MISSING %s for %s",
                notResolvedSharedOrder.get().getLeftToShare(table),
                notResolvedSharedOrder.get().getName()
            );
        }
        return table.getAccumulatedOrder();
    }
}
