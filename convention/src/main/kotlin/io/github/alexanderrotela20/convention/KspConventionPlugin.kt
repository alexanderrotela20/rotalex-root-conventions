package io.github.alexanderrotela20.convention

import io.github.alexanderrotela20.convention.ktx.rotalexConvention
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project


class KspConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            when {
                pluginManager.hasPlugin("com.android.application") -> {
                    TODO("To be implemented")
                }

                pluginManager.hasPlugin("com.android.library") -> {
                    TODO("To be implemented")
                }

                pluginManager.hasPlugin("org.jetbrains.kotlin.multiplatform") -> {
                    TODO("To be implemented")
                }

                else -> {
                    throw GradleException("The plugin ${rotalexConvention("ksp")} cannot be applied.")
                }
            }
        }
    }
}