plugins {
    kotlin("jvm") version "1.8.21"
    application
}

val projectName:    String by project
val projectVersion: String by project
val projectGroup:   String by project

group = projectGroup
version = projectVersion

repositories {
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
    api(libs.asm)
    api(libs.asmtree)
    api(libs.asmutil)
    api("com.github.weave-mc:weave-loader:70bd82faa6")
}