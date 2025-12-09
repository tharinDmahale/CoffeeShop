/* *
 * Author: Tharin Deelaka Mahale
 * UoW ID: W1833548
 * IIT ID: 20200709
 * Module code: 6SENG006C
 * Unit: Coursework
 * Scenario: 1
 * */
package main;
import main.coffeeShop.CoffeeShop;
import main.coffeeShop.CoffeeShopInstance;
import main.people.Barista;
import main.people.Customer;
import main.people.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class CoffeeShopSimulator {// Main class
    private static final int MAX_CAPACITY = 5;
    private static final int NUMBER_OF_PEOPLE = 15;

    private CoffeeShopSimulator() {}

    public static void main(final String[] args) {
        final CoffeeShop coffeeShop = new CoffeeShopInstance(MAX_CAPACITY);
        final Person[] customers = new Person[NUMBER_OF_PEOPLE];
        final Person[] baristas = new Person[NUMBER_OF_PEOPLE];

        IntStream.range(0, NUMBER_OF_PEOPLE).forEach(i -> {
            customers[i] = new Customer(coffeeShop, i);
            baristas[i] = new Barista(coffeeShop, i);
        });

        final List<Person> people = Stream.concat(Arrays.stream(customers), Arrays.stream(baristas)).collect(Collectors.toList());
        Collections.shuffle(people);// To simulate the randomness of placing and preparing orders
        people.forEach(Person::start);
    }
}