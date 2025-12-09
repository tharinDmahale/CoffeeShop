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
import main.coffeeShop.CoffeeShopForBarista;

public final class Barista extends Person implements Runnable {
    private final CoffeeShopForBarista coffeeShop;

    public Barista(final CoffeeShop coffeeShop, final int number) {
        super(number);
        this.thread = new Thread(this, this.toString());
        this.coffeeShop = (CoffeeShopForBarista) coffeeShop;
    }

    @Override
    public final void run() {
        this.coffeeShop.prepareOrder();
    }
}
