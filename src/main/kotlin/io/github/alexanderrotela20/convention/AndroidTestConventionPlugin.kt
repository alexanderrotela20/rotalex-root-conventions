package io.github.alexanderrotela20.convention

import com.android.build.gradle.TestExtension
import io.github.alexanderrotela20.convention.ktx.androidTargetSdk
import io.github.alexanderrotela20.convention.ktx.configureKotlinAndroid
import io.github.alexanderrotela20.convention.ktx.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidTestConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply(libs.findPlugin("android-test").get().get().pluginId)
				apply(libs.findPlugin("kotlin-android").get().get().pluginId)
			}

			extensions.configure<TestExtension> {
				configureKotlinAndroid(this)
				defaultConfig.targetSdk = androidTargetSdk
			}
		}
	}

}