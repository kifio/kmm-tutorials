pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "FindTime"
include(":androidApp")
include(":shared")
include(":shared-ui")
include(":desktop")