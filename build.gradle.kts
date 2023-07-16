val projectName:    String by project
val projectVersion: String by project
val projectGroup:   String by project

project.ext {
    version = projectVersion
    group   = projectGroup
}

plugins {
    kotlin("jvm") version "1.8.21"
    application
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    api(libs.asm)
    api(libs.asmtree)
    api(libs.asmutil)
    api("com.github.weave-mc:weave-loader:70bd82faa6")
}