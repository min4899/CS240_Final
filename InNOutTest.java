import java.util.Random;
/**
   CS 240 Final Part 1.
   Tests an inventory system for In-N-Out for the month of December 2017.
   @author Minwoo Soh
*/
public class InNOutTest
{
  public static void main(String[] args)
  {
    Random random = new Random();

    // Inventory object to handle restocking, making burgers, and ingredient expirations.
    Inventory kitchen = new Inventory();

    // Menu list with all burger options.
    LinkedDataList<String> menu = new LinkedDataList<String>();
    menu.add("Burger");
    menu.add("Cheese Burger");
    menu.add("Vegan Lettuce Wrap Burger");
    menu.add("Burger No Onion");
    menu.add("Cheese Burger No Onion");
    menu.add("Burger No Tomato");

    // Queue for customer line.
    SingleLinkedQueue line = new SingleLinkedQueue();

    // Dictionary to keep track of customer # and what they orderd.
    SortedLinkedDictionary report = new SortedLinkedDictionary();

    int day = 1; // Starting day: December 1, 2017.
    int shipmentArrival = 1; // Track days left until new shipment arrives.


    while(day < 32) // Go through each day until end of December.
    {
      System.out.println("December " + day +":");

      // Checks if shipment arrived at start of each day.
      shipmentArrival--;
      if(shipmentArrival == 0)
      {
        System.out.println("Shipment has arrived.");
      }
      else
      {
        System.out.println("No shipment today.");
      }
      if(shipmentArrival == 0)
      {
        kitchen.restock();
        shipmentArrival = random.nextInt(4) + 3; // Set next date for arrival. Between 3 - 6.
      } // end if - shipment complete.

      // Begin serving customers for the day.

      // Counters for number of menu items ordered for that day.
      int countItemOne = 0;
      int countItemTwo = 0;
      int countItemThree = 0;
      int countItemFour = 0;
      int countItemFive = 0;
      int countItemSix = 0;

      int customerServed = 0; // Counter for customers served for that day.
      int lostCustomerDay = 0; // Counter for lost customers for that day.

      for(int hours = 0; hours < 10; hours++) // Each day lasts 10 hours, from 10AM to 7PM.
      {
        int customers = random.nextInt(100) + 1; // Random amount of customers from 1 to 100.

        if(customers > 50) // Line can only have up to 50, rest must leave.
        {
          lostCustomerDay += customers - 50;
          customers = 50;
        }
        for(int i = 1; i <= customers; i++)
        {
          line.enqueue(i);
        }

        while(!line.isEmpty()) // Keep taking order until no more customers left in line.
        {
          String order = menu.view(random.nextInt(6) + 1); // Customer orders random menu item.
          switch(order)
          {
            case "Burger": // Ordered Burger.
              if(kitchen.makeBurger())
              {
                countItemOne++;
                customerServed++;
                report.add(customerServed, 1);
                line.dequeue();
              }
              else // Not even enough ingredients.
              {
                lostCustomerDay++;
                line.dequeue();
              }
              break;
            case "Cheese Burger": // Ordered Cheese Burger.
              if(kitchen.makeCheeseBurger())
              {
                countItemTwo++;
                customerServed++;
                report.add(customerServed, 2);
                line.dequeue();
              }
              else // Not even enough ingredients.
              {
                lostCustomerDay++;
                line.dequeue();
              }
              break;
            case "Vegan Lettuce Wrap Burger": // Ordered Vegan Lettuce Wrap Burger.
              if(kitchen.makeVeganBurger())
              {
                countItemThree++;
                customerServed++;
                report.add(customerServed, 3);
                line.dequeue();
              }
              else // Not even enough ingredients.
              {
                lostCustomerDay++;
                line.dequeue();
              }
              break;
            case "Burger No Onion": // Ordered Burger No Onion.
              if(kitchen.makeBurgerNoOnion())
              {
                countItemFour++;
                customerServed++;
                report.add(customerServed, 4);
                line.dequeue();
              }
              else // Not even enough ingredients.
              {
                lostCustomerDay++;
                line.dequeue();
              }
              break;
            case "Cheese Burger No Onion": // Ordered Cheese Burger No Onion.
              if(kitchen.makeCheeseBurgerNoOnion())
              {
                countItemFive++;
                customerServed++;
                report.add(customerServed, 5);
                line.dequeue();
              }
              else // Not even enough ingredients.
              {
                lostCustomerDay++;
                line.dequeue();
              }
              break;
            case "Burger No Tomato": // Ordered Burger No Tomato.
              if(kitchen.makeBurgerNoTomato())
              {
                countItemSix++;
                customerServed++;
                report.add(customerServed, 6);
                line.dequeue();
              }
              else // Not even enough ingredients.
              {
                lostCustomerDay++;
                line.dequeue();
              }
              break;
            default:
          } // end switch
        } // end while - no more in line.
      } // end for - end of hour.

      // Checking for expired ingredients at the end of each day.
      int wasteBun = kitchen.checkBun();
      int wastePatty = kitchen.checkPatty();
      int wasteLettuce = kitchen.checkLettuce();
      int wasteTomato = kitchen.checkTomato();
      int wasteOnion = kitchen.checkOnion();
      int wasteCheese = kitchen.checkCheese();
      // Removed all expired ingredients.

      // Print out report for each day.
      System.out.println("Lost Customers: " + lostCustomerDay);

      System.out.println("Expired Items:");
      System.out.println("  Buns: " + wasteBun);
      System.out.println("  Patties: " + wastePatty);
      System.out.println("  Lettuces: " + wasteLettuce);
      System.out.println("  Tomatoes: " + wasteTomato);
      System.out.println("  Onions: " + wasteOnion);
      System.out.println("  Cheeses: " + wasteCheese);

      System.out.println("Ordered Items:");
      System.out.println("  Burger: " + countItemOne);
      System.out.println("  Cheese Burger: " + countItemTwo);
      System.out.println("  Vegan Lettuce Wrap Burger: " + countItemThree);
      System.out.println("  Burger No Onion: " + countItemFour);
      System.out.println("  Cheese Burger No Onion: " + countItemFive);
      System.out.println("  Burger No Tomato: " + countItemSix);

      System.out.println("Customer Number and Item Ordered:");
      if(customerServed == 0)
      {
        System.out.println("  No orders today.");
      }
      else
      {
        for(int customer = 1; customer <= customerServed; customer++)
        {
          System.out.println("  Customer " + customer + " -> #" + report.getValue(customer));
        }
      }
      System.out.println();

      report.clear(); // Clear dictionary for next day.
      day++;
    } // end while - end of day.

  } // end main
} // end of InNOutTest
