apply {
    from("$rootDir/android-library-build.gradle")
}

plugins {
    alias(libs.plugins.secrets)
}


secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    "implementation"(libs.bundles.networking)
}
