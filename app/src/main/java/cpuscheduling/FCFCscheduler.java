package cpuscheduling;

import java.util.LinkedList;
import java.util.Queue;

/** First-Come, First-Serve scheduler. */
public class FCFCscheduler {
  /** Queue of processes. */
  private Queue<Process> queue;

  /** Constructor for the FCFSscheduler class. */
  public FCFCscheduler() {
    queue = new LinkedList<>();
  }

  /** Run one cycle of this scheduler. */
  public void cycle() {
    return;
  }

  /**
   * Add a new process to the queue.
   *
   * @param newProcess the new Process to be added to the back of the queue.
   */
  public void addProcess(Process newProcess) {
    queue.add(newProcess);
  }

  /**
   * Get the size of the current queue.
   *
   * @return size of the queue.
   */
  public int getSizeOfQueue() {
    return queue.size();
  }
}