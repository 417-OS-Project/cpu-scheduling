package cpuscheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    try {
      File pFile = new File(args[0]);
      Scanner fileScanner = new Scanner(pFile);

      while (fileScanner.hasNextLine()) {
        System.out.println(fileScanner.nextLine());
      }

      fileScanner.close();
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("File not provided");
      System.exit(-1);
    } catch (FileNotFoundException e) {
      System.out.println("Text file not provided");
      System.exit(-2);
    }
  }
}
