# CPU Scheduling
This project simulates a CPU scheduler and the various scheduling algorithms that it can implement. 
The algorithms included are as follows:
1. FCFS
2. SJF without Preemption
3. Priority with Preemption

## Requirements
* [Java 11+](https://www.java.com/en/)
* [Gradle](https://gradle.org/) (optional)

## Running Instructions
This program can be run with either Gradle's build functionality or from the jar file.

### Run with Gradle
`./gradlew run --args="TEXT-FILE"` where text file contains valid process information.

### Run with a Jar File
`java -jar JAR-FILE TEXT-FILE`

If a jar file is not presented, one can be generated using `./gradlew jar` which will then place the created jar 
file in the `app/build/libs/` directory.

## Sample Execution
When this program is executed with `app/src/resources/SmallDataFile.txt` provided as the input file and 
run with `java -jar scheduler.jar app/src/resources/SmallDataFile.txt`, 
the following output will be generated:
```
First Come, First Served
------------------------
Total Process Count: 10
Total Elapsed Time: 338
Throughput: 17.9
CPU Utilization: 52.95857988165681
Average Waiting Time: 7.8
Average Turnaround Time: 25.7
Average Response Time: 7.8

Shortest Job First
------------------------
Total Process Count: 10
Total Elapsed Time: 338
Throughput: 17.9
CPU Utilization: 52.95857988165681
Average Waiting Time: 5.8
Average Turnaround Time: 23.7
Average Response Time: 5.8

Priority with Preemption
------------------------
Total Process Count: 10
Total Elapsed Time: 338
Throughput: 17.9
CPU Utilization: 52.95857988165681
Average Waiting Time: 9.0
Average Turnaround Time: 26.9
Average Response Time: 9.0
```