// Use this file for putting global lifecycle tasks
val globalBuildGroup = "My global build"
val ciBuildGroup = "my CI Build"

tasks.named<TaskReportTask>("tasks") {
    displayGroup = globalBuildGroup
}

tasks.register("qualityCheckAll") {
    group = globalBuildGroup
    dependsOn(subprojects.map { "${it.name}:qualityCheck" })
}

tasks.register("checkAll") {
    group = ciBuildGroup
    dependsOn(subprojects.map { "${it.name}:check" })
}