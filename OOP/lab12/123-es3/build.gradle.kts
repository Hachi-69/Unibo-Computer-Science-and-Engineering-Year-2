plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "1.159.0"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.9.8")
}

application {
    mainClass.set("it.unibo.es3.Test")
}

tasks.javadoc {
    isFailOnError = false
}
