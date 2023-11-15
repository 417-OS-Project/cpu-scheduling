package cpuscheduling;

/** Process class to store a process' data. */
public class Process {

  /** Global counter for the process id number. */
  private static int PID_COUNTER = 1;

  /** The process id number. */
  private final int pid;

  /** The arrival time of this Process. */
  private final int arrivalTime;

  /** The CPU Burst time required of this Process. */
  private int burstTime;

  /** The priority of this Process. */
  private int priority;

  /**
   * Multi-integer constructor for the Process.
   *
   * @param aTime the arrival time for the Process.
   * @param bTime the burst time required for this Process.
   * @param prior the priority of this Process.
   */
  public Process(int aTime, int bTime, int prior) {
    this.pid = PID_COUNTER;
    PID_COUNTER++;

    this.arrivalTime = aTime;

    setBurstTime(bTime);
    setPriority(prior);
  }

  /**
   * Array constructor for the Process.
   *
   * @param data the 3-element array of data for the Process.
   */
  public Process(int[] data) {
    this.pid = PID_COUNTER;
    PID_COUNTER++;

    this.arrivalTime = data[0];
    setBurstTime(data[1]);
    setPriority(data[2]);
  }

  /**
   * Get the pid of this Process.
   *
   * @return process id.
   */
  public int getPid() {
    return this.pid;
  }

  /**
   * Get the arrival time of this Process.
   *
   * @return arrival time.
   */
  public int getArrivalTime() {
    return this.arrivalTime;
  }

  /**
   * Get this Process' CPU burst time.
   *
   * @return the burst time.
   */
  public int getBurstTime() {
    return this.burstTime;
  }

  /**
   * Set this Process' CPU burst time.
   *
   * @param time required CPU burst time.
   */
  public void setBurstTime(int time) {
    if (time < 0) {
      time = (time * -1);
    }
    this.burstTime = time;
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

  /**
   * Return a string representation of this Process.
   *
   * @return String of Process.
   */
  public String toString() {
    return String.format("%d %d %d %d", getPid(), getArrivalTime(), getBurstTime(), getPriority());
  }
}
