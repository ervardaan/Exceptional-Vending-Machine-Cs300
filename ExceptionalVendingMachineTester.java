//////////////// FILE HEADER //////////////////////////
//
// Title: P04 Exceptional Vending Machine
// Course: CS 300 Fall 2022
//
// Author: VARDAAN KAPOOR
// Email: VKAPOOR5@WISC.EDU
// Lecturer: (Mouna Kacem, Hobbes LeGault, or Jeff Nyhoff)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * This class implements testers to check the correctness of the methods implemented in p04
 * Exceptional Vending Machine
 *
 */
public class ExceptionalVendingMachineTester {
  

  // It is recommended but NOT required to add additional tester methods to check the correctness
  // of loadItems and saveVendingMachineSumary defined in the ExceptionalVendingMachine class.

  
  public ExceptionalVendingMachineTester() {
  }
  /**
   * Checks the correctness of the constructor of the class Item when passed invalid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */

  public static boolean testItemConstructorNotValidInput() {
    // description is null
    String s = null;// s is null
    String s1 = "";
    boolean check4;
    boolean check1;
    boolean check2;
    boolean check3;
    boolean check5;
    {

      try {

        Item o = new Item(s, 35);
        check1 = false;
      } catch (IllegalArgumentException e) {
        check1 = true;
      } catch (Exception e) {
        check1 = false;

      }

    }
    // description is null and expirationDate is invalid
    {

      try {

        Item o = new Item(s, -17);
        check2 = false;
      } catch (IllegalArgumentException e) {
        check2 = true;
      } catch (Exception e) {
        check2 = false;

      }
    }
    // description is empty and expirationDate is not invalid
    {

      try {
        Item o = new Item(s1, 17);
        check3 = false;
      } catch (IllegalArgumentException e) {
        check3 = true;
      } catch (Exception e) {
        check3 = false;

      }
    }
    // description is empty and expirationDate is invalid
    {

      try {
        Item o = new Item(s1, -17);
        check4 = false;
      } catch (IllegalArgumentException e) {
        check4 = true;
      } catch (Exception e) {
        check4 = false;

      }
    }
    // description is valid but expirationDate is invalid
    {

      try {
        Item o = new Item(s1 + "eee", -17);
        check5 = false;
      } catch (IllegalArgumentException e) {
        check5 = true;
      } catch (Exception e) {
        check5 = false;

      }
    }
    return check1 && check2 && check3 && check4 && check5;
  }

