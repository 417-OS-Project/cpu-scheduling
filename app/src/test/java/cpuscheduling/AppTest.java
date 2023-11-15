package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
  @Test
  public void testParseLine() {
    assertNull(App.parseLine("Arrival time\tCPU Burst length\tPriority"));
    assertNull(App.parseLine("Arrival Burst Priority"));
    Assert.assertArrayEquals(App.parseLine("148\t28\t10"), new int[] {148, 28, 10});
    Assert.assertArrayEquals(App.parseLine("148 28 10"), new int[] {148, 28, 10});
  }
}
