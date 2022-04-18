package com.exercise.waiter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created : 02/12/2021 13:47
 * Project : waiter
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
public class Table {
    private final Integer id;
    private final Integer size;
    private final LinkedList<Customer> customers;

    public Table(Integer id, Integer size) {
        this.id = id;
        this.size = size;
        this.customers = new LinkedList<>();
    }

    public List<Customer> customers() {
        return customers;
    }

    public void processCustomersOrder(Customer currentCustomer) {
        this.getOrderedBefore(currentCustomer).ifPresentOrElse(
            customer -> customer.updateOrder(currentCustomer.getOrder()),
            () -> {
                if (currentCustomer.sameOrderWanted()) {
                    currentCustomer.updateOrder(this.customers.getLast().getOrder());
                }
                this.customers.add(currentCustomer);
            }
        );
    }

    public int getLeftWithoutOrder() {
        return this.size - this.customers.size();
    }

    public boolean unservedExists() {
        return this.getLeftWithoutOrder() != 0;
    }

    public String getAccumulatedOrder() {
        return this.customers.stream()
            .map(Customer::getOrder)
            .map(Order::getName)
            .collect(Collectors.joining(", "));
    }

    public Optional<Order> getUnresolvedSharedOrder() {
        return this.customers.stream()
            .filter(Customer::sharingOrder)
            .map(Customer::getOrder)
            .filter(o -> !o.isShared(this))
            .findFirst();
    }

    private Optional<Customer> getOrderedBefore(Customer customer) {
        return this.customers.stream()
            .filter(c -> c.getName().equals(customer.getName()))
            .findFirst();
    }

    @Override
    public String toString() {
        return "Table{" +
            "id=" + id +
            ", size=" + size +
            ", customers=" + customers +
            '}';
    }
}
