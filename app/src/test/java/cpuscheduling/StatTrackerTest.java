package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatTrackerTest {
  @Test
  public void testUpdateStats() {
    StatTracker stats = new StatTracker();
    assertEquals(0, stats.getTotalProcessCount());

    Process p1 = new Process(1, 2, 3);
    stats.updateStats(p1);
    assertEquals(1, stats.getTotalProcessCount());
    stats.updateStats(p1);
    assertEquals(1, stats.getTotalProcessCount());

    stats.updateStats(null);
    assertEquals(1, stats.getTotalProcessCount());

    stats.updateStats(new Process(1, 2, 3));
    assertEquals(2, stats.getTotalProcessCount());
  }
}
