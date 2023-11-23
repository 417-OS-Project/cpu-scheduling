package cpuscheduling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class PriorityTest {
    PriorityScheduler pFull;

    @Before
    public void init() {
        pFull = new PriorityScheduler();
        ArrayList<Process> parsed;
        parsed = App.parseFile(new File("src/resources/SmallDataFile.txt"));

        while (!parsed.isEmpty()) {
            pFull.addProcess(parsed.get(0));
            parsed.remove(0);
        }
    }

    @Test
    public void testAddProcess() {
        assertEquals(10, pFull.getSizeOfQueue());
        assertEquals(0, pFull.getTotalProcessCount());

        pFull.addProcess(new Process(1, 2, 3));
        assertEquals(11, pFull.getSizeOfQueue());
        assertEquals(0, pFull.getTotalProcessCount());
    }
}
