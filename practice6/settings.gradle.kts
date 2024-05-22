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

rootProject.name = "practice6"
include(":app")
include(":app:errorCreate")
include(":securesharedpreferences")
include(":2internalfilestorage")
include(":3notebook")
include(":4employeedb")
