pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
    }
}

val projectName: String by settings
rootProject.name = projectName

include("v1.8")
include("v1.7")
include("v1.12")