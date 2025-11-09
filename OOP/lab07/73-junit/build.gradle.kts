import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java

}

repositories {
    mavenCentral()
}

dependencies {
    // The BOM (Bill of Materials) synchronizes all the versions of Junit coherently.
    testImplementation(platform("org.junit:junit-bom:6.0.1"))
    // The annotations, assertions and other elements we want to have access to when compiling our tests.
    testImplementation("org.junit.jupiter:junit-jupiter")
    // The engine that must be available at runtime to run the tests.
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    // Use junit platform for unit tests
    useJUnitPlatform()
    testLogging {
        events(*(TestLogEvent.values())) // events("passed", "skipped", "failed")
    }
    testLogging.showStandardStreams = true
}
