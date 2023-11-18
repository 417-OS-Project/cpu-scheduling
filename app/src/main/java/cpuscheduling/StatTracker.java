package cpuscheduling;

import java.util.ArrayList;

/** Stat tracker for scheduling classes. */
public class StatTracker {
  /** The total number of processes. */
  private int totalNumOfProcesses;

  /** List of process PIDs that have accessed the CPU. */
  private ArrayList<Integer> pidTrack;

  /** Constructor for the StatTracker. */
  public StatTracker() {
    this.totalNumOfProcesses = 0;
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
      }
    }
  }

  /**
   * Return the total number of processes that have accessed the CPU.
   *
   * @return total number of processes.
   */
  public int getTotalProcessCount() {
    return this.totalNumOfProcesses;
  }
}
