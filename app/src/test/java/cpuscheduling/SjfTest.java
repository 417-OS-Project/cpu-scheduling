package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.Test;

public class SjfTest {
  @Test
  public void testAddProcess() {
    SjfScheduler sjf = new SjfScheduler();
    assertEquals(0, sjf.getSizeOfQueue());

    sjf.addProcess(new Process(1, 2, 3));
    assertEquals(1, sjf.getSizeOfQueue());
  }

  @Test
  public void testStats() {
    assert false;
  }

  @Test
  public void testCanContinue() {
    assert false;
  }
}
