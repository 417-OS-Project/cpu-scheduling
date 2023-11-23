package cpuscheduling;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Priority (with Preemption) scheduler.
 */
public class PriorityScheduler {
    /**
     * Queue of Processes.
     */
    private Queue<Process> queue;

    /**
     * Stat tracker for this scheduler.
     */
    StatTracker stats;

    /**
     * Constructor for the Priority scheduler.
     */
    public PriorityScheduler() {
        this.queue = new LinkedList<>();
        stats = new StatTracker();
    }

    /**
     * Add a new process to the queue.
     *
     * @param newProcess process to be added.
     */
    public void addProcess(Process newProcess) {
        this.queue.add(newProcess);
    }

    /**
     * Return the size of the current queue.
     *
     * @return size of queue.
     */
    public int getSizeOfQueue() {
        return this.queue.size();
    }

    public int getTotalProcessCount() {
        return stats.getTotalProcessCount();
    }
}
