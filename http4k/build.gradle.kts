
plugins {
    application
    id("org.jmailen.kotlinter") version "3.11.1"
    kotlin("jvm") version "1.7.0"
}

application {
    mainClassName = "com.envylabs.cautiousengine.http4k.AppKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":generator-faker"))
    implementation(platform("org.http4k:http4k-bom:4.33.3.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-undertow")
    implementation("org.http4k:http4k-client-apache")
    implementation("org.http4k:http4k-format-jackson")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}