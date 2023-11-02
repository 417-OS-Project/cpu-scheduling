package cpuscheduling;

/** Process class to store a process' data. */
public class Process {

  /** Arrival time of this process. */
  private final int arrivalTime;

  /**
   * Constructor for the Process class.
   *
   * @param time the arrival time for the Process.
   */
  public Process(int time) {
    this.arrivalTime = time;
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
