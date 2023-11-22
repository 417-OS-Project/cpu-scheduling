# CPU Scheduling
This project simulates a CPU scheduler and the various scheduling algorithms that it can implement. 
The types included are as follows:
1. FIFO
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

If a jar file was not presented, one can be generated using `./gradlew jar` which will then place the created jar 
file in the `app/build/libs/` directory.

## Sample Execution

## Development Push/Merge Instructions
Refer to [CONTRIBUTING.md](CONTRIBUTING.md)

## Tools Used

