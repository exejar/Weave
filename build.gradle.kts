plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "net.weavemc.weave"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
}

application {
    mainClass.set("MainKt")
}