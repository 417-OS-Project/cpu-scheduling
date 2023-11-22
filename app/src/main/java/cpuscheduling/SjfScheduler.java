package cpuscheduling;

import java.util.LinkedList;
import java.util.Queue;

/** Shortest Job First (no preemption) scheduler. */
public class SjfScheduler {
  /** Queue of processes. */
  private Queue<Process> queue;

  /** The process currently using the CPU. */
  private Process currentProcess;

  /** The stat tracker for this scheduler. */
  private StatTracker stats;

  /** Default constructor for the shortest job first scheduler. */
  public SjfScheduler() {
    this.queue = new LinkedList<>();
    this.currentProcess = null;
    this.stats = new StatTracker();
  }

  /** Run the scheduler until it cannot continue. */
  public void fullCycle() {
    return;
  }

  /** Run one cycle of this scheduler. */
  public void cycle() {
    return;
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
    return 0;
  }

  /**
   * Return the total number of processes that have accessed the CPU.
   *
   * @return total number of processes.
   */
  public int getTotalProcessCount() {
    return 0;
  }

  /**
   * Return the total elapsed time of this scheduler.
   *
   * @return total elapsed time.
   */
  public int getTotalElapsedTime() {
    return 0;
  }

  /**
   * Utility function to determine if there is a current process or queue.
   *
   * @return true if there is, false if not.
   */
  public Boolean canContinue() {
    return !(this.currentProcess == null) || !this.queue.isEmpty();
  }
}
