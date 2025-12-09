/* *
 * Author: Tharin Deelaka Mahale
 * UoW ID: W1833548
 * IIT ID: 20200709
 * Module code: 6SENG006C
 * Unit: Coursework
 * Scenario: 1
 * */
package main.people;

public abstract class Person {
    protected Thread thread;
    protected final int number;

    protected Person(final int number) {
        this.number = number;
    }

    @Override
    public final String toString() {
        return String.format("{%s:%s}", this.getClass().getSimpleName(), this.number);
    }

    public final void start() {
        this.thread.start();
    }
}
