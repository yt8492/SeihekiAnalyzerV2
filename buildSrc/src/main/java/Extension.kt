import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

private fun Project.androidExt(configure: BaseAppModuleExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("android", configure)
}

fun Project.androidCommon() {
    androidExt {
        compileSdkVersion(Android.compileSdk)
        buildToolsVersion = Android.buildTools
        aaptOptions.cruncherEnabled = false

        defaultConfig {
            minSdkVersion(Android.minSdk)
            targetSdkVersion(Android.targetSdk)
            versionCode = Android.versionCode
            versionName = Android.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
        dataBinding {
            isEnabled = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}