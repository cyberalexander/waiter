package com.exercise.waiter;

import java.util.Objects;

/**
 * Created : 02/12/2021 13:16
 * Project : waiter
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
public class Customer {
    private final String name;
    private Order order; // one-to-one - 1 customer to 1 order

    private Customer(String name, String orderName) {
        this.name = name;
        this.order = new Order(orderName, this);
    }

    public static Customer of(String message) {
        String[] nameToOrder = message.split(": ");
        return new Customer(nameToOrder[0], nameToOrder[1]);
    }

    public String getName() {
        return this.name;
    }

    public Order getOrder() {
        return this.order;
    }

    public void updateOrder(Order newOrder) {
        this.order = new Order(newOrder.getName(), this);
    }

    /**
     * Method validates if current customer wants to share the order.
     * @return Boolean result: TRUE if the order expected to be shared between >1 Customers, or else FALSE
     */
    public boolean sharingOrder() {
        return this.getOrder().sharedBetween() > 1;
    }

    public boolean sameOrderWanted() {
        return "same".equalsIgnoreCase(this.getOrder().getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return getName().equals(customer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
