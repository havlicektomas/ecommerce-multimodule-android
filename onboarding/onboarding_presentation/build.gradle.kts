@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":coreui"))
    implementation(project(":onboarding:onboarding_domain"))
}