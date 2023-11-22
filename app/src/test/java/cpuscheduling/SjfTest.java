package cpuscheduling;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class SjfTest {
  SjfScheduler sjfFull = new SjfScheduler();

  @Before
  public void init() {
    ArrayList<Process> parsed;
    parsed = App.parseFile(new File("src/resources/SmallDataFile.txt"));

    while (!parsed.isEmpty()) {
      sjfFull.addProcess(parsed.get(0));
      parsed.remove(0);
    }
  }

  @Test
  public void testAddProcess() {
    SjfScheduler sjf = new SjfScheduler();
    assertEquals(0, sjf.getSizeOfQueue());

    sjf.addProcess(new Process(1, 2, 3));
    assertEquals(1, sjf.getSizeOfQueue());

    assertEquals(10, sjfFull.getSizeOfQueue());
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
    assertEquals(17.9, sjfFull.getThroughput(), 0.001);
    assertEquals(52.958, sjfFull.getUtilization(), 0.001);
    assertEquals(5.8, sjfFull.getAverageResponseTime(), 0.001);
  }

  @Test
  public void testCanContinue() {
    assertTrue(sjfFull.canContinue());

    SjfScheduler sjf = new SjfScheduler();
    assertFalse(sjf.canContinue());

    sjf.addProcess(new Process(1, 1, 1));
    assertTrue(sjf.canContinue());
  }
}
