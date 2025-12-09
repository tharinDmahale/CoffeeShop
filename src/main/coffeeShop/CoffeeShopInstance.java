/* *
 * Author: Tharin Deelaka Mahale
 * UoW ID: W1833548
 * IIT ID: 20200709
 * Module code: 6SENG006C
 * Unit: Coursework
 * Scenario: 1
 * */
package main.coffeeShop;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class CoffeeShopInstance implements CoffeeShopForCustomer, CoffeeShopForBarista {// Monitor class
   private final Queue<String> orderQueue;
   private final int maxCapacity;
   private final Lock lock;
   private final Condition customer;
   private final Condition barista;

   public CoffeeShopInstance(final int maxCapacity) {
      this.maxCapacity = maxCapacity;
      this.orderQueue = new LinkedBlockingQueue<>(this.maxCapacity);// LinkedBlockingQueue is thread safe
      this.lock = new ReentrantLock();
      this.customer = this.lock.newCondition();
      this.barista = this.lock.newCondition();
   }

   @Override
   public void placeOrder(final int orderNo) {// For customers only
      this.lock.lock();

      try {
         while (this.orderQueue.size() == this.maxCapacity) {// Customer must wait if order queue is full
            this.customer.await();
         }

         snooze();// To simulate ordering time
         final String order = String.format("{Order:%d}", orderNo);
         this.orderQueue.add(order);
         System.out.printf("The %s placed the %s. Number of orders in the order queue is %d.%n",
                 Thread.currentThread().getName(), order, this.orderQueue.size());

         if (this.orderQueue.size() == this.maxCapacity) {
            System.out.printf("The order queue is full.%n");
         }

         this.barista.signalAll();

      } catch (final InterruptedException interruptedException) {
         logInterruptedException(interruptedException);

      } finally {
         this.lock.unlock();
      }
   }

   @Override
   public void prepareOrder() {// For baristas only
      this.lock.lock();

      try {
         while (this.orderQueue.isEmpty()) {// Barista must wait if order queue is empty
            this.barista.await();
         }

         snooze();// To simulate preparation time
         System.out.printf("The %s prepared the %s. Number of orders in the order queue is %d.%n",
                 Thread.currentThread().getName(), this.orderQueue.poll(), this.orderQueue.size());

         if (this.orderQueue.isEmpty()) {
            System.out.printf("The order queue is empty.%n");
         }

         this.customer.signalAll();

      } catch (final InterruptedException interruptedException) {
         logInterruptedException(interruptedException);

      } finally {
         this.lock.unlock();
      }
   }

   private static void snooze() throws InterruptedException {// Auxiliary sleep method
      Thread.sleep(((new Random().nextInt(5)) + 1) * 1000);
   }

   private static void logInterruptedException(final InterruptedException interruptedException) {// Auxiliary interrupted exception logger
      System.err.printf("%s: %s: %s%n",
              Thread.currentThread().getName(), interruptedException.getMessage(), interruptedException.getCause());
   }
}
