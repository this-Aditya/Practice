plugins {
    id("my-java-base")
}

//val myBuildGroup = "my project build"
//tasks.named("run") {
//    group = myBuildGroup
//}


// Tasks are of two types -> 1. Actionable 2. Lifecycle

val packageApp = tasks.register<Zip>("packageAll") {
    from(layout.projectDirectory.file("run.sh"))
    from(tasks.jar) {
        into("libs")
    }
    from (configurations.runtimeClasspath) {
        into("libs")
    }

    destinationDirectory.set(layout.buildDirectory.dir("distro"))
    archiveFileName.set("myApplication.zip")
}

tasks.build {
    dependsOn(packageApp)
}