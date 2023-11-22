package cpuscheduling;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class SjfTest {
  SjfScheduler sjfFull = new SjfScheduler();

  @Before
  public void init() {
    ArrayList<Process> parsed;
    parsed = App.parseFile(new File("src/resources/SmallDataFile.txt"));

    while (!parsed.isEmpty()) {
      sjfFull.addProcess(parsed.get(0));
      parsed.remove(0);
    }
  }

  @Test
  public void testAddProcess() {
    SjfScheduler sjf = new SjfScheduler();
    assertEquals(0, sjf.getSizeOfQueue());

    sjf.addProcess(new Process(1, 2, 3));
    assertEquals(1, sjf.getSizeOfQueue());

    assertEquals(10, sjfFull.getSizeOfQueue());
  }

  @Test
  public void testStats() {
    assert false;
  }

  @Test
  public void testCanContinue() {
    assertTrue(sjfFull.canContinue());

    SjfScheduler sjf = new SjfScheduler();
    assertFalse(sjf.canContinue());

    sjf.addProcess(new Process(1, 1, 1));
    assertTrue(sjf.canContinue());
  }
}
