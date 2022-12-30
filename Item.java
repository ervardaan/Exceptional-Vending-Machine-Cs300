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
/**
 * This class creates an item to be a content in the vending machine
 * 
 * @author vardaan kapoor
 */
public class Item {
  private int expirationDate;
  // a private instance data field representing the expiration date of the item, starting
  // at day 0, which represents Jan 1, 2023.
  private String description;
//private instance variable which stores description of the item
  /**
   * checks if string is blank-not used isBlank() method
   * @param s gets a string to check
   * @return true if string is empty /blank
   */
  private boolean isBlankString(String s) {
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
   * Creates a new Item Object with a specific expiration date and description
   * 
   * @param description    takes argument of description
   * @param expirationDate takes argument of expiration date
   * @throws IllegalArgumentException - with a descriptive error message if expirationDate is
   *                                  negative (less than zero) or description is null or blank
   */
  public Item(String description, int expirationDate)

  {
    if (description == null || (description != null && description.length() == 0)
        || isBlankString(description) || description.isBlank() || expirationDate < 0) 
    //checking if description is valid and expirationDate is also valid
    {
      throw new IllegalArgumentException("Either description or expiration date is not valid");

    }
    this.description = description;
    this.expirationDate = expirationDate;
  }

  /**
   * Gets the description of this item
   * 
   * @return description of item
   */
  public String getDescription() {
    return description;
  }

  /**
   * Changes the description of this item
   * 
   * @param description
   * @throws IllegalArgumentException-with a descriptive error message if description is null/blank
   */
  public void setDescription(String description) {
    if (description == null || (description != null && description.length() == 0)
        || isBlankString(description) || description.isBlank()) {
      throw new IllegalArgumentException("Description is null or empty");
    }
    this.description = description;
  }

  /**
   * Gets the expiration date of this item
   * 
   * @return the expiration date of this item
   */
  public int getExpirationDate() {
    return expirationDate;
  }

  /**
   * toString() method of Object super class
   * 
   * @return a String representation of this item formatted as "description: expirationDate"
   * 
   */
  @Override
  public String toString() {
    return this.getDescription() + ": " + this.getExpirationDate();
  }

  /**
   * Checks whether this item equals another object passed as input.
   * 
   * @return true if other is instance of Item and has the same description as this item, false
   *         otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Item && ((Item) other).getDescription().equals(this.getDescription()))
    // casting Object class' object to Item class' object
    {
      return true;
    } else {
      return false;
    }
  }

}

