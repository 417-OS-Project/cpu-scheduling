package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StatTrackerTest {
  StatTracker stats;

  @Before
  public void init() {
    stats = new StatTracker();
  }

  @Test
  public void testUpdateCountAndTime() {
    assertEquals(0, stats.getTotalProcessCount());
    assertEquals(0, stats.getTotalElapsedTime());

    Process p1 = new Process(1, 2, 3);
    stats.updateStats(p1);
    assertEquals(1, stats.getTotalProcessCount());
    assertEquals(1, stats.getTotalElapsedTime());
    stats.updateStats(p1);
    assertEquals(1, stats.getTotalProcessCount());
    assertEquals(2, stats.getTotalElapsedTime());

    stats.updateStats(null);
    assertEquals(1, stats.getTotalProcessCount());
    assertEquals(3, stats.getTotalElapsedTime());

    stats.updateStats(new Process(1, 2, 3));
    assertEquals(2, stats.getTotalProcessCount());
    assertEquals(4, stats.getTotalElapsedTime());
  }

  @Test
  public void testThroughput() {
    Process p1 = new Process(0, 4, 3);
    Process p2 = new Process(0, 5, 3);
    Process p3 = new Process(234, 6, 4);

    for (int i = 0; i < 4; i++) {
      stats.updateStats(p1);
    }
    for (int i = 0; i < 5; i++) {
      stats.updateStats(p2);
    }

    // 9 Cycles / 2 Processes
    assertEquals(4.5, stats.calculateThroughput(), 0.001);

    for (int i = 0; i < 6; i++) {
      stats.updateStats(p3);
    }
    // 15 Cycles / 3 Processes
    assertEquals(5, stats.calculateThroughput(), 0.001);
  }

  @Test
  public void testUtilization() {
    Process p1 = new Process(0, 4, 3);
    Process p2 = new Process(0, 5, 3);

    for (int i = 0; i < 4; i++) {
      stats.updateStats(p1);
    }
    for (int i = 0; i < 5; i++) {
      stats.updateStats(p2);
    }

    // 9 Burst Units / 9 Elapsed Time
    assertEquals(100, stats.calculateUtilization(), 0.001);
    stats.updateStats(null);
    // 9 Burst Units / 10 Elapsed Time
    assertEquals(90, stats.calculateUtilization(), 0.001);
  }

  @Test
  public void testResponseTime() {
    Process p1 = new Process(0, 5, 4);
    Process p2 = new Process(2, 2, 2);

    for (int i = 0; i < 5; i++) {
      stats.updateStats(p1);
    }
    stats.updateStats(p2);

    // (0 + (5-2)) / 2 Total Processes
    assertEquals(stats.calculateAverageResponseTime(), 1.5, 0.01);
  }

  @Test
  public void testWaitingTime() {
    Process p1 = new Process(0, 5, 4);
    Process p2 = new Process(2, 2, 2);

    for(int i = 0; i < 5; i++) {
      p1.decrementRemainingBurst();
      stats.updateStats(p1);
    }

    for(int i = 0; i < 2; i++) {
      p2.decrementRemainingBurst();
      stats.updateStats(p2);
    }

    // (0 + (7 - 2 - 2)) / 2 Total Processes
    assertEquals(stats.calculateAverageWaitingTime(), 1.5, 0.01);

    Process p3 = new Process(7, 3, 3);
    for(int i = 0; i < 3; i++) {
      p3.decrementRemainingBurst();
      stats.updateStats(p3);
    }

    // (0 + 3 + 0) / 3 Total Processes
    assertEquals(stats.calculateAverageWaitingTime(), 1, 0.01);
  }
}
