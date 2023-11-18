package cpuscheduling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class FCFSTest {
  ArrayList<Process> list = new ArrayList<>();

  @Before
  public void init() {
    list.add(new Process(0, 24, 1));
    list.add(new Process(0, 3, 6));
    list.add(new Process(0, 4, 8));
  }

  @Test
  public void testAddProcess() {
    FcfsScheduler fcfs = new FcfsScheduler();
    assertEquals(0, fcfs.getSizeOfQueue());

    fcfs.addProcess(new Process(3, 4, 5));
    assertEquals(1, fcfs.getSizeOfQueue());
    assertEquals(0, fcfs.getTotalProcessCount());

    FcfsScheduler second = new FcfsScheduler();
    for (Process process : list) {
      second.addProcess(process);
    }
    assertEquals(3, second.getSizeOfQueue());
    assertEquals(0, second.getTotalProcessCount());
  }

  @Test
  public void testCycle() {
    FcfsScheduler fcfs = new FcfsScheduler();
    assertEquals(0, fcfs.getSizeOfQueue());
    fcfs.cycle();
    assertEquals(0, fcfs.getSizeOfQueue());

    for (Process process : list) {
      fcfs.addProcess(process);
    }

    assertEquals(3, fcfs.getSizeOfQueue());
    assertEquals(0, fcfs.getTotalProcessCount());
    fcfs.cycle();
    assertEquals(2, fcfs.getSizeOfQueue());
    assertEquals(1, fcfs.getTotalProcessCount());
    assertEquals(23, fcfs.getCurrentBurstRemaining());

    for (int i = 0; i <= 23; i++) {
      fcfs.cycle();
    }
    assertEquals(2, fcfs.getCurrentBurstRemaining());
    assertEquals(1, fcfs.getSizeOfQueue());
    assertEquals(2, fcfs.getTotalProcessCount());
    assertTrue(fcfs.canContinue());

    for (int i = 0; i <= 2; i++) {
      fcfs.cycle();
    }
    assertEquals(3, fcfs.getCurrentBurstRemaining());
    assertEquals(0, fcfs.getSizeOfQueue());
    assertEquals(3, fcfs.getTotalProcessCount());
    assertTrue(fcfs.canContinue());

    for (int i = 0; i <= 3; i++) {
      fcfs.cycle();
    }
    assertEquals(0, fcfs.getCurrentBurstRemaining());
    assertEquals(0, fcfs.getSizeOfQueue());
    assertEquals(3, fcfs.getTotalProcessCount());
    assertFalse(fcfs.canContinue());
  }
}
