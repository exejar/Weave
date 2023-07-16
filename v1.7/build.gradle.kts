plugins {
    id("com.github.weave-mc.weave-gradle") version "bcf6ab0279"
}

minecraft.version("1.7.10")

dependencies {
    implementation(project(rootProject.path))
}