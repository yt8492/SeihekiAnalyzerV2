plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

androidCommon()
android {
    defaultConfig {
        applicationId = ("com.yt8492.seihekianalyzerv2")
        buildConfigField("String", "HOST", "\"10.0.2.2\"")
        buildConfigField("int", "PORT", "50051")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":proto"))

    implementation(Dependencies.Kotlin.jvm)

    implementation(Dependencies.AndroidX.Appcompat.appcompat)
    implementation(Dependencies.AndroidX.Core.coreKtx)
    implementation(Dependencies.AndroidX.Constraintlayout.constraintlayout)
    implementation(Dependencies.AndroidX.Recyclerview.recyclerview)
    implementation(Dependencies.AndroidX.Fragment.fragmentKtx)
    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(Dependencies.AndroidX.Navigation.uiKtx)

    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.androidSupport)
    kapt(Dependencies.Dagger.compiler)
    kapt(Dependencies.Dagger.androidProcessor)

    implementation(Dependencies.Grpc.okHttp)
    implementation(Dependencies.Grpc.stub)
    implementation(Dependencies.Grpc.protobuf)

    implementation(Dependencies.Protobuf.java)

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}

ktlint {
    android.set(true)
}
