@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":discovery:discovery_domain"))

    ksp(libs.room.compiler)
    implementation(libs.room)
    implementation(libs.room.ktx)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.converter)
}