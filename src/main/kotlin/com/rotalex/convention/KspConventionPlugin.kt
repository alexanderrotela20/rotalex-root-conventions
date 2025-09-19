package com.rotalex.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.rotalex.convention.ktx.configureKsp
import com.rotalex.convention.ktx.libs
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


class KspConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply(libs.findPlugin("ksp").get().get().pluginId)
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