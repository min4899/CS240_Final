import java.util.Random;
/**
   CS 240 Final Part 1.
   An inventory system for In-N-Out that restocks, makes burgers,
   and checks for expired ingredients.
   @author Minwoo Soh
*/
public class Inventory
{
  Random random = new Random();

  // Stack for each ingredient.
  // Integers indicate how many days left until expiration.
  LinkedDataStack<Integer> bun;
  LinkedDataStack<Integer> patty;
  LinkedDataStack<Integer> lettuce;
  LinkedDataStack<Integer> tomato;
  LinkedDataStack<Integer> onion;
  LinkedDataStack<Integer> cheese;

  // Stack for temporarily storing values.
  LinkedDataStack<Integer> temp = new LinkedDataStack<Integer>();

  /** Default constructor */
  public Inventory()
  {
    bun = new LinkedDataStack<Integer>();
    patty = new LinkedDataStack<Integer>();
    lettuce = new LinkedDataStack<Integer>();
    tomato = new LinkedDataStack<Integer>();
    onion = new LinkedDataStack<Integer>();
    cheese = new LinkedDataStack<Integer>();
    temp = new LinkedDataStack<Integer>();
  } // end default constructor

  /** Restocks food stacks with random shipment amount of ingredients. */
  public void restock()
  {
    // Receive random amount of total shipment between 700 - 1000.
    int shipmentAmount = random.nextInt(301) + 700;

    // Counter for new ingredients.
    int newBun = 0;
    int newPatty = 0;
    int newLettuce = 0;
    int newTomato = 0;
    int newOnion = 0;
    int newCheese = 0;

    // Get random amount of ingredients from shipment.
    for(int i = 0; i < shipmentAmount; i++)
    {
      int item = random.nextInt(6); // Random number between 0 - 5.
      switch(item)
      {
        case 0:
          newBun++;
          break;
        case 1:
          newPatty++;
          break;
        case 2:
          newLettuce++;
          break;
        case 3:
          newTomato++;
          break;
        case 4:
          newOnion++;
          break;
        case 5:
          newCheese++;
          break;
        default:
      } // end switch
    } // end for

    // Reordering bun stack.
    while(!bun.isEmpty())
    {
      temp.push(bun.pop()); // Store old ingredients in temporary stack.
    }
    for(int i = 0; i < newBun; i++)
    {
      bun.push(5); // Push new ingredients to the bottom of stack.
    }
    while(!temp.isEmpty())
    {
      bun.push(temp.pop()); // Push old ingredients to top of new ones.
    }

    // Reordering patty stack.
    while(!patty.isEmpty())
    {
      temp.push(patty.pop()); // Store old ingredients in temporary stack.
    }
    for(int i = 0; i < newPatty; i++)
    {
      patty.push(4); // Push new ingredients to the bottom of stack.
    }
    while(!temp.isEmpty())
    {
      patty.push(temp.pop()); // Push old ingredients to top of new ones.
    }

    // Reordering lettuce stack.
    while(!lettuce.isEmpty())
    {
      temp.push(lettuce.pop()); // Store old ingredients in temporary stack.
    }
    for(int i = 0; i < newLettuce; i++)
    {
      lettuce.push(3); // Push new ingredients to the bottom of stack.
    }
    while(!temp.isEmpty())
    {
      lettuce.push(temp.pop()); // Push old ingredients to top of new ones.
    }

    // Reordering tomato stack.
    while(!tomato.isEmpty())
    {
      temp.push(tomato.pop()); // Store old ingredients in temporary stack.
    }
    for(int i = 0; i < newTomato; i++)
    {
      tomato.push(3); // Push new ingredients to the bottom of stack.
    }
    while(!temp.isEmpty())
    {
      tomato.push(temp.pop()); // Push old ingredients to top of new ones.
    }

    // Reordering onion stack.
    while(!onion.isEmpty())
    {
      temp.push(onion.pop()); // Store old ingredients in temporary stack.
    }
    for(int i = 0; i < newOnion; i++)
    {
      onion.push(5); // Push new ingredients to the bottom of stack.
    }
    while(!temp.isEmpty())
    {
      onion.push(temp.pop()); // Push old ingredients to top of new ones.
    }

    // Reordering cheese stack.
    while(!cheese.isEmpty())
    {
      temp.push(cheese.pop()); // Store old ingredients in temporary stack.
    }
    for(int i = 0; i < newCheese; i++)
    {
      cheese.push(2); // Push new ingredients to the bottom of stack.
    }
    while(!temp.isEmpty())
    {
      cheese.push(temp.pop()); // Push old ingredients to top of new ones.
    }
  } // end restock

  /** Removes ingredients from ingredient stacks to make a Burger.
      @return  True if burger was made, false if there if not. */
  public boolean makeBurger()
  {
    boolean made = false;
    // Necessary ingredients: bun, patty, lettuce, tomato, onion.
    if(!bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty() && !onion.isEmpty())
    {
      bun.pop();
      patty.pop();
      lettuce.pop();
      tomato.pop();
      onion.pop();
      made = true;
    }
    return made;
  } // end makeBurger

  /** Removes ingredients from ingredient stacks to make a cheese Burger.
      @return  True if burger was made, false if there if not. */
  public boolean makeCheeseBurger()
  {
    boolean made = false;
    // Necessary ingredients: cheese, bun, patty, lettuce, tomato, onion.
    if(!cheese.isEmpty() && !bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty() && !onion.isEmpty())
    {
      cheese.pop();
      bun.pop();
      patty.pop();
      lettuce.pop();
      tomato.pop();
      onion.pop();
      made = true;
    }
    return made;
  } // end makeCheeseBurger

