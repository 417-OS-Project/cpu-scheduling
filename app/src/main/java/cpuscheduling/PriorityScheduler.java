package cpuscheduling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/** Priority (with Preemption) scheduler. */
public class PriorityScheduler {
  /** Queue of Processes. */
  private Queue<Process> queue;

  /** The process currently using the CPU. */
  Process currentProcess;

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
    while (this.canContinue()) {
      this.cycle();
    }
  }

  /** Conduct one cycle of this scheduler. */
  public void cycle() {
    updateList();
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
  private void updateList() {
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
   * Utility function to determine if the scheduler can continue.
   *
   * @return true if there are more processes, false if not.
   */
  public Boolean canContinue() {
    return this.currentProcess != null || !this.queue.isEmpty() || !this.list.isEmpty();
  }
}
