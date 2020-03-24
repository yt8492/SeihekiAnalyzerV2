import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.Kotlin.jvm)
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Jsoup.jsoup)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
