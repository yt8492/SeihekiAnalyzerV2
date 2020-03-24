object Dependencies {
    object JVM {
        const val version = "1.8"
    }

    object Kotlin {
        const val version = "1.3.70"
        const val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    }

    object Coroutines {
        const val version = "1.3.3"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object Jsoup {
        const val version = "1.12.1"
        const val jsoup = "org.jsoup:jsoup:$version"
    }

    object Javax {
        object Annotation {
            const val version = "1.3.2"
            const val api = "javax.annotation:javax.annotation-api:$version"
        }
    }

    object Grpc {
        const val version = "1.25.0"
        const val protobuf = "io.grpc:grpc-protobuf:$version"
        const val stub = "io.grpc:grpc-stub:$version"
        const val netty = "io.grpc:grpc-netty:$version"
        const val okHttp = "io.grpc:grpc-okhttp:$version"
    }

    object Protobuf {
        const val version = "3.10.0"
        const val java = "com.google.protobuf:protobuf-java:$version"
    }

    object KrotoPlus {
        const val version = "0.6.1"
        const val coroutines = "com.github.marcoferrer.krotoplus:kroto-plus-coroutines:$version"
        const val message = "com.github.marcoferrer.krotoplus:kroto-plus-message:$version"
    }

    object SqlDelight {
        const val version = "1.2.1"
        const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$version"
    }
}