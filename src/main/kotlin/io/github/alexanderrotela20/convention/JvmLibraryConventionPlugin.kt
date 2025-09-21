package io.github.alexanderrotela20.convention

import io.github.alexanderrotela20.convention.ktx.configureKotlinJvm
import io.github.alexanderrotela20.convention.ktx.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply(libs.findPlugin("kotlin-jvm").get().get().pluginId)
			}
			configureKotlinJvm()
		}
	}
}