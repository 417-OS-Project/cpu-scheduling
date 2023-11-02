package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProcessTest {
  static Process p1, p2;

  @BeforeClass
  public static void setUp() {
    p1 = new Process(5);
    p2 = new Process(12);
  }

  @Test
  public void testBurstTime() {
    assert p1.getBurstTime() == 5;
    assert p2.getBurstTime() == 12;

    Process p3 = new Process(63);
    assert p3.getBurstTime() == 63;

    p1.setBurstTime(55);
    assert p1.getBurstTime() == 55;

    p2.setBurstTime(3);
    assert p2.getBurstTime() == 3;
  }
}
