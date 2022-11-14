plugins {
    application
    id("org.jmailen.kotlinter") version "3.11.1"
    kotlin("jvm") version "1.7.0"
}

application {
    // The shadowjar plugin requires use of the deprecated `mainClassName`.
    mainClassName = "com.envylabs.cautiousengine.javalin.AppKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":generator-faker"))
    implementation("io.javalin:javalin:5.1.4")
    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0")
}