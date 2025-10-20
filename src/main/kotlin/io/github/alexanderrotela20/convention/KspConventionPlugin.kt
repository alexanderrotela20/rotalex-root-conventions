package io.github.alexanderrotela20.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import io.github.alexanderrotela20.convention.ktx.configureKsp
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


class KspConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("com.google.devtools.ksp")
			when {
				pluginManager.hasPlugin("com.android.application") ->
					configure<ApplicationExtension>(::configureKsp)

				pluginManager.hasPlugin("com.android.library") ->
					configure<LibraryExtension>(::configureKsp)

				else -> {
					throw GradleException("The plugin 'idroid.ksp' cannot be applied.")
				}
			}
		}
	}
}