  /** Removes ingredients from ingredient stacks to make a Vegan lettuce Wrap Burger.
      @return  True if burger was made, false if there if not. */
  public boolean makeVeganBurger()
  {
    boolean made = false;
    // Necessary ingredients: lettuce, lettuce, tomato, onion.
    if(!lettuce.isEmpty()) // Check if lettuce is available.
    {
      int firstLettuce = lettuce.pop();
      // Check if 2nd lettuce is available.
      if(!lettuce.isEmpty() && !tomato.isEmpty() && !onion.isEmpty())
      {
        lettuce.pop(); // Take 2nd lettuce.
        tomato.pop();
        onion.pop();
        made = true;
      }
      else // 2nd lettuce not available, cannot order. Place 1st lettuce back to stack.
      {
        lettuce.push(firstLettuce);
      }
    }
    return made;
  } // end makeVeganBurger

  /** Removes ingredients from ingredient stacks to make a Burger No onion.
      @return  True if burger was made, false if there if not. */
  public boolean makeBurgerNoOnion()
  {
    boolean made = false;
    // Necessary ingredients: bun, patty, lettuce, tomato.
    if(!bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty())
    {
      bun.pop();
      patty.pop();
      lettuce.pop();
      tomato.pop();
      made = true;
    }
    return made;
  } // end makeBurgerNoOnion

  /** Removes ingredients from ingredient stacks to make a cheese Burger No onion.
      @return  True if burger was made, false if there if not. */
  public boolean makeCheeseBurgerNoOnion()
  {
    boolean made = false;
    // Necessary ingredients: cheese, bun, patty, lettuce, tomato.
    if(!cheese.isEmpty() && !bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty())
    {
      cheese.pop();
      bun.pop();
      patty.pop();
      lettuce.pop();
      tomato.pop();
      made = true;
    }
    return made;
  } // end makeCheeseBurgerNoOnion

  /** Removes ingredients from ingredient stacks to make a Burger No tomato.
      @return  True if burger was made, false if there if not. */
  public boolean makeBurgerNoTomato()
  {
    boolean made = false;
    // Necessary ingredients: bun, patty, lettuce, onion.
    if(!bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !onion.isEmpty())
    {
      bun.pop();
      patty.pop();
      lettuce.pop();
      onion.pop();
      made = true;
    }
    return made;
  } // end makeBurgerNoTomato

  /** Check for expired Buns.
      @return  The integer number of wasted Buns. */
  public int checkBun()
  {
    int waste = 0;
    while(!bun.isEmpty())
    {
      int check = bun.pop() - 1; // Decrement remaining days left to use ingredient.
      if(check > 0) // Item is not expired.
      {
        temp.push(check);
      }
      else // Item is expired.
      {
        waste++;
      }
    }
    while(!temp.isEmpty()) // Reorder stack.
    {
      bun.push(temp.pop());
    }
    return waste;
  } // end checkBun

  /** Check for expired Patties.
      @return  The integer number of wasted Patties. */
  public int checkPatty()
  {
    int waste = 0;
    while(!patty.isEmpty())
    {
      int check = patty.pop() - 1; // Decrement remaining days left to use ingredient.
      if(check > 0) // Item is not expired.
      {
        temp.push(check);
      }
      else // Item is expired.
      {
        waste++;
      }
    }
    while(!temp.isEmpty()) // Reorder stack.
    {
      patty.push(temp.pop());
    }
    return waste;
  } // end checkPatty

  /** Check for expired Lettuces.
      @return  The integer number of wasted Lettuces. */
  public int checkLettuce()
  {
    int waste = 0;
    while(!lettuce.isEmpty())
    {
      int check = lettuce.pop() - 1; // Decrement remaining days left to use ingredient.
      if(check > 0) // Item is not expired.
      {
        temp.push(check);
      }
      else // Item is expired.
      {
        waste++;
      }
    }
    while(!temp.isEmpty()) // Reorder stack.
    {
      lettuce.push(temp.pop());
    }
    return waste;
  } // end checkLettuce

  /** Check for expired Tomatoes.
      @return  The integer number of wasted Tomatoes. */
  public int checkTomato()
  {
    int waste = 0;
    while(!tomato.isEmpty())
    {
      int check = tomato.pop() - 1; // Decrement remaining days left to use ingredient.
      if(check > 0) // Item is not expired.
      {
        temp.push(check);
      }
      else // Item is expired.
      {
        waste++;
      }
    }
    while(!temp.isEmpty()) // Reorder stack.
    {
      tomato.push(temp.pop());
    }
    return waste;
  } // emd checkTomato

  /** Check for expired Onions.
      @return  The integer number of wasted Onions. */
  public int checkOnion()
  {
    int waste = 0;
    while(!onion.isEmpty())
    {
      int check = onion.pop() - 1; // Decrement remaining days left to use ingredient.
      if(check > 0) // Item is not expired.
      {
        temp.push(check);
      }
      else // Item is expired.
      {
        waste++;
      }
    }
    while(!temp.isEmpty()) // Reorder stack.
    {
      onion.push(temp.pop());
    }
    return waste;
  } // end checkOnion

  /** Check for expired Cheeses.
      @return  The integer number of wasted Cheeses. */
  public int checkCheese()
  {
    int waste = 0;
    while(!cheese.isEmpty())
    {
      int check = cheese.pop() - 1; // Decrement remaining days left to use ingredient.
      if(check > 0) // Item is not expired.
      {
        temp.push(check);
      }
      else // Item is expired.
      {
        waste++;
      }
    }
    while(!temp.isEmpty()) // Reorder stack.
    {
      cheese.push(temp.pop());
    }
    return waste;
  } // end checkCheese

} // end of Inventory
