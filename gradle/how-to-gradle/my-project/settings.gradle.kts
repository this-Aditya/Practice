rootProject.name = "my-project"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
    includeBuild("../my-another-project")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
//    includeBuild("../my-other-project")
}

include("app")
include("domain-layer")
include("business-logic")