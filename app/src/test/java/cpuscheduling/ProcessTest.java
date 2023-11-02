package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProcessTest {
  static Process p1, p2;

  @BeforeClass
  public static void setUp() {
    p1 = new Process(5);
    p2 = new Process(2);
  }

  @Test
  public void testPriority() {
    assertEquals(5, p1.getPriority());
    assertEquals(2, p2.getPriority());

    p1.setPriority(-3);
    assertEquals(3, p1.getPriority());
  }
}
