package cpuscheduling;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class PriorityTest {
  PriorityScheduler pFull;

  @Before
  public void init() {
    pFull = new PriorityScheduler();
    ArrayList<Process> parsed;
    parsed = App.parseFile(new File("src/resources/SmallDataFile.txt"));

    while (!parsed.isEmpty()) {
      pFull.addProcess(parsed.get(0));
      parsed.remove(0);
    }
  }

  @Test
  public void testAddProcess() {
    assertEquals(10, pFull.getSizeOfQueue());
    assertEquals(0, pFull.getTotalProcessCount());

    pFull.addProcess(new Process(1, 2, 3));
    assertEquals(11, pFull.getSizeOfQueue());
    assertEquals(0, pFull.getTotalProcessCount());
  }

  @Test
  public void testStats() {
    assertEquals(0, pFull.getTotalProcessCount());
    assertEquals(0, pFull.getTotalElapsedTime());

    pFull.cycle();
    assertEquals(0, pFull.getTotalProcessCount());
    assertEquals(1, pFull.getTotalElapsedTime());

    for (int i = 0; i < 10; i++) {
      pFull.cycle();
    }
    assertEquals(1, pFull.getTotalProcessCount());
    assertEquals(11, pFull.getTotalElapsedTime());
    assertEquals(21, pFull.getCurrentBurstRemaining());

    pFull.fullCycle();
    assertEquals(10, pFull.getTotalProcessCount());
    assertEquals(338, pFull.getTotalElapsedTime());
    assertEquals(17.9, pFull.getThroughput(), 0.01);
    // Utilization
    assertEquals(9, pFull.getAverageWaitingTime(), 0.01);
    assertEquals(26.9, pFull.getAverageTurnaroundTime(), 0.01);;
    // Response time
  }
}
