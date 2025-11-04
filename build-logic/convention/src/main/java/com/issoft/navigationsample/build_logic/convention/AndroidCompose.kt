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
            implementation(libs.findLibrary("androidx.core.ktx").get())
            implementation(libs.findLibrary("androidx.appcompat").get())

            implementation(libs.findLibrary("koin-core").get())
            implementation(libs.findLibrary("koin-androidx-compose").get())
            implementation(libs.findLibrary("androidx.compose.material3").get())
            implementation(libs.findLibrary("androidx.material3.windowsizeclass").get())

            implementation(libs.findLibrary("androidx.navigation3.runtime").get())
            implementation(libs.findLibrary("androidx.navigation3.ui").get())
            implementation(libs.findLibrary("androidx.lifecycle.viewmodel.navigation3").get())

            implementation(libs.findLibrary("androidx.compose.material.icons.core").get())
            implementation(libs.findLibrary("androidx.compose.material.icons.extended").get())

            implementation(libs.findLibrary("kotlinx.serialization.core").get())
            implementation(libs.findLibrary("kotlinx.serialization.json").get())

            implementation(project(":core:navigation"))


            androidTestImplementation(platform(bom))
            debugImplementation(libs.findLibrary("androidx.ui.tooling").get())
            debugImplementation(libs.findLibrary("androidx.ui.tooling.preview").get())
        }
    }
}