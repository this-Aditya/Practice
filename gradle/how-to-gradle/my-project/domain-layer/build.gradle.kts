plugins {
    id("java-library")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

dependencies {
    implementation(project(":domain-layer"))
    implementation("org.apache.commons:commons-lang3:3.9")
}