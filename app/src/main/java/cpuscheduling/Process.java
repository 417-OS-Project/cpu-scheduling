package cpuscheduling;

/** Process class to store a process' data. */
public class Process {

  /** Global counter for the process id number. */
  private static int PID_COUNTER = 1;

  /** The process id number. */
  private final int pid;

  /** Constructor for the Process class. */
  public Process() {
    this.pid = PID_COUNTER;
    PID_COUNTER++;
  }

  /**
   * Get the pid.
   *
   * @return process id.
   */
  public int getPid() {
    return this.pid;
  }
}
