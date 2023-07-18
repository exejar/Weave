val projectVersion: String by project
val projectGroup:   String by project

project.ext {
    version = projectVersion
    group   = projectGroup
}

plugins {
    application
    id("com.github.weave-mc.weave-gradle") version "bcf6ab0279" apply false
    kotlin("jvm") version "1.8.21"
}

subprojects {
    apply(plugin = "com.github.weave-mc.weave-gradle")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories.maven("https://jitpack.io")
    dependencies.compileOnly(project(rootProject.path))
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    api(libs.asm)
    api(libs.asmtree)
    api(libs.asmutil)
    api(libs.loader)
}