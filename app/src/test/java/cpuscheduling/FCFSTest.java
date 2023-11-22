package cpuscheduling;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class FCFSTest {
  FcfsScheduler fcfs;

  @Before
  public void init() {
    fcfs = new FcfsScheduler();
    ArrayList<Process> parsed;
    parsed = App.parseFile(new File("src/resources/SmallDataFile.txt"));

    while (!parsed.isEmpty()) {
      fcfs.addProcess(parsed.get(0));
      parsed.remove(0);
    }
  }

  @Test
  public void testAddProcess() {
    assertEquals(10, fcfs.getSizeOfQueue());
    assertEquals(0, fcfs.getTotalProcessCount());

    fcfs.addProcess(new Process(3, 4, 5));
    assertEquals(11, fcfs.getSizeOfQueue());
    assertEquals(0, fcfs.getTotalProcessCount());

    fcfs.addProcess(new Process(2, 3, 4));
    assertEquals(12, fcfs.getSizeOfQueue());
    assertEquals(0, fcfs.getTotalProcessCount());
  }

  @Test
  public void testStats() {
    assertEquals(0, fcfs.getTotalProcessCount());
    assertEquals(0, fcfs.getTotalElapsedTime());

    fcfs.cycle();
    assertEquals(0, fcfs.getTotalProcessCount());
    assertEquals(1, fcfs.getTotalElapsedTime());

    for (int i = 0; i < 10; i++) {
      fcfs.cycle();
    }
    assertEquals(21, fcfs.getCurrentBurstRemaining());
    assertEquals(1, fcfs.getTotalProcessCount());
    assertEquals(11, fcfs.getTotalElapsedTime());

    fcfs.fullCycle();

    assertEquals(10, fcfs.getTotalProcessCount());
    assertEquals(338, fcfs.getTotalElapsedTime());
    assertEquals(17.9, fcfs.getThroughput(), 0.01);
    assertEquals(52.96, fcfs.getUtilization(), 0.01);
    assertEquals(7.8, fcfs.getAverageWaitingTime(), 0.01);
    assertEquals(25.7, fcfs.getAverageTurnaroundTime(), 0.01);
    assertEquals(7.8, fcfs.getAverageResponseTime(), 0.01);
  }

  @Test
  public void testCanContinue() {
    assertTrue(fcfs.canContinue());
    fcfs.fullCycle();
    assertFalse(fcfs.canContinue());

    FcfsScheduler emptyFcfs = new FcfsScheduler();
    assertFalse(emptyFcfs.canContinue());

    emptyFcfs.addProcess(new Process(1, 2, 3));
    assertTrue(emptyFcfs.canContinue());
  }

  @Test
  public void testToString() {
    String str = fcfs.toString();
    assertTrue(str.contains(String.valueOf(fcfs.getTotalProcessCount())));
    assertTrue(str.contains(String.valueOf(fcfs.getTotalElapsedTime())));
    assertTrue(str.contains(String.valueOf(fcfs.getThroughput())));
    assertTrue(str.contains(String.valueOf(fcfs.getUtilization())));
    assertTrue(str.contains(String.valueOf(fcfs.getAverageWaitingTime())));
    assertTrue(str.contains(String.valueOf(fcfs.getAverageTurnaroundTime())));
    assertTrue(str.contains(String.valueOf(fcfs.getAverageResponseTime())));

    fcfs.fullCycle();
    str = fcfs.toString();
    assertTrue(str.contains(String.valueOf(fcfs.getTotalProcessCount())));
    assertTrue(str.contains(String.valueOf(fcfs.getTotalElapsedTime())));
    assertTrue(str.contains(String.valueOf(fcfs.getThroughput())));
    assertTrue(str.contains(String.valueOf(fcfs.getUtilization())));
    assertTrue(str.contains(String.valueOf(fcfs.getAverageWaitingTime())));
    assertTrue(str.contains(String.valueOf(fcfs.getAverageTurnaroundTime())));
    assertTrue(str.contains(String.valueOf(fcfs.getAverageResponseTime())));
  }
}
