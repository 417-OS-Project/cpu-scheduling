package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatTrackerTest {
  @Test
  public void testUpdateStats() {
    StatTracker stats = new StatTracker();
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
    StatTracker stats = new StatTracker();
    stats.updateStats(new Process(0, 4, 3));
    stats.updateStats(new Process(2, 5, 3));
    assertEquals(4.5, stats.calculateThroughput(), 0.001);

    stats.updateStats(new Process(34, 6, 3));
    assertEquals(5, stats.calculateThroughput(), 0.001);
  }
}
