package cpuscheduling;

/** Process class to store a process' data. */
public class Process {

  /** The CPU Burst time required of this Process. */
  private int burstTime;

  /**
   * Constructor for the Process class.
   *
   * @param bTime the burst time required for this Process.
   */
  public Process(int bTime) {
    setBurstTime(bTime);
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
      this.burstTime = (time * -1);
      return;
    }
    this.burstTime = time;
  }
}
