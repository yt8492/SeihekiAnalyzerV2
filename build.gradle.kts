import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Dependencies.Android.gradlePlugin)
        classpath(Dependencies.Kotlin.gradlePlugin)
        classpath(Dependencies.AndroidX.Navigation.safeArgsGradlePlugin)
        classpath(Dependencies.Protobuf.gradlePlugin)
        classpath(Dependencies.SqlDelight.gradlePlugin)
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
        disabledRules.set(setOf("no-wildcard-imports"))
        reporters {
            reporter(ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
