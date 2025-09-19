package com.rotalex.convention

import com.rotalex.convention.ktx.addComposeMultiplatformDependencies
import com.rotalex.convention.ktx.libs
import com.rotalex.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibraryComposeConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply(rotalexConvention("kotlin.multiplatform.library"))
				apply(libs.findPlugin("compose").get().get().pluginId)
				apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
			}
			configure<KotlinMultiplatformExtension> {
				addComposeMultiplatformDependencies()
			}
		}
	}
}



