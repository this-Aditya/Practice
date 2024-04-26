plugins {
    id("my-java-library")
}

dependencies {
    implementation(project(":domain-layer"))
    implementation("org.apache.commons:commons-lang3:3.9")
}