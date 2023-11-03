package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProcessTest {
  static Process p1, p2;

  @BeforeClass
  public static void setUp() {
    p1 = new Process(1);
    p2 = new Process(6);
  }

  @Test
  public void testProcessID() {
    assertEquals(1, p1.getPid());
    assertEquals(2, p2.getPid());

    Process p3 = new Process(3);
    assertTrue(p3.getPid() > 2);

    assertEquals(1, p1.getArrivalTime());
    assertEquals(6, p2.getArrivalTime());
    assertEquals(3, p3.getArrivalTime());
  }

  @Test
  public void testArrivalTime() {
    assertEquals(1, p1.getArrivalTime());
    assertEquals(6, p2.getArrivalTime());

    assertEquals(1, p1.getPid());
    assertEquals(2, p2.getPid());

    Process p3 = new Process(10);
    assertEquals(10, p3.getArrivalTime());
  }
}
