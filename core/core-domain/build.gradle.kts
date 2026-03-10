plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
}

kotlin {
    jvmToolchain(17)
}
dependencies {
    implementation(libs.koin.core)
    testImplementation(libs.junit)
}