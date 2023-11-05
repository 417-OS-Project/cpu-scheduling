package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProcessTest {
  static Process p1, p2;

  @BeforeClass
  public static void setUp() {
    p1 = new Process(1, 5, 5);
    p2 = new Process(6, 12, 2);
  }

  @Test
  public void testProcessID() {
    assertEquals(1, p1.getPid());
    assertEquals(2, p2.getPid());

    Process p3 = new Process(3, 61, 2);
    assertTrue(p3.getPid() > 2);

    assertEquals(3, p3.getArrivalTime());
    assertEquals(61, p3.getBurstTime());
    assertEquals(2, p3.getPriority());
  }

  @Test
  public void testArrivalTime() {
    assertEquals(1, p1.getArrivalTime());
    assertEquals(6, p2.getArrivalTime());

    Process p3 = new Process(10, 35, 4);
    assertEquals(10, p3.getArrivalTime());

    assertEquals(10, p3.getArrivalTime());
    assertEquals(35, p3.getBurstTime());
    assertEquals(4, p3.getPriority());
  }

  @Test
  public void testBurstTime() {
    assertEquals(5, p1.getBurstTime());
    assertEquals(12, p2.getBurstTime());

    Process p3 = new Process(3, 63, 5);
    int pid = p3.getPid();
    int arrivalTime = p3.getArrivalTime();
    int priority = p3.getPriority();

    assertEquals(63, p3.getBurstTime());

    p3.setBurstTime(55);
    assertEquals(55, p3.getBurstTime());

    assertEquals(pid, p3.getPid());
    assertEquals(arrivalTime, p3.getArrivalTime());
    assertEquals(priority, p3.getPriority());

    p3.setBurstTime(-23);
    assertEquals(23, p3.getBurstTime());

    assertEquals(pid, p3.getPid());
    assertEquals(arrivalTime, p3.getArrivalTime());
    assertEquals(priority, p3.getPriority());
  }

  @Test
  public void testPriority() {
    assertEquals(5, p1.getPriority());
    assertEquals(2, p2.getPriority());

    Process p3 = new Process(72, 21, 1);
    int pid = p3.getPid();
    int arrivalTime = p3.getArrivalTime();
    int burstTime = p3.getBurstTime();

    assertEquals(1, p3.getPriority());

    p3.setPriority(8);
    assertEquals(8, p3.getPriority());

    assertEquals(pid, p3.getPid());
    assertEquals(arrivalTime, p3.getArrivalTime());
    assertEquals(burstTime, p3.getBurstTime());

    p3.setPriority(-3);
    assertEquals(3, p3.getPriority());

    assertEquals(pid, p3.getPid());
    assertEquals(arrivalTime, p3.getArrivalTime());
    assertEquals(burstTime, p3.getBurstTime());
  }
}
