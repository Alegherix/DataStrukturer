package IntroSvar;

import java.util.Scanner;

public class GuessNumber {
  private int high   = 100;
  private int low    = 1;
  private int guess  = (low + high) / 2;
  private Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    GuessNumber guessNumber = new GuessNumber();
    guessNumber.run();
  }

  public void run() {
    int i = 1;

    printIntro();

    do {
      System.out.print(i++ + ". ");
    } while (!guess());

    System.out.println("\nYour number was: " + guess);
  }

  private void printIntro() {
    String msg = "Hi! I am going to figure out the number you are thinking about!\n\n"
               + "Please answer if your number is '(h)igher' or '(l)ower'\n";

    System.out.println(msg);
  }

  private boolean guess() {
    System.out.print("Is your number: " + guess + "?\n> ");
    String answer = in.next();

    if (answer.startsWith("h"))
      low  = guess;
    else
      high = guess;

    guess = (low + high) / 2;

    return (high - low) <= 2;
  }
}