  /**
   * Checks the correctness of the constructor of the class Item, Item.getDescription(),
   * Item.getExpirationDate(), Item.setDescription(), and Item.toString() when passed valid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorGettersSetters() {
    // making valid constructor call
    Item i = new Item("hero", 17);
    boolean check1;
    boolean check2;
    boolean check3;
    boolean check4;
    // setDescription changes description
    {
      String prev_des = i.getDescription();
      i.setDescription("toy");
      if (!i.getDescription().equals(prev_des)) {
        check1 = true;
      } else {
        check1 = false;
      }
    }
    // setDescription doesn't change description because value is empty
    {
      String prev_des = i.getDescription();
      try {
        i.setDescription("");
        check2 = false;
      } catch (IllegalArgumentException e) {
        if (i.getDescription().equals(prev_des)) {
          check2 = true;
        } else {
          check2 = false;
        }
      }
    }
    // setDescription doesn't change description because value is null
    {
      String prev_des = i.getDescription();
      try {
        i.setDescription(null);
        check3 = false;
      } catch (IllegalArgumentException e) {
        if (i.getDescription().equals(prev_des)) {
          check3 = true;
        } else {
          check3 = false;
        }
      }
    }
    // setDescription doesn't change description because value is same
    {
      String prev_des = i.getDescription();
      i.setDescription(prev_des);
      if (i.getDescription().equals(prev_des)) {
        check4 = true;
      } else {
        check4 = false;
      }
    }
    return check1 && check2 && check3 && check4; // default return statement added to resolve
    // compiler errors
  }

  /**
   * Checks the correctness of the Item.equals() method. You should consider at least the following
   * four scenarios. (1) Create an item with valid description and expiration date, comparing it to
   * itself should return true. (2) Two items having the same description but different expiration
   * dates should be equal. (3) Passing a null reference to the Item.equals() method should return
   * false. (4) An item MUST NOT be equal to an object NOT instance of the class Item, for instance
   * a string object.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemEquals() {
    Item i = new Item("pizza", 34);
    boolean check1;
    boolean check2;
    boolean check3;
    boolean check4;
    boolean check5;
    boolean check6;
    // Scenario 1:pass same object
    {
      check1 = i.equals(i);
    }
    // scenario 2:pass null object
    {
      check2 = i.equals(null);
    }
    // scenario 3:pass object with different description but same expirationDate
    {
      check3 = i.equals(new Item("hello", 34));
    }
    // scenario 4:pass object with same description but different expirationDate
    {
      check4 = i.equals(new Item("pizza", 2));
    }
    // scenario 5:pass different object with same values
    {
      check5 = i.equals(new Item("pizza", 34));
    }
    // scenario 6:pass object of some other class
    {
      check6 = i.equals(new String("hello"));
    }
    return check1 && !check2 && !check3 && check4 && check5 && !check6;
    // default return statement added to resolve compiler errors
  }


  /**
   * Checks the correctness of the constructor of the ExceptionalVendingMachine when passed invalid
   * input
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineConstructor() {
    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;

    try {
      ExceptionalVendingMachine evm = new ExceptionalVendingMachine(0);
    } catch (IllegalArgumentException e) {

      check1 = true;
    }
    try {
      ExceptionalVendingMachine evm2 = new ExceptionalVendingMachine(-1);
    } catch (IllegalArgumentException e) {
      check2 = true;
    }
    try {
      ExceptionalVendingMachine evm3 = new ExceptionalVendingMachine(10);
    } catch (IllegalArgumentException e) {
      check3 = true;
    }
    return check1 == true && check2 == true && check3 == false;
  }

  /**
   * Checks the correctness of the following methods defined in the ExceptionalVendingMachine class
   * when an exception is expected to be thrown:
   * 
   * addItem(), containsItem(), getIndexNextItem(), getItemAtIndex(), getItemOccurrences(),
   * getItemOccurrencesByExpirationDate(), removeNextItem().
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineAddContainsRemoveGetters() {
    ExceptionalVendingMachine evm = new ExceptionalVendingMachine(5);
    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;
    boolean check4 = false;
    boolean check5 = false;
    boolean check6 = false;
    boolean check7a = false;
    {

      // scenario 1:add correct item to additem method
      try {
        int prev_size = evm.size();
        evm.addItem("papers", 10);
        if (evm.size() == prev_size + 1)
          check1 = true;
      } catch (Exception e) {
        check1 = false;
      }

    }
    {
      // scenario 2:description is null
      try {
        int prev_size = evm.size();
        evm.addItem(null, 10);
        if (evm.size() == prev_size + 1) {
          check2 = false;
        }
      } catch (IllegalArgumentException e) {
        check2 = true;
      } catch (Exception e) {
        check2 = false;
      }
    }
    {
      // scenario 3:description is empty
      try {
        int prev_size = evm.size();
        evm.addItem(null, 10);
        if (evm.size() == prev_size + 1)
          check3 = false;
      } catch (IllegalArgumentException e) {
        check3 = true;
      } catch (Exception e) {
        check3 = false;
      }
    }
    {
      // scenario 4:expirationdate<0
      try {
        int prev_size = evm.size();
        evm.addItem("papers", -3);
        if (evm.size() == prev_size + 1)
          check4 = false;
      } catch (IllegalArgumentException e) {
        check4 = true;
      } catch (Exception e) {
        check4 = false;
      }
    }
    {
      // scenario 5:both description and expirationdate have a defect
      try {
        int prev_size = evm.size();
        evm.addItem("", -3);
        if (evm.size() == prev_size + 1)
          check5 = false;
      } catch (IllegalArgumentException e) {
        check5 = true;
      } catch (Exception e) {
        check5 = false;
      }
    }
    {
      // scenario 6:both description and expirationdate have a defect
      try {
        int prev_size = evm.size();
        evm.addItem(null, -3);
        if (evm.size() == prev_size + 1)
          check6 = false;
      } catch (IllegalArgumentException e) {
        check6 = true;
      } catch (Exception e) {
        check6 = false;
      }
    }

    {
      // scenario 7:vending machine is full
      if (evm.size() == 1) {


        evm.addItem("papers2", 10);
        evm.addItem("papers3", 10);
        evm.addItem("papers4", 10);
        evm.addItem("papers5", 10);


        if (evm.size() == 5) {
          try {
            int prev_size = evm.size();
            evm.addItem("papers6", 10);
            if (evm.size() == prev_size + 1) {
              check7a = false;
            }
          } catch (IllegalStateException e) {
            check7a = true;
          } catch (Exception e) {
            check7a = false;
          }
        } else {
          check7a = false;
        }
      }

      else {
        check7a = false;
      }
    }
    // checking containsItem() method
    ExceptionalVendingMachine evm2 = new ExceptionalVendingMachine(5);
    boolean check11 = false;
    boolean check12 = false;
    boolean check13 = false;
    boolean check14 = false;
    boolean check15 = false;
    boolean check16 = false;
    boolean check17 = false;
    boolean check18 = false;
    {
      // scenario 11:adding an item and searching by wrong description
      evm2.addItem("papers", 10);
      try {
        boolean e1 = evm2.containsItem("paperssss");
        if (e1 == false) {
          check11 = true;
        }
      }

      catch (Exception e) {
        check11 = false;
      }
    }

    {
      // scenario 12:adding an item and searching by correct description
      evm2.addItem("hero", 1);
      try {
        evm2.containsItem("hero");
        check12 = true;
      } catch (Exception e) {
        check12 = false;
      }
    }
    {
      // scenario 13:adding an item and searching by blank description
      evm2.addItem("super", 1);
      try {
        evm2.containsItem("");
        check13 = false;
      } catch (IllegalArgumentException e) {
        check13 = true;
      } catch (Exception e) {
        check13 = false;
      }
    }
    {
      // scenario 14:adding an item and searching by null description
      evm2.addItem("byepass", 1);
      try {
        evm2.containsItem(null);
        check14 = false;
      } catch (IllegalArgumentException e) {
        check14 = true;
      } catch (Exception e) {
        check14 = false;
      }
    }
    {
      // scenario 15:not adding an item and searching by valid description
      try {
        evm2.addItem("", 2);
      } catch (Exception e) {
        try {
          boolean e2 = evm2.containsItem("hero111");
          if (e2 == false) {
            check15 = true;
          }
        } catch (Exception e1) {
          check15 = false;
        }
      }
    }
    {
      // scenario 16:not adding an item and searching by invalid description but null
      try {
        evm2.addItem(null, 5);

      } catch (Exception e) {
        try {
          evm2.containsItem(null);
          check16 = false;
        } catch (IllegalArgumentException e11) {
          check16 = true;
        } catch (Exception e2) {
          check16 = false;
        }
      }
    }
    {
      // scenario 17:not adding an item and searching by invalid description but empty string
      try {
        evm2.addItem(null, 5);

      } catch (Exception e) {
        try {
          evm2.containsItem("");
          check17 = false;
        } catch (IllegalArgumentException e3) {
          check17 = true;
        } catch (Exception e4) {
          check17 = false;
        }
      }
    }
    {
      // scenario 18:not adding an item but searching previously added same items by correct
      // description
      evm2.addItem("movie", 44);
      try {
        evm2.addItem("", 2);
      } catch (Exception e) {
        try {
          boolean extra = evm2.containsItem("movie");
          if (extra == true) {
            check18 = true;
          }
        }

        catch (Exception e5) {
          check18 = false;
        }
      }
    }
    ExceptionalVendingMachine evm3 = new ExceptionalVendingMachine(5);
    boolean check21 = false;
    boolean check22 = false;
    boolean check23 = false;
    boolean check24 = false;
    boolean check25 = false;
    evm3.addItem("papercookie", 3);
    evm3.addItem("pizza", 4);
    // CHECKING GETINDEXNEXTITEM
    {
      // scenario 21:description is null but item exists

      try {
        evm3.getIndexNextItem(null);
        check21 = false;
      } catch (IllegalArgumentException e) {
        check21 = true;
      } catch (Exception e2) {
        check21 = false;
      }
    }

    {
      // scenario 22:description is empty but item exits
      try {
        evm3.getIndexNextItem("");
        check22 = false;
      } catch (IllegalArgumentException e) {
        check22 = true;
      } catch (Exception e2) {
        check22 = false;
      }
    }
    {
      // scenario 23:description is good but only one item exists
      try {
        int i = evm3.getIndexNextItem("pizza");
        if (i == 1) {
          check23 = true;
        } else {
          check23 = false;
        }
      }

      catch (Exception e2) {
        check23 = false;
      }
    }
    {
      // scenario 24:description is good and many items exist
      evm3.addItem("pancake", 3);
      evm3.addItem("pizza", 56);
      evm3.addItem("pizza", 0);
      try {
        int i = evm3.getIndexNextItem("pizza");
        if (i == 4) {
          check24 = true;
        } else {
          check24 = false;
        }
      }


      catch (Exception e2) {
        check24 = false;
      }
    }
    {
      // scenario 25:description is good but no item exists
      try {
        evm3.getIndexNextItem("burger");
        check25 = false;
      } catch (NoSuchElementException e) {
        check25 = true;
      } catch (Exception e) {
        check25 = false;
      }

    }
    boolean check31 = false;
    boolean check32 = false;
    boolean check33 = false;
    // checking getitematindex
    {
      // scenario 31:index<0
      try {
        evm3.getItemAtIndex(-3);
        check31 = false;
      } catch (IndexOutOfBoundsException e) {
        check31 = true;
      } catch (Exception e) {
        check31 = false;
      }
    }
    {
      // scenario 32:index>=size
      try {
        evm3.getItemAtIndex(5);
        check32 = false;
      } catch (IndexOutOfBoundsException e) {
        check32 = true;
      } catch (Exception e) {
        check32 = false;
      }
    }
    {
      // scenario 33:index is within bounds
      try {
        evm3.getItemAtIndex(2);
        check33 = true;
      }

      catch (Exception e) {
        check33 = false;
      }
    }
    boolean check41 = false;
    boolean check42 = false;
    boolean check43 = false;
    boolean check44 = false;
    boolean check45 = false;
    // check getitemoccurences
    {
      // scenario 41:description is null
      try {
        evm3.getItemOccurrences(null);
        check41 = false;
      } catch (IllegalArgumentException e) {
        check41 = true;
      } catch (Exception e2) {
        check41 = false;
      }
    }
    {
      // scenario 42:description is empty
      try {
        evm3.getItemOccurrences("");
        check42 = false;
      } catch (IllegalArgumentException e) {
        check42 = true;
      } catch (Exception e2) {
        check42 = false;
      }
    }
    {
      // scenario 43:description is valid but item doesn't exist
      try {
        int i = evm3.getItemOccurrences("potato");
        if (i == 0) {
          check43 = true;
        } else {
          check43 = false;
        }
      }


      catch (Exception e2) {
        check43 = false;
      }
    }
    {
      // scenario 44:description is valid and item occurs only once
      try {
        int i = evm3.getItemOccurrences("papercookie");
        if (i == 1) {
          check44 = true;
        } else {
          check44 = false;
        }
      }


      catch (Exception e2) {
        check44 = false;
      }
    }
    {
      // scenario 45:description is valid and item occurs many times
      try {
        int i = evm3.getItemOccurrences("pizza");
        if (i == 3) {
          check45 = true;
        } else {
          check45 = false;
        }
      }


      catch (Exception e2) {
        check45 = false;
      }
    }
    ExceptionalVendingMachine evm4 = new ExceptionalVendingMachine(10);
    evm4.addItem("pizza", 0);
    evm4.addItem("juice", 100);
    evm4.addItem("pizza", 78);
    evm4.addItem("juice", 3);
    evm4.addItem("pizza", 3);
    evm4.addItem("pine", 45);
    evm4.addItem("bread", 45);
    evm4.addItem("mustard", 300);
    boolean check51 = false, check52 = false, check53 = false, check54 = false, check55 = false,
        check56 = false, check57 = false, check58 = false, check59 = false, check60 = false;
    // checking getitemsbyexpirationdate
    {
      // scenario 51:description is null
      try {
        evm4.getItemOccurrencesByExpirationDate(null, 56);
        check51 = false;
      } catch (IllegalArgumentException e) {
        check51 = true;
      } catch (Exception e) {
        check51 = false;
      }
    }
    {
      // scenario 52:description is empty
      try {
        evm4.getItemOccurrencesByExpirationDate("", 56);
        check52 = false;
      } catch (IllegalArgumentException e) {
        check52 = true;
      } catch (Exception e) {
        check52 = false;
      }
    }
    {
      // scenario 53:description is valid but expirationdate<0
      try {
        evm4.getItemOccurrencesByExpirationDate("pretzel", -4);
        check53 = false;
      } catch (IllegalArgumentException e) {
        check53 = true;
      } catch (Exception e) {
        check53 = false;
      }
    }
    {
      // scenario 54:description is valid but expirationdate is 0
      try {
        int i = evm4.getItemOccurrencesByExpirationDate("pizza", 0);
        if (i == 3) {
          check54 = true;
        } else {
          check54 = false;
        }
      }


      catch (Exception e) {
        check54 = false;
      }
    }
    {
      // scenario 55:both description and expirationdate are valid and one item exixts but
      // below the expirationdate
      try {
        int i = evm4.getItemOccurrencesByExpirationDate("pine", 46);
        if (i == 0) {
          check55 = true;
        } else {
          check55 = false;
        }
      }

      catch (Exception e) {
        check55 = false;
      }
    }
    {
      // scenario 56:both description and expirationdate are valid and many items exist but all
      // below the expirationdate
      try {
        int i = evm4.getItemOccurrencesByExpirationDate("juice", 101);
        if (i == 0) {
          check56 = true;
        } else {
          check56 = false;
        }
      }

      catch (Exception e) {
        check56 = false;
      }
    }
    {
      // scenario 57:both description and expirationdate are valid and many items exist but all
      // above the expirationdate
      try {
        int i = evm4.getItemOccurrencesByExpirationDate("juice", 3);
        if (i == 2) {
          check57 = true;
        } else {
          check57 = false;
        }
      }

      catch (Exception e) {
        check57 = false;
      }
    }
    {
      // scenario 58:both description and expirationdate are valid and one item exixts but
      // above the expirationdate
      try {
        int i = evm4.getItemOccurrencesByExpirationDate("pine", 45);
        if (i == 1) {
          check58 = true;
        } else {
          check58 = false;
        }
      }

      catch (Exception e) {
        check58 = false;
      }

    }
    {
      // scenario 59:both description and expirationdate are valid and many items exist but some
      // above the expirationdate and some below it
      try {
        int i = evm4.getItemOccurrencesByExpirationDate("pizza", 75);
        if (i == 1) {
          check59 = true;
        } else {
          check59 = false;
        }
      }

      catch (Exception e) {
        check59 = false;
      }
    }
    {
      // scenario 60:both description and expirationdate are valid and no item exists
      try {
        int i = evm4.getItemOccurrencesByExpirationDate("pretzel", 0);
        if (i == 0) {
          check60 = true;
        } else {
          check60 = false;
        }
      }


      catch (Exception e) {
        check60 = false;
      }

    }
    // checking removenextitem
    boolean check61 = false, check62 = false, check63 = false, check64 = false, check65 = false;
    {
      // scenario 61:description is null
      int s = evm4.size();
      try {

        evm4.removeNextItem(null);
        check61 = false;
      } catch (IllegalArgumentException e) {
        if (s == evm4.size()) {
          check61 = true;
        } else {
          check61 = false;
        }
      } catch (Exception e) {
        check61 = false;
      }

    }
    {
      // scenario 62:description is empty
      int s = evm4.size();
      try {

        evm4.removeNextItem("");
        check62 = false;
      } catch (IllegalArgumentException e) {
        if (s == evm4.size()) {
          check62 = true;
        } else {
          check62 = false;
        }
      } catch (Exception e) {
        check62 = false;
      }
    }
    {
      // scenario 63:description is valid but only one item exists
      int s = evm4.size();
      try {
        Item i1 = evm4.removeNextItem("mustard");
        if (i1 != null && s == evm4.size() + 1) {
          check63 = true;
        } else {
          check63 = false;
        }
      } catch (Exception e) {
        check63 = false;
      }
    }
    {
      // scenario 64:description is valid and many items exist
      int s = evm4.size();
      try {
        Item i1 = evm4.removeNextItem("pizza");
        if (i1 != null && s == evm4.size() + 1) {
          check64 = true;
        } else {
          check64 = false;
        }
      } catch (Exception e) {
        check64 = false;
      }
    }
    {
      // scenario 65:description is valid but no item exists
      int s = evm4.size();
      try {
        Item i1 = evm4.removeNextItem("burger");
        check65 = false;
      } catch (NoSuchElementException e) {
        if (s == evm4.size()) {
          check65 = true;
        } else {
          check65 = false;
        }
      } catch (Exception e) {
        check65 = false;
      }
    }
    boolean checkfinal7 = check61 && check62 && check63 && check64 && check65;
    boolean checkfinal6 = check51 && check52 && check53 && check54 && check55 && check56 && check57
        && check58 && check59 && check60;
    boolean checkfinal5 = check41 && check42 && check43 && check44 && check45;
    boolean checkfinal4 = check31 && check32 && check33;
    boolean checkfinal3 = check21 && check22 && check23 && check24 && check25;
    boolean checkfinal2 =
        check11 && check12 && check13 && check14 && check15 && check16 && check17 && check18;
    boolean checkfinal1 = check1 && check2 && check3 && check4 && check5 && check6 && check7a;
    return checkfinal1 && checkfinal2 && checkfinal3 && checkfinal4 && checkfinal5 && checkfinal6
        && checkfinal7;
    // default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of isEmpty(), size(), and isFull() methods defined in the
   * ExceptionalVendingMachine class
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testEmptySizeFullExceptionalVendingMachine() {
    boolean check1 = false, check2 = false;
    ExceptionalVendingMachine evm5 = new ExceptionalVendingMachine(3);
    {
      // scenario 1:vending machine is empty
      if (evm5.isEmpty() && evm5.size() == 0) {
        check1 = true;
      }
    }
    evm5.addItem("pizza", 10);
    evm5.addItem("pizza", 10);
    evm5.addItem("pizza", 10);
    {
      // vending machine is full
      if (evm5.isFull() && evm5.size() == 3) {
        check2 = true;
      }
    }

    return check1 && check2; // default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of loadOneItem method with respect to its specification. Consider at
   * least the four following scenarios. (1) Successful scenario for loading one item with a valid
   * string representation to a non-full vending machine. (2) Unsuccessful scenario for passing null
   * or a blank string (for instance one space or empty string) to the loadOneItem() method call, an
   * IllegalArgumentEXception is expected to be thrown. (3) Unsuccessful scenario for passing a
   * badly formatted string to the loadOneItem method. A DataFormatException is expected to be
   * thrown. (4) Unsuccessful scenario for trying to load an item with a valid representation to a
   * full vending machine. An IllegalStateException is expected to be thrown.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testLoadOneItem() {
    boolean check1 = false, check2 = false, check3 = false, check4 = false, check5 = false,
        check6 = false, check7 = false, check8 = false, check9 = false, check10 = false;
    // case 1:string is empty
    ExceptionalVendingMachine evm = new ExceptionalVendingMachine(3);
    {
      try {
        evm.loadOneItem("");
        check1 = false;
      } catch (IllegalArgumentException e) {
        check1 = true;
      } catch (Exception e) {
        check1 = false;
      }

    }
    // case 2:string is null
    {
      try {
        evm.loadOneItem(null);
        check2 = false;
      } catch (IllegalArgumentException e) {
        check2 = true;
      } catch (Exception e) {
        check2 = false;
      }
    }
    // case 3:string is only white spaces
    {
      try {
        evm.loadOneItem("           ");
        check3 = false;
      } catch (IllegalArgumentException e) {
        check3 = true;
      } catch (Exception e) {
        check3 = false;
      }
    }
    // case 4:string has more than one colon
    {
      try {
        evm.loadOneItem("pizza:slice: 195");
        check4 = false;
      } catch (DataFormatException e) {
        check4 = true;
      } catch (Exception e) {
        check4 = false;
      }

    }
    // case 5:string has no colon
    {
      try {
        evm.loadOneItem("pizzaslice 195");
        check5 = false;
      } catch (DataFormatException e) {
        check5 = true;
      } catch (Exception e) {
        check5 = false;
      }
    }
    // case 6:string has no whitespace after colon and before expiration date
    {
      try {
        evm.loadOneItem("pizzaslice:195");
        check6 = true;
      } catch (Exception e) {
        check6 = false;
      }
    }
    // case 7:description is blank
    {
      try {
        evm.loadOneItem(": 195");
        check7 = false;
      } catch (DataFormatException e) {
        check7 = true;
      } catch (Exception e) {
        check7 = false;
      }
    }
    // case 8:expirationdate is blank
    {
      try {
        evm.loadOneItem("pizzaslice:");
        check8 = false;
      } catch (DataFormatException e) {
        check8 = true;
      } catch (Exception e) {
        check8 = false;
      }
    }
    // case 9:expirationdate is not parsable to an integer
    {
      try {
        evm.loadOneItem("pizzaslice: six");
        check9 = false;
      } catch (DataFormatException e) {
        check9 = true;
      } catch (Exception e) {
        check9 = false;
      }
    }
    // case 10: everything is good and item is added
    {
      try {
        int prev_size = evm.size();
        evm.loadOneItem("pizzaslice: 195");
        if (prev_size == evm.size() - 1) {
          check10 = true;
        } else {
          check10 = false;
        }

      }

      catch (Exception e) {
        check10 = false;

      }
    }
    // case 11:vending machine is full
    boolean check11 = false;
    {
      ExceptionalVendingMachine evmm = new ExceptionalVendingMachine(2);
      evmm.addItem("pizza", 10);
      evmm.addItem("pizza", 10);
      try {
        evmm.loadOneItem("platter: 10");
        check11 = false;
      } catch (IllegalStateException e) {
        check11 = true;
      } catch (Exception e) {
        check11 = false;
      }
    }

    return check1 && check2 && check3 && check4 && check5 && check6 && check7 && check8 && check9
        && check10 && check11;
    // default return statement added to resolve compiler errors
  }

  /**
   * Invokes all the public tester methods implemented in this class
   * 
   * @return true if all testers pass with no errors, and false if any of the tester fails.
   */
  public static boolean runAllTests() {
    return testItemConstructorNotValidInput() && testItemConstructorGettersSetters()
        && testItemEquals() && testExceptionalVendingMachineConstructor()
        && testExceptionalVendingMachineAddContainsRemoveGetters()
        && testEmptySizeFullExceptionalVendingMachine() && testLoadOneItem();


    // default return statement added to resolve compiler errors
  }



  /**
   * Main method for the tester class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    /*
     * System.out.println(testItemConstructorNotValidInput());
     * System.out.println(testItemConstructorGettersSetters());
     * System.out.println(testItemEquals());
     * System.out.println(testExceptionalVendingMachineConstructor());
     * System.out.println(testExceptionalVendingMachineAddContainsRemoveGetters());
     * System.out.println(testEmptySizeFullExceptionalVendingMachine());
     * System.out.println(testLoadOneItem());
     */

    System.out.println(runAllTests());

    

  }
}


