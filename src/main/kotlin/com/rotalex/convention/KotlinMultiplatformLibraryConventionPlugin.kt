package com.rotalex.convention

import com.rotalex.convention.ktx.configureKotlinMultiplatformTargetsAndSourceSets
import com.rotalex.convention.ktx.libs
import com.rotalex.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
				apply(rotalexConvention("android.library"))

			}

			configure<KotlinMultiplatformExtension> {
				configureKotlinMultiplatformTargetsAndSourceSets()
			}

		}
	}

}