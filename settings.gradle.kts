pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    // FIXME: When you add this, "Build was configured to prefer settings repositories over project repositories but repository 'ivy' was added" will occur
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "DroidKaigi2022"
include(
    ":app",
    ":appioscombined",
    ":feature-sessions",
    ":core-ui",
    ":core-designsystem",
    ":core-data",
    ":core-model"
)

project(":appioscombined").projectDir = file("app-ioscombined")
