@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.dagger.hilt)
    kotlin("kapt")
}

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    //
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}