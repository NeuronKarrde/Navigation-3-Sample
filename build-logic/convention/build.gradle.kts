plugins {
    `kotlin-dsl`
}

group = "com.issoft.navigationsample.build_logic.convention"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.issoft.navigationsample"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "com.issoft.navigationsample.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.issoft.navigationsample.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "com.issoft.navigationsample.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }
}