package com.issoft.navigationsample.build_logic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.findLibrary("androidx.compose.bom").get()
            implementation(platform(bom))
            implementation(libs.findLibrary("koin-core").get())
            implementation(libs.findLibrary("koin-androidx-compose").get())
            implementation(libs.findLibrary("androidx.compose.material3").get())
            implementation(libs.findLibrary("androidx.material3.windowsizeclass").get())
            androidTestImplementation(platform(bom))
            debugImplementation(libs.findLibrary("androidx.ui.tooling").get())
            debugImplementation(libs.findLibrary("androidx.ui.tooling.preview").get())
        }
    }
}