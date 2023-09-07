pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HDmedi"
include(":app")
include(":feature:home:data")
include(":feature:home:ui")
include(":feature:home:domain")
include(":feature:medicine:data")
include(":feature:medicine:domain")
include(":feature:medicine:ui")
