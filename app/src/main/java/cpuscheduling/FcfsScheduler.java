package cpuscheduling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/** First-Come, First-Serve scheduler. */
public class FcfsScheduler {
  /** Queue of processes. */
  private Queue<Process> queue;

  /** The process currently using the CPU. */
  private Process currentProcess;

  /** The stat tracker for this scheduler. */
  private StatTracker stats;

  /** Constructor for the FcfsScheduler class. */
  public FcfsScheduler() {
    this.queue = new LinkedList<>();
    this.currentProcess = null;
    this.stats = new StatTracker();
  }

  /** Fully cycle through this scheduler. */
  public void fullCycle() {
    while (canContinue()) {
      cycle();
    }
  }

  /** Run one cycle of this scheduler. */
  public void cycle() {
    if (this.currentProcess == null) {
      // Grab top of queue and continue
      if (canPop()) {
        this.currentProcess = this.queue.remove();
      } else {
        // The null case
        stats.updateStats(this.currentProcess);
        return;
      }
    }

    // No more cycles for current process
    if (this.currentProcess.getRemainingBurstTime() == 0) {
      this.currentProcess = null;
      if (canPop()) {
        this.currentProcess = this.queue.remove();
      } else if (this.queue.isEmpty()) {
        // Nothing left to do
        return;
      } else {
        stats.updateStats(null);
        return;
      }
    }

    this.currentProcess.decrementRemainingBurst();
    stats.updateStats(this.currentProcess);
  }

  /**
   * Determine if the top of the stack is ready to pop.
   *
   * @return true if there is an element and its arrival time has occurred.
   */
  private Boolean canPop() {
    return !this.queue.isEmpty()
        && this.queue.peek().getArrivalTime() <= this.stats.getTotalElapsedTime();
  }

  /**
   * Add a new process to the queue.
   *
   * @param newProcess the new Process to be added to the back of the queue.
   */
  public void addProcess(Process newProcess) {
    this.queue.add(newProcess);
  }

  /**
   * Get the size of the current queue.
   *
   * @return size of the queue.
   */
  public int getSizeOfQueue() {
    return this.queue.size();
  }

  /**
   * Return the remaining bursts left on the current Process.
   *
   * @return current process' remaining burst time.
   */
  public int getCurrentBurstRemaining() {
    return this.currentProcess.getRemainingBurstTime();
  }

  /**
   * Return the total number of processes that have accessed the CPU.
   *
   * @return total process count.
   */
  public int getTotalProcessCount() {
    return this.stats.getTotalProcessCount();
  }

  /**
   * Return the total elapsed time.
   *
   * @return total elapsed time.
   */
  public int getTotalElapsedTime() {
    return this.stats.getTotalElapsedTime();
  }

  /**
   * Return the calculated throughput.
   *
   * @return current throughput.
   */
  public double getThroughput() {
    return stats.calculateThroughput();
  }

  /**
   * Return the calculated CPU utilization.
   *
   * @return current CPU utilization.
   */
  public double getUtilization() {
    return stats.calculateUtilization();
  }

  /**
   * Return the average waiting time.
   *
   * @return average waiting time.
   */
  public double getAverageWaitingTime() {
    return stats.calculateAverageWaitingTime();
  }

  /**
   * Return the average turnaround time.
   *
   * @return average turnaround time.
   */
  public double getAverageTurnaroundTime() {
    return stats.calculateAverageTurnaroundTime();
  }

  /**
   * Return the average response time.
   *
   * @return average response time.
   */
  public double getAverageResponseTime() {
    return stats.calculateAverageResponseTime();
  }

  /**
   * Utility function to determine if there is a current process or queue.
   *
   * @return True if there is a current process or queue.
   */
  public Boolean canContinue() {
    return this.currentProcess != null || !this.queue.isEmpty();
  }

  /**
   * Return a string representation of this scheduler.
   *
   * @return string representation.
   */
  public String toString() {
    String retString = "";

    retString += "Total Process Count: " + this.getTotalProcessCount() + "\n";
    retString += "Total Elapsed Time: " + this.getTotalElapsedTime() + "\n";
    retString += "Throughput: " + this.getThroughput() + "\n";
    retString += "CPU Utilization: " + this.getUtilization() + "\n";
    retString += "Average Waiting Time: " + this.getAverageWaitingTime() + "\n";
    retString += "Average Turnaround Time: " + this.getAverageTurnaroundTime() + "\n";
    retString += "Average Response Time: " + this.getAverageResponseTime() + "\n";
    return retString;
  }

  /** Write the to string to a text file located in the reports/ directory. */
  public void toFile() throws IOException {
    File output = new File("reports");
    if (!output.exists()) {
      output.mkdir();
    }

    FileWriter file = new FileWriter("reports/fcfs.txt");

    file.write(this.toString());
    file.close();
  }
}
