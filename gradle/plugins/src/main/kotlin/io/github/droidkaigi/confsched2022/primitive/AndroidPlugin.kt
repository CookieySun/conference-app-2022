package io.github.droidkaigi.confsched2022.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            androidLibrary {
                setupAndroid()
            }
        }
    }
}
