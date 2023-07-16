plugins {
    id("com.github.weave-mc.weave-gradle") version "bcf6ab0279"
}

minecraft.version("1.12.2")

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies.implementation(project(rootProject.path))