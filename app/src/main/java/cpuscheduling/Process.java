package cpuscheduling;

/** Process class to store a process' data. */
public class Process {

  /** The priority of this Process. */
  private int priority;

  /**
   * Constructor for the Process class.
   *
   * @param prior the priority of this Process.
   */
  public Process(int prior) {
    setPriority(prior);
  }

  /**
   * Get the priority of this Process.
   *
   * @return priority of this Process.
   */
  public int getPriority() {
    return this.priority;
  }

  /**
   * Set the priority of this Process.
   *
   * @param prior the priority integer.
   */
  public void setPriority(int prior) {
    if (prior < 0) {
      prior = prior * -1;
    }
    this.priority = prior;
  }
}
