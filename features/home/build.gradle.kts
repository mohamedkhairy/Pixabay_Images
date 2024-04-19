apply {
    from("$rootDir/android-library-build.gradle")
}

plugins {
    alias(libs.plugins.ksp)
    id("kotlinx-serialization")
}


dependencies {

    val composeBom = platform(libs.androidx.compose.bom)

    "implementation"(composeBom)
    "implementation"(libs.serialization)
    "implementation"(libs.bundles.jetpackCompost)
    "implementation"(libs.hiltNavigationCompose)
    "implementation"(libs.ktorAndroid)
    "implementation"(libs.bundles.archComponents)
    "implementation"(libs.bundles.kotlinCoroutines)
    "implementation"(libs.bundles.room)
    "ksp"(libs.roomCompiler)
    "implementation"(project(":core:database"))
    "implementation"(project(":core:network"))
    "implementation"(project(":core:utils"))
    "implementation"(project(":core:ui"))


}
