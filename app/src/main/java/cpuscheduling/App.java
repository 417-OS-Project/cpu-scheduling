package cpuscheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
  /**
   * Parses the line provided to create Process objects.
   *
   * @param line containing the process information.
   * @return Process object.
   */
  public String parseLine(String line) {
    return "Hello World!";
  }

  public static void main(String[] args) {
    boolean noArg = args.length < 1;
    if (noArg) {
      System.out.println("File not provided");
      System.exit(-1);
    }
    File dFile = new File(args[0]);

    try (Scanner fileScanner = new Scanner(dFile, "UTF-8")) {
      while (fileScanner.hasNextLine()) {
        System.out.println(fileScanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      System.out.println("Text file not provided");
      System.exit(-1);
    }
  }
}
