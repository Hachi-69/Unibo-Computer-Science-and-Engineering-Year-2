plugins {
    java
    application
    id("org.danilopianini.gradle-java-qa") version "1.159.0"
}

repositories {
    mavenCentral()
}

tasks.javadoc {
    isFailOnError = false
}

dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.9.8")
    implementation("org.slf4j:slf4j-api:2.0.17")
    //  JOOL: Java 8+ Fluent API for JDK 8+ Streams
    implementation("org.jooq:jool:0.9.15")
    // Logback backend for SLF4J
    runtimeOnly("ch.qos.logback:logback-classic:1.5.21")
}


application {
    // Run with: ./gradlew -PmainClass=it.unibo.oop.MyMainClass run
    val main: String? by project
    mainClass.set(main ?: "it.unibo.oop.reactivegui01.Test")
}
