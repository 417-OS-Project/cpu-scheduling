package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProcessTest {
  static Process p1, p2;

  @BeforeClass
  public static void setUp() {
    p1 = new Process();
    p2 = new Process();
  }

  @Test
  public void testProcessID() {
    assertEquals(1, p1.getPid());
    assertEquals(2, p2.getPid());

    Process p3 = new Process();
    assertEquals(3, p3.getPid());
  }
}
