package cpuscheduling;

import java.util.LinkedList;
import java.util.Queue;

/** First-Come, First-Serve scheduler. */
public class FcfsScheduler {
  /** Queue of processes. */
  private Queue<Process> queue;

  /** The process currently using the CPU. */
  private Process currentProcess;

  /** Constructor for the FcfsScheduler class. */
  public FcfsScheduler() {
    this.queue = new LinkedList<>();
    this.currentProcess = null;
  }

  /** Run one cycle of this scheduler. */
  public void cycle() {
    // TODO: Cut down on if statements
    if (this.currentProcess == null) {
      if (this.getSizeOfQueue() != 0) {
        this.currentProcess = this.queue.remove();
      } else {
        return;
      }
    }
    this.currentProcess.decrementRemainingBurst();

    if (this.currentProcess.getRemainingBurstTime() == 0) {
      if (this.getSizeOfQueue() != 0) {
        this.currentProcess = this.queue.remove();
      } else {
        this.currentProcess = null;
      }
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
}
