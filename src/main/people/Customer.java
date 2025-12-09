/* *
 * Author: Tharin Deelaka Mahale
 * UoW ID: W1833548
 * IIT ID: 20200709
 * Module code: 6SENG006C
 * Unit: Coursework
 * Scenario: 1
 * */
package main.people;

import main.coffeeShop.CoffeeShop;
import main.coffeeShop.CoffeeShopForCustomer;

public final class Customer extends Person implements Runnable {
    private final CoffeeShopForCustomer coffeeShop;

    public Customer(final CoffeeShop coffeeShop, final int number) {
        super(number);
        this.thread = new Thread(this, this.toString());
        this.coffeeShop = (CoffeeShopForCustomer) coffeeShop;
    }

    @Override
    public final void run() {
        this.coffeeShop.placeOrder(this.number);
    }
}
