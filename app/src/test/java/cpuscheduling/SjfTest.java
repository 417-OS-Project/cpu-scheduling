package cpuscheduling;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class SjfTest {
  SjfScheduler sjfFull;

  @Before
  public void init() {
    sjfFull = new SjfScheduler();
    ArrayList<Process> parsed;
    parsed = App.parseFile(new File("src/resources/SmallDataFile.txt"));

    while (!parsed.isEmpty()) {
      sjfFull.addProcess(parsed.get(0));
      parsed.remove(0);
    }
  }

  @Test
  public void testAddProcess() {
    assertEquals(10, sjfFull.getSizeOfQueue());
    assertEquals(0, sjfFull.getTotalProcessCount());

    sjfFull.addProcess(new Process(300, 32, 4));
    assertEquals(11, sjfFull.getSizeOfQueue());
    assertEquals(0, sjfFull.getTotalProcessCount());

    sjfFull.addProcess(new Process(2, 3, 4));
    assertEquals(12, sjfFull.getSizeOfQueue());
    assertEquals(0, sjfFull.getTotalProcessCount());
  }

  @Test
  public void testStats() {
    assertEquals(0, sjfFull.getTotalProcessCount());
    assertEquals(0, sjfFull.getTotalElapsedTime());

    sjfFull.cycle();
    assertEquals(0, sjfFull.getTotalProcessCount());
    assertEquals(1, sjfFull.getTotalElapsedTime());

    for (int i = 0; i < 10; i++) {
      sjfFull.cycle();
    }
    assertEquals(1, sjfFull.getTotalProcessCount());
    assertEquals(11, sjfFull.getTotalElapsedTime());
    assertEquals(21, sjfFull.getCurrentBurstRemaining());

    sjfFull.fullCycle();
    assertEquals(10, sjfFull.getTotalProcessCount());
    assertEquals(338, sjfFull.getTotalElapsedTime());
    assertEquals(17.9, sjfFull.getThroughput(), 0.01);
    assertEquals(52.96, sjfFull.getUtilization(), 0.01);
    assertEquals(5.8, sjfFull.getAverageWaitingTime(), 0.01);
    assertEquals(23.7, sjfFull.getAverageTurnaroundTime(), 0.01);
    assertEquals(5.8, sjfFull.getAverageResponseTime(), 0.01);
  }

  @Test
  public void testCanContinue() {
    assertTrue(sjfFull.canContinue());

    SjfScheduler sjf = new SjfScheduler();
    assertFalse(sjf.canContinue());

    sjf.addProcess(new Process(1, 1, 1));
    assertTrue(sjf.canContinue());
  }

  @Test
  public void testToString() {
    String str = sjfFull.toString();

    assertTrue(str.contains(String.valueOf(sjfFull.getTotalProcessCount())));
    assertTrue(str.contains(String.valueOf(sjfFull.getTotalElapsedTime())));
    assertTrue(str.contains(String.valueOf(sjfFull.getThroughput())));
    assertTrue(str.contains(String.valueOf(sjfFull.getUtilization())));
    assertTrue(str.contains(String.valueOf(sjfFull.getAverageWaitingTime())));
    assertTrue(str.contains(String.valueOf(sjfFull.getAverageTurnaroundTime())));
    assertTrue(str.contains(String.valueOf(sjfFull.getAverageResponseTime())));

    assertTrue(str.contains(String.valueOf(sjfFull.getTotalProcessCount())));
    assertTrue(str.contains(String.valueOf(sjfFull.getTotalElapsedTime())));
    assertTrue(str.contains(String.valueOf(sjfFull.getThroughput())));
    assertTrue(str.contains(String.valueOf(sjfFull.getUtilization())));
    assertTrue(str.contains(String.valueOf(sjfFull.getAverageWaitingTime())));
    assertTrue(str.contains(String.valueOf(sjfFull.getAverageTurnaroundTime())));
    assertTrue(str.contains(String.valueOf(sjfFull.getAverageResponseTime())));
  }
}
