pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "2.2.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Hogwarts"
include(":app")

// Core модули (лежат в папке core)
include(":core:core-data")
include(":core:core-ui")
include(":core:core-navigation")
include(":core:core-util")
include(":core:core-domain")

// Feature модули (лежат в папке feature)
include(":feature:feature-main")
include(":feature:feature-character-detail")
