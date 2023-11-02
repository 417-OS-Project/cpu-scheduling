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
    ;
  }

  @Test
  public void testArrivalTime() {
    assert p1.getArrivalTime() == 1;
    assert p2.getArrivalTime() == 6;

    Process p3 = new Process(10);
    assert p3.getArrivalTime() == 10;
  }
}
