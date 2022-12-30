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
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * This class models a vending machine. When requested, the item with the soonest expiration date
 * will be dispensed first.
 *
 */
public class ExceptionalVendingMachine {
  private Item[] items; // array storing the items available within this vending machine
  private int size; // number of items available in this vending machine

  /**
   * Creates a new vending machine with a given capacity
   * 
   * @param capacity maximum number of items that can be held by this vending machine
   * @throws IllegalArgumentException with a descriptive error message if capacity is zero or
   *                                  negative
   */
  public ExceptionalVendingMachine(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("capacity is 0 or negative");
    }
    items = new Item[capacity];
    size = 0; // optional since 0 is the default value for primitive type int
  }

  /**
   * Checks whether this vending machine is empty
   * 
   * @return true if this vending machine is empty, false otherwise
   */
  public boolean isEmpty() {

    if (size < 1) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * Checks whether this vending machine is full
   * 
   * @return true if this vending machine is full, false otherwise
   */
  public boolean isFull() {

    if (size >= items.length) {
      return true;
    }
    return false;
  }

  /**
   * Returns the total number of items available in this vending machine
   * 
   * @return the size of this vending machine
   */
  public int size() {

    return size;
  }

  /**
   * Appends an item defined by its description and expirationDate to this vending machine. The item
   * will be added to the end of the vending machine.
   * 
   * @param description    description of the item to be added to the vending machine
   * @param expirationDate a positive integer which represents the expiration date of the item.
   * @throws IllegalStateException    with a descriptive error message if the vending machine is
   *                                  full
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank or if expirationDate is negative ( &lt; 0)
   */
  public void addItem(String description, int expirationDate) {
    // question :what if both exceptions are true-but only one of them will run-how can we throw
    // both
    // create a new item and add it to the end of this vending machine
    if (size == items.length) {
      throw new IllegalStateException("vending machine is full");
    }
    if (description == null || (description != null && description.length() == 0)
        || isBlankString1(description) || description.isBlank() || expirationDate < 0) {
      throw new IllegalArgumentException("either description or expirationDate is wrong");
    }

    items[size] = new Item(description, expirationDate);
    size++;
  }

  /**
   * Returns without removing the string representation of the item at the given index within the
   * vending machine
   * 
   * @param index index of an item within the vending machine
   * @return the string representation of the item stored at the given index within the vending
   *         machine defined by items and itemsCount. The returned string must have the following
   *         format: "description: expirationDate".
   * @throws IndexOutOfBoundsException with a descriptive error message if index &lt; 0 or index
   *                                   &gt;= size of the vending machine
   */
  public String getItemAtIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("can't access this index as it is out of bounds");
    }
    return items[index].toString();// returning string representation of the object
  }

  /**
   * Returns the number of occurrences of items with a given description within this vending machine
   * 
   * @param description description (name) of an item
   * @return the number of occurrences of items with a given description within the vending machine
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   */
  public int getItemOccurrences(String description) {
    if (description == null || (description != null && description.length() == 0)
        || isBlankString1(description) || description.isBlank()) {
      throw new IllegalArgumentException("description is wrong");
    }
    int nbOccurrences = 0;
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription()))// checking if 2 descriptions are equal
      {
        nbOccurrences++;
      }
    }
    return nbOccurrences;
  }

  /**
   * Checks whether the vending machine contains at least one item with the provided description
   * 
   * @param description description (name) of an item to search
   * @return true if there is a match with description in the vending machine, false otherwise
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   */
  public boolean containsItem(String description) {
    if (description == null || (description != null && description.length() == 0)
        || isBlankString1(description) || description.isBlank()) {
      throw new IllegalArgumentException("description is wrong");
    }
    return getItemOccurrences(description) != 0;
  }

  /**
   * Returns the number of items in the vending machine with a specific description and whose
   * expiration dates are greater or equal to a specific expiration date
   * 
   * @param description    description of the item to find its number of occurrences
   * @param expirationDate specific (earliest) expiration date
   * @return the number of items with a specific description and whose expiration date is greater or
   *         equal to the given one
   * @throws IllegalArgumentException with a descriptive error message if expirationDate is negative
   *                                  (less than zero) or description is null or blank
   */
  public int getItemOccurrencesByExpirationDate(String description, int expirationDate) {
    if (description == null || (description != null && description.length() == 0)
        || isBlankString1(description) || description.isBlank() || expirationDate < 0) {
      throw new IllegalArgumentException("either description or expirationDate is wrong");
    }
    int nbOccurrences = 0; // number of occurrences of the matching items
    // traverse the vending machine looking for matching items
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())
          && items[i].getExpirationDate() >= expirationDate) {// match found
        nbOccurrences++;
      }
    }
    // return the number of occurrences of the matching items
    return nbOccurrences;
  }

  /**
   * Returns without removing the index of the item having the provided description and the smallest
   * expiration date within the vending machine.
   * 
   * @param description description of an item
   * @return the index of the next item, meaning the item with the given description and the
   *         smallest expiration date.
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match found
   */
  public int getIndexNextItem(String description) {
    if (description == null || (description != null && description.length() == 0)
        || isBlankString1(description) || description.isBlank()) {
      throw new IllegalArgumentException("description is wrong");
    }
    int index = -1; // index of the search item
    int minExpirationDate = -1; // smallest expiration date of matching items
    // traverse the vending machine looking for the matching item with the smallest expiration date
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())) {
        int itemExpirationDate = items[i].getExpirationDate();
        if (index == -1) { // first match found
          minExpirationDate = items[i].getExpirationDate();
          index = i;
        } else { // another match found
          if (itemExpirationDate < minExpirationDate) {
            // match with smaller (sooner) expiration date found
            minExpirationDate = itemExpirationDate; // update minimum expiration date
            index = i; // update the index of the next item
          }
        }
      }
    }
    if (index < 0) {
      throw new NoSuchElementException("no element found");
    }
    return index; // return the index of the item with the given description and the smallest
                  // expiration date if found
  }

  /**
   * Removes and returns the item having the provided description with the smallest expiration date
   * within the vending machine. This method should maintain the order of precedence of items in the
   * vending machine. This means that the remove operation involves a shift operation.
   * 
   * @param description description of the item to remove or dispense
   * @return The removed or dispensed item if it is available
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match found
   * 
   */
  public Item removeNextItem(String description) {
    // get the index of the next item to dispense by this vending machine

    int index = getIndexNextItem(description); // exceptions throws by this method call should
    // propagate
    // we didn't throw an exception explicitly because getIndexNextItem will throw those and we
    // just propagate the exception down the stack
    // save a copy of the item to dispense
    Item itemToDispense = items[index];
    // remove the item at index using a shift operation
    for (int i = index + 1; i < size; i++) {
      items[i - 1] = items[i];
    }
    items[size - 1] = null;
    size--;
    return itemToDispense; // return the removed item
  }

  /**
   * Returns a summary of the contents of this vending machine in the following format: Each line
   * contains the description or item name followed by one the number of occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary description will be as
   * follows. water (5)\n chocolate (10)\n snack (7) If the vending machine is empty, this method
   * returns an empty string ""
   * 
   * @return a descriptive summary of the contents of the vending machine
   */
  public String getItemsSummary() {
    String summary = ""; // empty string
    // traverse the vending machine and build its items summary description
    for (int i = 0; i < size; i++) {
      // add the item's description and count if not yet processed
      if (!summary.contains(items[i].getDescription())) {
        summary = summary + items[i].getDescription() + " ("
            + getItemOccurrences(items[i].getDescription()) + ")\n";
      }
    }
    return summary.trim(); // return the items' summary
  }


  /**
   * checks if string is blank or not
   * 
   * @param s takes the argument string
   * @return true if string is blank
   */
  private boolean isBlankString1(String s) {
    if (s != null && s.length() > 0) {
      for (int i = 0; i < s.length(); i++) {
        if (!Character.isWhitespace(s.charAt(i))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * private helper method to check if given parameter string is not null or blank
   * 
   * @param s
   * @return true if no exceptional condition occurs
   */
  private boolean findValidString(String s) {
    if (s == null || (s != null && s.length() == 0) || isBlankString1(s)) {
      throw new IllegalArgumentException("invalid string representation");
    }
    return true;
  }

  /**
   * private helper to check if string is not empty after trimming
   * 
   * @param s
   * @return true if no exception occurs
   * @throws DataFormatException if condition is true
   */
  private boolean findCorrectLength(String s) throws DataFormatException {
    if (s.length() < 1) {
      throw new DataFormatException("Invalid input");
    }
    return true;
  }

  /**
   * private helper to check if description and expiration date are not empty or null
   * 
   * @param s
   * @return true if no exception is thrown
   * @throws DataFormatException if condition is true
   */
  private boolean findCorrectRepresentation(String s) throws DataFormatException {
    int i = s.indexOf(":");
    if (s.substring(0, i) == "" || s.substring(i + 2, s.length()) == "" || s.charAt(i + 1) != ' ') {
      throw new DataFormatException("Invalid input");
    }
    return true;
  }

  /**
   * private helper to check if expiration date is parsable to an integer
   * 
   * @param s
   * @return true if it can be parsed
   * @throws Exception if it can't be parsed
   */
  private boolean findCorrectParsableInteger(String s) throws Exception {
    int i = Integer.parseInt((s.substring(s.indexOf(":") + 2, s.length())).trim());
    return true;
  }

  /**
   * private helper which determines that there is only one colon
   * 
   * @param s
   * @return no of colons
   */
  private int findNumOfColon(String s) {
    int c = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ':') {
        c++;
      }
    }
    return c;
  }

  /**
   * Parse an item's string representation and add the corresponding item to this vending machine
   * 
   * @param itemRepresentation a String representation of an item formatted as "description:
   *                           expirationDate". Extra spaces at the beginning and end of the item
   *                           description and expirationDate can be disregarded.
   * @throws IllegalArgumentException with a descriptive error message if itemRepresentation is null
   *                                  or blank
   * @throws DataFormatException      with a descriptive error message if the provided string is not
   *                                  correctly formatted. A correct format of the
   *                                  itemRepresentation is "description: expirationDate". The
   *                                  description must be a NOT blank string. The expirationDate
   *                                  must be a non-empty string parsable to a positive integer. The
   *                                  item's description and its expiration date must be separated
   *                                  by one colon ":". Extra whitespace at the beginning and end of
   *                                  description or expirationDate should be disregarded.
   * @throws IllegalStateException    with a descriptive error message if the vending machine is
   *                                  full
   */
  public void loadOneItem(String itemRepresentation) throws DataFormatException {
    // [HINT] Use String.split() and String.trim() methods to help parsing the itemRepresentation
    // This method MUST call addItem(String, int) to try adding the parsed item to the vending
    // machine

    // This is a complex method. Try to decompose it into steps. We highly recommend breaking its
    // functionality down the way that you see fits using private helper methods.
    /*
     * steps- 1)check valid string -check for null or empty 2)trim string-won't give error on non
     * empty or not null string 3)check length>0 after trimming 4)check position of ":" 5)check if
     * description and expirationdate are not null and there is a space after ":" 6)check if
     * expirationdate doesn't give inputmismatchexception
     */
    // question:if some line throws an exception but is not in try block, then will a catch block
    // ctch that exception or does catch only catches the exception given from inside try block

    if (size == items.length) {
      throw new IllegalStateException("Vending machine FULL. No more items can be loaded.");
    }
    boolean check1 = findValidString(itemRepresentation);// checking if string is valid or not
    itemRepresentation = itemRepresentation.trim();// trimming the string if it is valid
    // checking if after trimming it is not blank
    boolean check2 = findCorrectLength(itemRepresentation);
    // checking if colon is at correct position
    if (itemRepresentation.indexOf(":") == -1 || itemRepresentation.indexOf(":") == 0
        || itemRepresentation.indexOf(":") >= itemRepresentation.length() - 2) {
      throw new DataFormatException("Invalid Input");
    }
    int index = itemRepresentation.indexOf(":");
    // checking if description and expirationDate are valid or not
    if (itemRepresentation.substring(0, index) != null
        && itemRepresentation.substring(index + 1, itemRepresentation.length()) != null) {
      String[] a1 = itemRepresentation.split(":");
      if (a1.length == 2) {
        if (a1[0] != null) {
          String des = a1[0];
        } else {
          throw new DataFormatException("Invalid Input");
        }
        if (a1[1] != null) {
          String exp = a1[1];
        } else {
          throw new DataFormatException("Invalid Input");
        }
        // Checking if next character after colon is whitespace or number only
        if (!(Character.isDigit(a1[1].charAt(0)) || Character.isWhitespace(a1[1].charAt(0)))) {
          throw new DataFormatException("Invalid Input");
        }
        a1[0] = a1[0].trim();
        a1[1] = a1[1].trim();
        // checking if expirationDate is parsable to an integer
        try {
          boolean check4 = findCorrectParsableInteger(a1[1]);
        } catch (Exception e) {
          throw new DataFormatException("Invalid Input");
        }
        addItem(a1[0], Integer.parseInt(a1[1]));
      } else {
        throw new DataFormatException("Invalid Input");
      }
    } else {
      throw new DataFormatException("Invalid Input");
    }

    /*
     * if (size == items.length) { throw new
     * IllegalStateException("Vending machine FULL. No more items can be loaded."); } boolean check1
     * = findValidString(itemRepresentation); String s = itemRepresentation.trim(); boolean check2 =
     * findCorrectLength(s); int extra = findNumOfColon(s);
     * 
     * if (extra != 1 && s.indexOf(":") == -1 || s.indexOf(":") == 0 || s.indexOf(":") >= s.length()
     * - 2) { throw new DataFormatException("Invalid Input"); } boolean check3 =
     * findCorrectRepresentation(s); try { boolean check4 = findCorrectParsableInteger(s); } catch
     * (Exception e) { throw new DataFormatException("Invalid Input"); }
     * 
     * addItem(s.substring(0, s.indexOf(":")), Integer.parseInt(s.substring(s.indexOf(":") + 2,
     * s.length())));
     */
    /*
     * if (size == items.length) { throw new
     * IllegalStateException("Vending machine FULL. No more items can be loaded."); } boolean check1
     * = findValidString(itemRepresentation); String s = itemRepresentation.trim(); boolean check2 =
     * findCorrectLength(s); int extra = findNumOfColon(s);
     * 
     * if (extra != 1 && s.indexOf(":") == -1 || s.indexOf(":") == 0 || s.indexOf(":") >= s.length()
     * - 2) { throw new DataFormatException("Invalid Input"); } boolean check3 =
     * findCorrectRepresentation(s); try { boolean check4 = findCorrectParsableInteger(s); } catch
     * (Exception e) { throw new DataFormatException("Invalid Input"); }
     * 
     * addItem(s.substring(0, s.indexOf(":")), Integer.parseInt(s.substring(s.indexOf(":") + 2,
     * s.length())));
     */
    /*
     * if (size == items.length) {
     * 
     * throw new IllegalStateException("Vending machine FULL. No more items can be loaded."); }
     * boolean check1 = findValidString(itemRepresentation); String s = itemRepresentation.trim();
     * boolean check2 = findCorrectLength(s); int extra = findNumOfColon(s);
     * 
     * if (extra != 1 && s.indexOf(":") == -1 || s.indexOf(":") == 0 || s.indexOf(":") >= s.length()
     * - 2) { throw new DataFormatException("Invalid Input"); } boolean check3 =
     * findCorrectRepresentation(s); try { boolean check4 = findCorrectParsableInteger(s); } catch
     * (Exception e) { throw new DataFormatException("Invalid Input"); }
     * 
     * addItem(s.substring(0, s.indexOf(":")), Integer.parseInt(s.substring(s.indexOf(":") + 2,
     * s.length())));
     */
    /*
     * if (size == items.length) { throw new
     * IllegalStateException("Vending machine FULL. No more items can be loaded."); } boolean check1
     * = findValidString(itemRepresentation); String s = itemRepresentation.trim(); boolean check2 =
     * findCorrectLength(s); if ( s.indexOf(":") == -1 || s.indexOf(":") == 0 || s.indexOf(":") >=
     * s.length() - 2 || s.charAt(s.indexOf(":")+1)!=' ') { throw new
     * DataFormatException("Invalid Input"); } String[] desexp=s.split(":");
     * desexp[0].trim();desexp[1].trim(); if(desexp[0]==null || (desexp[0]!=null &&
     * desexp[0].length()==0) || isBlankString1(desexp[0]) || desexp[1]=="") { throw new
     * DataFormatException("Invalid input"); } try { boolean check4 =
     * findCorrectParsableInteger(desexp[1]); } catch (Exception e) { throw new
     * DataFormatException("Invalid Input"); }
     * 
     * addItem(desexp[0],Integer.parseInt(desexp[1]));
     */
    /*
     * another implementation in one go if(items.length==size) { throw new
     * IllegalStateException("the vending machine is full"); } if(itemRepresentation==null ||
     * itemRepresentation.length()<1) { throw new
     * IllegalArgumentException("the passed string is invalid"); } else { String
     * s1=itemRepresentation.trim();//can give error for empty string if(s1!="") { //all these
     * conditions work until description doesn't contain any colon if(s1.indexOf(":")==-1 ||
     * s1.charAt(s1.indexOf(":")+1)!=' ' || s1.indexOf(":")==0 || s1.indexOf(":")==s1.length()-1) {
     * throw new DataFormatException("invalid value given"); } try { String[] s=s1.split(" ");//can
     * give exception if empty string is there or null string String
     * des=s[0].substring(0,s[0].length()-1);//can give SIOOB exception String
     * special=s[0].substring(s[0].length()-1,s[0].length()); String exp=s[1]; int
     * num=Integer.parseInt(exp);//can give input mismatch exception-what to do? if(num<0) { throw
     * new DataFormatException("invalid value given"); } } catch(Exception e) { throw new
     * DataFormatException("invalid value given"); } } throw new
     * DataFormatException("invalid value given"); }
     */
  }

  /**
   * Reads and parses the file passed as input line by line and loads the corresponding items to the
   * vending machine. Each line in the file represents an item description formatted as
   * "description: expirationDate". Blank and badly formatted lines must be skipped.
   * 
   * Displays "Vending machine FULL. No more items can be loaded." when trying to load a new item to
   * the vending machine if it is or becomes full.
   * 
   * @param file file to load items from
   * @return the total number of new items loaded to this vending machine
   * @throws FileNotFoundException if the file object does not correspond to an actual file within
   *                               the file system.
   */
  public int loadItems(File file) throws FileNotFoundException {
    // Create and use a java.util.Scanner object to open and read the file
    // This method MUST call the loadOneItem(String) method to operate while parsing each line

    // Notice carefully that this method does not throw any exception if the vending machine is full
    // or becomes full while trying to lead an item.
    Scanner sc = new Scanner(file);
    int prev_size = size();// previous size to be substracted
    // ask how to incorporate "checking for end of file reaching" in java so we can use OR
    // from professor or TAs
try {
  
    while (size() < items.length) {

      if (sc.hasNextLine()) {
        String s = sc.nextLine();
        System.out.println(s);
        if (s != "") {
          try {
            loadOneItem(s);
          } catch (DataFormatException | IllegalArgumentException | IllegalStateException e) {
          }

        }
      }

      else {
        break;
      }



    }
}

finally {
    sc.close();}

    /*
     * while (size() < items.length) {
     * 
     * if (sc.hasNextLine()) { String s = sc.nextLine(); System.out.println(s); if (s != "") { try {
     * loadOneItem(s); } catch (DataFormatException | IllegalArgumentException |
     * IllegalStateException e) { } finally { sc.close(); }
     * 
     * } }
     * 
     * else { break; }
     * 
     * 
     * 
     * }
     * 
     */
    return size() - prev_size; // default return statement. Feel free to change it.
  }

  /**
   * Saves the summary of this vending machine to the file object passed as input
   * 
   * @param file file object where the vending machine summary will be saved
   */
  public void saveVendingMachineSummary(File file) {
    // You can use either a java.io.PrintWriter or a java.io.FileWriter to write into the file
    // This method MUST call the getItemsSummary() to get the summary of this vending machine to be
    // saved to the file
    // This method MUST NOT throw any exception
/*
    try (FileWriter fw = new FileWriter(file))// using try with resources to automatically close
                                              // a file if exception occurs
    {

      fw.write(getItemsSummary());
    }

    catch (IOException e) {
      System.out.println("output file not found");
    }

*/
    FileWriter fw=null;
    try {
      fw=new FileWriter(file);
      fw.write(getItemsSummary());
    }
    catch(IOException e)
    {
      System.out.println("output file not found");
    }
    finally {
      try {
      fw.close();}
      catch(IOException e)
      {}
    }
  }
}
