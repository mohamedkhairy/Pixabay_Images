apply {
    from("$rootDir/android-library-build.gradle")
}

//plugins {
//    id("kotlin-parcelize")
//}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    "implementation"(composeBom)
    "implementation"(libs.bundles.jetpackCompost)
    "implementation"(libs.kotlin)

}
