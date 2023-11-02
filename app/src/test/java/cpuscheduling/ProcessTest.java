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
    assert p1.getPid() == 1;
    assert p2.getPid() == 2;

    Process p3 = new Process();
    assert p3.getPid() == 3;
  }
}
