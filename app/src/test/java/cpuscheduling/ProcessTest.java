package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cpuscheduling.Process;

public class ProcessTest {
  static Process p1, p2;

  @BeforeClass
  public static void setUp() {
    p1 = new Process();
    p2 = new Process();
  }

  @Test
  public void testProcessID() {
    assert p1.pid() == 1;
    assert p2.pid() == 2;
  }
}
