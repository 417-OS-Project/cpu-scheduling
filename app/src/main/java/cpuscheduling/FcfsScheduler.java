package cpuscheduling;

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

  /** Run one cycle of this scheduler. */
  public void cycle() {
    // TODO: Cut down on if statements
    if (this.currentProcess == null) {
      if (this.getSizeOfQueue() != 0
          && this.queue.peek().getArrivalTime() <= this.stats.getTotalElapsedTime()) {
        this.currentProcess = this.queue.remove();
      } else {
        // The null case
        stats.updateStats(this.currentProcess);
        return;
      }
    }
    this.currentProcess.decrementRemainingBurst();
    stats.updateStats(this.currentProcess);

    if (this.currentProcess.getRemainingBurstTime() == 0) {
      this.currentProcess = null;
    }
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
    if (this.currentProcess == null) {
      return 0;
    }
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
   * @return True if there is a current process or queue
   */
  public Boolean canContinue() {
    return this.currentProcess != null || this.getSizeOfQueue() != 0;
  }
}
