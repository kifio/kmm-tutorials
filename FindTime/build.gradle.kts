buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.kotlin_gradle_plugin)
        classpath(Deps.android_gradle_plugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//        maven("https://androidx.dev/storage/compose-compiler/repository/")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}