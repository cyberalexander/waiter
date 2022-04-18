package com.exercise.waiter;

import java.util.Objects;

/**
 * Created : 03/12/2021 11:47
 * Project : waiter
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
public class Order {
    private final String name;
    private final Customer customer;
    private Integer toBeSharedBetween = 1;

    public Order(String name, Customer customer) {
        this.name = name;
        if (this.name.contains(" for ")) {
            this.toBeSharedBetween = Integer.parseInt(this.name.split(" for ")[1]);
        }
        this.customer = customer;
    }

    public String getName() {
        return this.name;
    }

    public Integer sharedBetween() {
        return this.toBeSharedBetween;
    }

    public Integer getLeftToShare(Table table) {
        return this.toBeSharedBetween - actuallySharedBetween(table);
    }

    public boolean isShared(Table table) {
        return this.toBeSharedBetween == actuallySharedBetween(table);
    }

    /**
     * Method calculates between how many Customers shared current order at the moment.
     * @param table The Table entity
     * @return The count of the Customers, who ordered the same Order.
     */
    private int actuallySharedBetween(Table table) {
        return (int) table.customers().stream().filter(ca -> ca.getOrder().getName().equals(name)).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getName().equals(order.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Order{" +
            "name='" + name + '\'' +
            ", customer=" + customer +
            ", toBeSharedBetween=" + toBeSharedBetween +
            '}';
    }
}
