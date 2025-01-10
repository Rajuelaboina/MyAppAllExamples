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

        maven { url = uri("https://www.jitpack.io" ) }
    }
    plugins {
        kotlin("jvm") version "1.9.23"
        //alias(libs.plugins.jetbrains.kotlin.android)
        //alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)


    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyMap"
include(":app")

