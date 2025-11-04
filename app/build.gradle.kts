plugins {
    alias(libs.plugins.convention.android.application.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.issoft.navigationsample"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

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
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)

    implementation(projects.networkapi)
    implementation(projects.features.checkin)
    implementation(projects.features.login)
    implementation(projects.features.workouts)
    implementation(projects.features.referfriend)
}