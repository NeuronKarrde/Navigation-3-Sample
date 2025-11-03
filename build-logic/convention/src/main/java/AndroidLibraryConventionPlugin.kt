import com.android.build.api.dsl.LibraryExtension
import com.issoft.navigationsample.build_logic.convention.configureKotlinAndroid
import com.issoft.navigationsample.build_logic.convention.implementation
import com.issoft.navigationsample.build_logic.convention.libs
import com.issoft.navigationsample.build_logic.convention.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")

            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                dependencies {
                    implementation(libs.findLibrary("koin-core").get())

                    testImplementation(kotlin("test"))
                }
            }
        }
    }
}