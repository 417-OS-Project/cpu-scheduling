package cpuscheduling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/** Shortest Job First (no preemption) scheduler. */
public class SjfScheduler {
  /** Queue of processes. */
  private Queue<Process> queue;

  /** List of processes once they leave the queue. */
  private ArrayList<Process> list;

  /** The process currently using the CPU. */
  private Process currentProcess;

  /** The stat tracker for this scheduler. */
  private StatTracker stats;

  /** Default constructor for the shortest job first scheduler. */
  public SjfScheduler() {
    this.queue = new LinkedList<>();
    this.list = new ArrayList<>();
    this.currentProcess = null;
    this.stats = new StatTracker();
  }

  /** Run the scheduler until it cannot continue. */
  public void fullCycle() {
    while (canContinue()) {
      cycle();
    }
  }

  /** Run one cycle of this scheduler. */
  public void cycle() {
    updateQueue();
    if (this.currentProcess == null) {
      // Grab the element of the list with the shortest burst.
      if (!this.list.isEmpty()) {
        this.currentProcess = getShortestProcess();
      } else {
        // The null case
        stats.updateStats(null);
        return;
      }
    }

    // No more cycles for current process
    if (this.currentProcess.getRemainingBurstTime() == 0) {
      this.currentProcess = null;
      if (!this.list.isEmpty()) {
        this.currentProcess = getShortestProcess();
      } else if (!canContinue()) {
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
   * Return the shortest job remaining.
   *
   * @return Process with the shortest burst time remaining.
   */
  private Process getShortestProcess() {
    int pos = 0;
    for (int i = 1; i < this.list.size(); i++) {
      if (this.list.get(pos).getRemainingBurstTime() > this.list.get(i).getRemainingBurstTime()) {
        pos = i;
      }
    }
    Process retProcess = list.get(pos);
    list.remove(pos);
    return retProcess;
  }

  /** Check the queue for a new Process arrival. */
  private void updateQueue() {
    if (!this.queue.isEmpty()
        && this.queue.peek().getArrivalTime() <= this.stats.getTotalElapsedTime()) {
      this.list.add(this.queue.remove());
    }
  }

  /**
   * Add a new process to the queue.
   *
   * @param newProcess process to be added.
   */
  public void addProcess(Process newProcess) {
    this.queue.add(newProcess);
  }

  /**
   * Return the current size of the queue.
   *
   * @return size of the queue.
   */
  public int getSizeOfQueue() {
    return this.queue.size();
  }

  /**
   * Return the remaining burst time of the current process.
   *
   * @return the remaining burst time.
   */
  public int getCurrentBurstRemaining() {
    return this.currentProcess.getRemainingBurstTime();
  }

  /**
   * Return the total number of processes that have accessed the CPU.
   *
   * @return total number of processes.
   */
  public int getTotalProcessCount() {
    return this.stats.getTotalProcessCount();
  }

  /**
   * Return the total elapsed time of this scheduler.
   *
   * @return total elapsed time.
   */
  public int getTotalElapsedTime() {
    return this.stats.getTotalElapsedTime();
  }

  /**
   * Return the calculated throughput.
   *
   * @return throughput.
   */
  public double getThroughput() {
    return stats.calculateThroughput();
  }

  /**
   * Return the calculated CPU utilization.
   *
   * @return CPU utilization.
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
   * Utility function to determine if there is a current process, queue, or listed processes.
   *
   * @return true if there is, false if not.
   */
  public Boolean canContinue() {
    return this.currentProcess != null || !this.queue.isEmpty() || !this.list.isEmpty();
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

    FileWriter file = new FileWriter("reports/sjf.txt");

    file.write(this.toString());
    file.close();
  }
}
