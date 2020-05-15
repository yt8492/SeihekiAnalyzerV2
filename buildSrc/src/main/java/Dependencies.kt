object Dependencies {
    object JVM {
        const val version = "1.8"
    }

    object Kotlin {
        const val version = "1.3.72"
        const val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Coroutines {
        const val version = "1.3.3"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:3.6.3"
    }

    object AndroidX {
        object Core {
            const val version = "1.2.0"
            const val coreKtx = "androidx.core:core-ktx:$version"
        }

        object Appcompat {
            const val version = "1.1.0"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object Constraintlayout {
            const val version = "1.1.3"
            const val constraintlayout = "androidx.constraintlayout:constraintlayout:$version"
        }

        object Recyclerview {
            const val version = "1.2.0-alpha03"
            const val recyclerview = "androidx.recyclerview:recyclerview:$version"
        }

        object Fragment {
            const val version = "1.2.4"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Navigation {
            const val version = "2.2.2"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }
    }

    object Dagger {
        const val version = "2.25.4"
        const val dagger = "com.google.dagger:dagger:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
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
        const val gradlePlugin = "com.google.protobuf:protobuf-gradle-plugin:0.8.10"
    }

    object KrotoPlus {
        const val version = "0.6.1"
        const val coroutines = "com.github.marcoferrer.krotoplus:kroto-plus-coroutines:$version"
        const val message = "com.github.marcoferrer.krotoplus:kroto-plus-message:$version"
    }

    object SqlDelight {
        const val version = "1.2.1"
        const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$version"
        const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$version"
    }
}
