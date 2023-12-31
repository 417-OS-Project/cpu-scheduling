package cpuscheduling;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** Main application for the cpuscheduling package. */
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

  /**
   * Parse a file for given process information.
   *
   * @param file valid text file.
   * @return collection of processes, empty collection if invalid file.
   */
  public static ArrayList<Process> parseFile(File file) {
    ArrayList<Process> processCollection = new ArrayList<Process>();

    try (Scanner fileScanner = new Scanner(file, StandardCharsets.UTF_8)) {
      while (fileScanner.hasNextLine()) {
        int[] line = parseLine(fileScanner.nextLine());
        if (line != null) {
          processCollection.add(new Process(line));
        }
      }
    } catch (IOException e) {
      return processCollection;
    }

    return processCollection;
  }

  /**
   * Main driver for the cpuscheduling package.
   *
   * @param args containing a text file of process information.
   */
  public static void main(String[] args) throws IOException {
    boolean noArg = args.length < 1;
    if (noArg) {
      System.out.println("File not provided");
      System.exit(-1);
    }
    File dataFile = new File(args[0]);

    ArrayList<Process> processCollection = parseFile(dataFile);

    FcfsScheduler fcfs = new FcfsScheduler();
    SjfScheduler sjf = new SjfScheduler();
    PriorityScheduler pri = new PriorityScheduler();

    while (!processCollection.isEmpty()) {
      Process copyProcess = processCollection.get(0);
      fcfs.addProcess(copyProcess.clone());
      sjf.addProcess(copyProcess.clone());
      pri.addProcess(copyProcess.clone());
      processCollection.remove(0);
    }

    fcfs.fullCycle();
    sjf.fullCycle();
    pri.fullCycle();

    System.out.print(
        "First Come, First Served\n" + String.join("", Collections.nCopies(24, "-")) + "\n");
    System.out.printf(fcfs + "\n");

    System.out.print("Shortest Job First\n" + String.join("", Collections.nCopies(24, "-")) + "\n");
    System.out.printf(sjf + "\n");

    System.out.print(
        "Priority with Preemption\n" + String.join("", Collections.nCopies(24, "-")) + "\n");
    System.out.printf(pri.toString());

    fcfs.toFile();
    pri.toFile();
    sjf.toFile();
  }
}
