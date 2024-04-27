plugins {
    id ("java")
    id ("checkstyle")
}

val myBuildGroup = "my project build"
tasks.named<TaskReportTask>("tasks") {
    displayGroup = myBuildGroup
}

tasks.build {
    group = myBuildGroup
}

tasks.check {
    group = myBuildGroup
    description = "Run tasks (including tests)."
}

tasks.register("qualityCheck") {
    group = myBuildGroup
    dependsOn(tasks.classes, tasks.checkstyleMain)
//    dependsOn(tasks.testClasses, tasks.checkstyleTest)
    description = "Run tasks (excluding tests)."
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

tasks.test {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.7.2")
}