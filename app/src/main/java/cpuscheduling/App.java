package cpuscheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
  /**
   * Parses the line provided to create Process objects.
   *
   * @param line containing the process information.
   * @return array of integers.
   */
  public static int[] parseLine(String line) {
    String[] splitStr = line.split("\\s+");
    if (splitStr.length > 3) {
      return null;
    }

    int[] values = new int[3];
    try {
      for (int i = 0; i < 3; i++) {
        values[i] = Integer.parseInt(splitStr[i]);
      }
    } catch (NumberFormatException e) {
      return null;
    }
    return values;
  }

  public static void main(String[] args) {
    boolean noArg = args.length < 1;
    if (noArg) {
      System.out.println("File not provided");
      System.exit(-1);
    }
    File dFile = new File(args[0]);

    ArrayList<Process> processCollection = new ArrayList<Process>();
    try (Scanner fileScanner = new Scanner(dFile, "UTF-8")) {
      while (fileScanner.hasNextLine()) {
        int[] line = parseLine(fileScanner.nextLine());
        if (line != null) {
          processCollection.add(new Process(line));
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Text file not provided");
      System.exit(-1);
    }

    for (Process process : processCollection) {
      System.out.println(process.toString());
    }
  }
}
