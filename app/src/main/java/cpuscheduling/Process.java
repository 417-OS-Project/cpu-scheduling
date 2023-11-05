package cpuscheduling;

/** Process class to store a process' data. */
public class Process {

  /** Global counter for the process id number. */
  private static int PID_COUNTER = 1;

  /** The process id number. */
  private final int pid;

  /** Arrival time of this process. */
  private final int arrivalTime;

  /** The CPU Burst time required of this Process. */
  private int burstTime;

  /**
   * Constructor for the Process class.
   *
   * @param aTime the arrival time for the Process.
   * @param bTime the burst time required for this Process.
   */
  public Process(int aTime, int bTime) {
    this.pid = PID_COUNTER;
    PID_COUNTER++;

    this.arrivalTime = aTime;

    setBurstTime(bTime);
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
}
