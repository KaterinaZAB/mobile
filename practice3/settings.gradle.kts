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

rootProject.name = "practice3"
include(":IntentApp")
include(":sharer")
include(":favoritebook")
include(":systemintentsapp")
include(":simplefragmentapp")
