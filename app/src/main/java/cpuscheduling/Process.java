package cpuscheduling;

/** Process class to store a process' data. */
public class Process {

  /** Global counter for the process id number. */
  private static int PID_COUNTER = 1;

  /** The process id number. */
  private final int pid;

  /** Arrival time of this process. */
  private final int arrivalTime;

  /**
   * Constructor for the Process class.
   *
   * @param time the arrival time for the Process.
   */
  public Process(int time) {
    this.pid = PID_COUNTER;
    PID_COUNTER++;

    this.arrivalTime = time;
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
  int getArrivalTime() {
    return this.arrivalTime;
  }
}
