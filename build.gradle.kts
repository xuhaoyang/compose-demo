// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.1.0-alpha04")
    val nav_version by extra("2.4.0-alpha09")
    val accompanist_version by extra("0.18.0")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}