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

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.recyclerview:recyclerview:1.2.0-alpha01")
    implementation("androidx.fragment:fragment-ktx:1.2.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.0")

    implementation("com.google.dagger:dagger:2.25.4")
    implementation("com.google.dagger:dagger-android-support:2.25.4")
    kapt("com.google.dagger:dagger-compiler:2.25.4")
    kapt("com.google.dagger:dagger-android-processor:2.25.4")

    implementation(Dependencies.Grpc.okHttp)
    implementation(Dependencies.Grpc.stub)
    implementation(Dependencies.Grpc.protobuf)

    implementation(Dependencies.Protobuf.java)

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
