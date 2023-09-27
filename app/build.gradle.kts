/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.3/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    application

    jacoco

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

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("cpuscheduling.App")
}

/* Test Configurations */

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        csv.required.set(true)
    }
}

jacoco {
    toolVersion = "0.8.10"
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

tasks.withType<Checkstyle>().configureEach {
    ignoreFailures = true
}

spotbugs {
    ignoreFailures = true
}