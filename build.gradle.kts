import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Kotlin.version}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.1")
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.10")
        classpath("com.squareup.sqldelight:gradle-plugin:1.2.1")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1" apply false
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    extensions.configure<KtlintExtension>("ktlint") {
        ignoreFailures.set(true)
        reporters {
            reporter(ReporterType.CHECKSTYLE)
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}