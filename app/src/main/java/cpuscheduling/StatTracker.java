package cpuscheduling;

import java.util.ArrayList;

/** Stat tracker for scheduling classes. */
public class StatTracker {
  /** The total number of processes. */
  private int totalNumOfProcesses;

  /** The total elapsed time. */
  private int totalElapsedTime;

  /** The total burst time of all processes. */
  private int totalBurstTime;

  /**
   * The total response time of all processes.
   */
  private int totalResponseTime;

  /** List of process PIDs that have accessed the CPU. */
  private ArrayList<Integer> pidTrack;

  /** Constructor for the StatTracker. */
  public StatTracker() {
    this.totalNumOfProcesses = 0;
    this.totalElapsedTime = 0;
    this.totalBurstTime = 0;
    this.totalResponseTime = 0;
    this.pidTrack = new ArrayList<>();
  }

  /**
   * Update the current stats.
   *
   * @param process the current process, pass null if no current process.
   */
  public void updateStats(Process process) {
    if (process != null) {
      if (!pidTrack.contains(process.getPid())) {
        this.pidTrack.add(process.getPid());
        this.totalNumOfProcesses++;
        this.totalResponseTime += (this.totalElapsedTime - process.getArrivalTime());
      }
      this.totalBurstTime++;
    }
    this.totalElapsedTime++;
  }

  /**
   * Return the total number of processes that have accessed the CPU.
   *
   * @return total number of processes.
   */
  public int getTotalProcessCount() {
    return this.totalNumOfProcesses;
  }

  /**
   * Return the total elapsed time.
   *
   * @return total elapsed time.
   */
  public int getTotalElapsedTime() {
    return this.totalElapsedTime;
  }

  /**
   * Return the total response time.
   *
   * @return total response time.
   */
  public double getTotalResponseTime() {
    return ((double)this.totalResponseTime / this.totalNumOfProcesses);
  }

  /**
   * Return the calculated throughput.
   *
   * @return calculated throughput.
   */
  public double calculateThroughput() {
    return ((double) this.totalBurstTime / this.totalNumOfProcesses);
  }

  /**
   * Return the calculated CPU utilization.
   *
   * @return CPU utilization.
   */
  public double calculateUtilization() {
    return (((double) this.totalBurstTime / this.totalElapsedTime * 100));
  }
}
