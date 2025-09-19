package com.rotalex.convention

import com.android.build.gradle.LibraryExtension
import com.rotalex.convention.ktx.configureAndroidCompose
import com.rotalex.convention.ktx.libs
import com.rotalex.convention.ktx.rotalexConvention
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply {
				apply(rotalexConvention("android.library"))
			}
			when {
				pluginManager.hasPlugin("com.android.library") -> {
					apply(libs.findPlugin("compose").get().get().pluginId)
					configureAndroidCompose(extensions.getByType<LibraryExtension>())
				}
				else -> {
					throw GradleException("Plugin 'com.android.library' must be applied before 'org.jetbrains.kotlin.plugin.compose'")
				}
			}
		}
	}
}