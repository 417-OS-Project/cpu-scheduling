plugins {
    application

    jacoco
    id ("org.barfuin.gradle.jacocolog") version "3.1.0"

    id("com.diffplug.spotless") version "6.21.0"

    pmd
    checkstyle
    id("com.github.spotbugs") version "5.1.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:32.1.1-jre")

    testImplementation("junit:junit:4.13.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("cpuscheduling.App")
}

tasks.jar {
    manifest.attributes["Main-Class"] = application.mainClass
}

/* Test Configurations */

jacoco {
    toolVersion = "0.8.10"
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        csv.required.set(true)
    }
}

/* Formatting */
spotless {
    java {
        importOrder()

        cleanthat()
        googleJavaFormat()
    }
}

tasks.check {
    // Check formatting before linting
    dependsOn(tasks.spotlessCheck)
}

/* Check Configurations */

pmd {
    toolVersion = "6.55.0"
    isConsoleOutput = true
}

tasks.withType<Pmd>().configureEach {
    ignoreFailures = true
}

checkstyle {
    toolVersion = "10.12.3"
}

tasks.checkstyleTest {
    enabled = false
}

tasks.withType<Checkstyle>().configureEach {
    ignoreFailures = true
}

spotbugs {
    ignoreFailures = true
}
