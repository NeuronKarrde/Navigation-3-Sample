plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.jetbrains.kotlin.serialization)
//    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.issoft.core.navigation"
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.androidx.navigation3.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}