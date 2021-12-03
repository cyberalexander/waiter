package com.exercise.waiter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RestaurantTest {

    @Test
    public void createOrder() {
        final Restaurant restaurant = new Restaurant();
        final int tableId = restaurant.initTable(4);
        restaurant.customerSays(tableId, "Joe: Soup");
        restaurant.customerSays(tableId, "Jim: Same");
        restaurant.customerSays(tableId, "Jack: Chips");
        restaurant.customerSays(tableId, "John: Chips");
        assertEquals("Soup, Soup, Chips, Chips", restaurant.createOrder(tableId));
    }

    @Test
    public void failedCreationBecauseNotEveryoneOrdered() {
        final Restaurant restaurant = new Restaurant();
        final int tableId = restaurant.initTable(4);
        restaurant.customerSays(tableId, "Joe: Soup");
        restaurant.customerSays(tableId, "Joe: Spaghetti");
        restaurant.customerSays(tableId, "Jim: Roastbeef");
        assertEquals("MISSING 2", restaurant.createOrder(tableId));
        restaurant.customerSays(tableId, "Jack: Spaghetti");
        restaurant.customerSays(tableId, "John: Chips");
        assertEquals("Spaghetti, Roastbeef, Spaghetti, Chips", restaurant.createOrder(tableId));
    }

    @Test
    public void failedCreationBecauseNotEnoughPeopleForADishFor2() {
        final Restaurant restaurant = new Restaurant();
        final int tableId = restaurant.initTable(4);
        restaurant.customerSays(tableId, "Joe: Soup");
        restaurant.customerSays(tableId, "Jim: Same");
        restaurant.customerSays(tableId, "Joe: Fish for 2");
        restaurant.customerSays(tableId, "Jack: Chips");
        restaurant.customerSays(tableId, "John: Chips");
        assertEquals("MISSING 1 for Fish for 2", restaurant.createOrder(tableId));
        restaurant.customerSays(tableId, "John: Fish for 2");
        assertEquals("Fish for 2, Soup, Chips, Fish for 2", restaurant.createOrder(tableId));
    }
}
