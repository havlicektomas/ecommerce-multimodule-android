@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.dagger.hilt)
    kotlin("kapt")
}

android {
    defaultConfig {
        applicationId = "dev.havlicektomas.ecommerce"
    }
}

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":coreui"))
    implementation(project(":onboarding:onboarding_domain"))
    implementation(project(":onboarding:onboarding_presentation"))
    implementation(project(":discovery:discovery_data"))
    implementation(project(":discovery:discovery_domain"))
    implementation(project(":discovery:discovery_presentation"))

    implementation(libs.pref.datastore)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}