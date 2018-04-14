package main.java;

import java.util.Scanner;

/**
 * @author pvobbilireddy
 *
 */
public class InputKey {

  private Scanner input; // reads data from the command line

  public InputKey() {
    input = new Scanner(System.in);
  }

  // return an integer value entered by user
  public int getInput() {
    return input.nextInt(); // we assume that user enters an integer
  }

}
