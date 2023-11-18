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
    Process p1 = new Process(0, 4, 3);
    Process p2 = new Process(0, 5, 3);
    Process p3 = new Process(234, 6, 4);

    for(int i = 0; i < 4; i++) {
      stats.updateStats(p1);
    }
    for(int i = 0; i < 5; i++) {
      stats.updateStats(p2);
    }
    assertEquals(4.5, stats.calculateThroughput(), 0.001);

    for(int i = 0; i < 6; i++) {
      stats.updateStats(p3);
    }
    assertEquals(5, stats.calculateThroughput(), 0.001);
  }

  @Test
  public void testUtilization() {
    assert false;
  }
}
