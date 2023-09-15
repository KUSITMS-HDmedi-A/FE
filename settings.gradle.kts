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
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}

rootProject.name = "HDmedi"
include(":app")
include(":core:network")
include(":feature:home:ui")
include(":feature:home:domain")
include(":feature:home:data")
include(":feature:alarm:data")
include(":feature:alarm:ui")
include(":feature:alarm:domain")
include(":feature:medicine:data")
include(":feature:medicine:domain")
include(":feature:medicine:ui")
include(":feature:selfcheck:data")
include(":feature:selfcheck:ui")
include(":feature:signin:data")
include(":feature:signin:ui")
include(":feature:history:data")
include(":feature:history:ui")
include(":feature:history:domain")
include(":core:common")
include(":core:navigation")
include(":feature:search:ui")
include(":feature:mypage:ui")
include(":feature:signin:domain")
