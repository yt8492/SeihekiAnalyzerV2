import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
    id("com.google.protobuf")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.Kotlin.jvm)
    implementation(Dependencies.Javax.Annotation.api)
    implementation(Dependencies.Grpc.protobuf)
    implementation(Dependencies.Grpc.stub)
    implementation(Dependencies.Grpc.okHttp)
    implementation(Dependencies.KrotoPlus.coroutines)
    implementation(Dependencies.KrotoPlus.message)
}

sourceSets {
    getByName("main") {
        java {
            srcDir("$buildDir/generated/source/proto/main/java")
            srcDir("$buildDir/generated/source/proto/main/grpc")
            srcDir("$buildDir/generated/source/proto/main/coroutines")
            include("**/*.protodevel")
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Dependencies.JVM.version
            freeCompilerArgs = listOf("-Xuse-experimental=kotlin.Experimental")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${Dependencies.Protobuf.version}"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${Dependencies.Grpc.version}"
        }
        id("coroutines") {
            artifact = "com.github.marcoferrer.krotoplus:protoc-gen-grpc-coroutines:${Dependencies.KrotoPlus.version}"
        }
        id("kroto") {
            artifact = "com.github.marcoferrer.krotoplus:protoc-gen-kroto-plus:${Dependencies.KrotoPlus.version}"
        }
    }

    generateProtoTasks {
        val krotoConfig = file("krotoPlusConfig.asciipb")
        ofSourceSet("main").forEach { task ->
            task.inputs.files.plus(krotoConfig)
            task.plugins {
                id("grpc") {}
                id("coroutines") {}
                id("kroto") {
                    outputSubDir = "java"
                    option("ConfigPath=$krotoConfig")
                }
            }
        }
    }
}