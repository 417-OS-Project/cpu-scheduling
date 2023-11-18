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
    FCFCscheduler fcfs = new FCFCscheduler();
    assertEquals(0, fcfs.getSizeOfQueue());

    fcfs.addProcess(new Process(3, 4, 5));
    assertEquals(1, fcfs.getSizeOfQueue());

    FCFCscheduler second = new FCFCscheduler();
    for (Process process : list) {
      second.addProcess(process);
    }
    assertEquals(3, second.getSizeOfQueue());
  }

  @Test
  public void testCycle() {
    FCFCscheduler fcfs = new FCFCscheduler();
    assertEquals(0, fcfs.getSizeOfQueue());
    fcfs.cycle();
    assertEquals(0, fcfs.getSizeOfQueue());

    for (Process process : list) {
      fcfs.addProcess(process);
    }

    assertEquals(3, fcfs.getSizeOfQueue());
    fcfs.cycle();
    assertEquals(2, fcfs.getSizeOfQueue());
    assertEquals(23, fcfs.getCurrentBurstRemaining());

    for (int i = 0; i < 23; i++) {
      fcfs.cycle();
    }
    assertEquals(3, fcfs.getCurrentBurstRemaining());
    assertEquals(1, fcfs.getSizeOfQueue());

    for (int i = 0; i < 3; i++) {
      fcfs.cycle();
    }
    assertEquals(4, fcfs.getCurrentBurstRemaining());
    assertEquals(0, fcfs.getSizeOfQueue());

    for (int i = 0; i < 5; i++) {
      fcfs.cycle();
    }
    assertEquals(0, fcfs.getCurrentBurstRemaining());
    assertEquals(0, fcfs.getSizeOfQueue());
  }
}
