# Gradle
[Gradle](https://gradle.org/) is a build automation tool that can be utilized in multiple programming languages and is designed to be operating system independent. Gradle is also known for its wide selection of [plugins](https://plugins.gradle.org/). 

## Tasks
All gradle tasks for done through either `./gradlew [task]` on Linux or `gradlew.bat [task]` on Windows.

### Run
For Java applications, Gradle can run the program through the Gradle wrapper without having to manually compile a jar file.
- `./gradlew run` - Run the main program
- `./gradlew run --args="..."` - Run the main program with specific command line arguments

### Testing
[Unit Testing](https://www.geeksforgeeks.org/unit-testing-software-testing/) provides a way to test code without having to run it, as well as detect and prevent unintended changes. This program utilizes [JUnint 4](https://github.com/junit-team/junit4) to write tests and [jacoco](https://github.com/jacoco/jacoco) to track test coverage.

- `./gradlew test` - Run the testing suite

### Linting
[Linters](https://en.wikipedia.org/wiki/Lint_(software) provide a means to analyze code quality, specifically as it pretains to style errors, error prone code, and language best practices. This program contains [PMD](https://pmd.github.io/), [Checkstyle](https://github.com/checkstyle/checkstyle), and [Spotbugs](https://github.com/spotbugs/spotbugs) functionality. [Spotless](https://github.com/diffplug/spotless) functionality has also been provided to automate code formatting based on [Google's Java Style Guide](https://google.github.io/styleguide/javaguide.html).

- `./gradlew check` - Run the linting tools
- `./gradlew build` - Meta-task that runs the afformentioned test and check tasks together 
- `./gradlew spotlessCheck` - Run the spotless format checker
- `./gradlew spotlessApply` - Reformat the code base to ensure format consistency

#### Build Pass/Fail
The aforementioned linters **will not** cause this build to fail if they find an error; this is by design. The linters should be run and the errors should be remidied; however, not all errors have the severity needed to stop the build and some errors could be unavoidable. Thus, the build will bring attention to what it deems to be errors and continue. 

The spotless plugin **will** cause the build to fail; this is also by design. By causing the build to fail, it necessitates you utilize the plugin before a pull request gets merged in. This makes collaborative development easier by ensuring no spacing issues occur to make it harder to review code. Thankfully, this is very easy to fix by just running the previously mentioned `spotlessApply` task.

### Misc
- `./gradlew clean` - Remove Gradle build files so the next build will start from scratch
