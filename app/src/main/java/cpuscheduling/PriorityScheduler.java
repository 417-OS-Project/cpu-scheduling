package cpuscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/** Priority (with Preemption) scheduler. */
public class PriorityScheduler {
  /** Queue of Processes. */
  private Queue<Process> queue;

  /** The process currently using the CPU. */
  private Process currentProcess;

  /** List of available processes. */
  private ArrayList<Process> list;

  /** Stat tracker for this scheduler. */
  StatTracker stats;

  /** Constructor for the Priority scheduler. */
  public PriorityScheduler() {
    this.queue = new LinkedList<>();
    this.currentProcess = null;
    this.list = new ArrayList<>();
    this.stats = new StatTracker();
  }

  /** Run the scheduler until it cannot anymore. */
  public void fullCycle() {
    while (canContinue()) {
      cycle();
    }
  }

  /** Conduct one cycle of this scheduler. */
  public void cycle() {
    updatePriority();

    if (this.currentProcess == null) {
      if (!this.list.isEmpty()) {
        this.currentProcess = this.list.remove(0);
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
        this.currentProcess = this.list.remove(0);
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

  /** Update the list when a new Process arrives. */
  private void updatePriority() {
    if (!this.queue.isEmpty()
        && this.queue.peek().getArrivalTime() <= this.stats.getTotalElapsedTime()) {
      Process toAdd = this.queue.remove();

      if (this.currentProcess != null && this.currentProcess.getRemainingBurstTime() != 0) {
        if (toAdd.getPriority() < this.currentProcess.getPriority()) {
          this.list.add(this.currentProcess);
          this.currentProcess = toAdd;
          sortList();
          return;
        }
      }
      this.list.add(toAdd);
      sortList();
    }
  }

  /** Sort the list based on Process' priority number. */
  private void sortList() {
    Collections.sort(
        this.list,
        new Comparator<Process>() {
          @Override
          public int compare(Process o1, Process o2) {
            if (o1.getPriority() == o2.getPriority()) {
              return 0;
            }
            // RHS should be front
            return o1.getPriority() > o2.getPriority() ? 1 : -1;
          }
        });
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
   * Return the size of the current queue.
   *
   * @return size of queue.
   */
  public int getSizeOfQueue() {
    return this.queue.size();
  }

  /**
   * Return the burst time remaining on the current process.
   *
   * @return remaining burst time.
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
    return stats.getTotalProcessCount();
  }

  /**
   * Return the total elapsed time of this scheduler.
   *
   * @return total elapsed time.
   */
  public int getTotalElapsedTime() {
    return stats.getTotalElapsedTime();
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
   * Utility function to determine if the scheduler can continue.
   *
   * @return true if there are more processes, false if not.
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
}
