package cpuscheduling;

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
    assert fcfs.getSizeOfQueue() == 0;

    fcfs.addProcess(new Process(3, 4, 5));
    assert fcfs.getSizeOfQueue() == 1;

    FCFCscheduler second = new FCFCscheduler();
    for (Process process : list) {
      second.addProcess(process);
    }
    assert second.getSizeOfQueue() == 3;
  }

  @Test
  public void testCycle() {
    assert false;
  }
}
