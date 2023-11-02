package cpuscheduling;

/**
 * Process class to store a process' data.
 */
public class Process {

    /**
     * The process id number.
     */
    private int pid;

    /**
     * Constructor for the Process class.
     */
    public Process() {
        this.pid = 1;
        // Set process ID number
        // Increment process ID number for the next object
    }

    /**
     * Get the pid.
     *
     * @return process id.
     */
    int pid() {
        return this.pid;
    }
}
