plugins {
    id("com.github.weave-mc.weave-gradle") version "bcf6ab0279"
}

minecraft.version("1.8.9")

dependencies {
    implementation(project(":shared"))
}