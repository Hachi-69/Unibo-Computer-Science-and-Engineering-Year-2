plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "1.159.0"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("it.unibo.es2.Test")
}

tasks.javadoc {
    isFailOnError = false
}
