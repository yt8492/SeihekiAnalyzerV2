import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
    id("com.squareup.sqldelight")
    application
}

application {
    mainClassName = "com.yt8492.seihekianalyzerv2.server.ServerMainKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":proto"))
    implementation(project(":common"))

    implementation(Dependencies.Kotlin.jvm)
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Jsoup.jsoup)
    implementation(Dependencies.Grpc.protobuf)
    implementation(Dependencies.Grpc.stub)
    implementation(Dependencies.Grpc.netty)
    implementation(Dependencies.KrotoPlus.coroutines)
    implementation(Dependencies.SqlDelight.sqliteDriver)
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Dependencies.JVM.version
        }
    }
}

sqldelight {
    database("Database") {
        packageName = "com.yt8492.seihekianalyzerv2.server"
        sourceFolders = listOf("sqldelight")
        schemaOutputDirectory = file("src/main/sqldelight/databases")
    }
}
