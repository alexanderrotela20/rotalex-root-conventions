package io.github.alexanderrotela20.convention

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import io.github.alexanderrotela20.convention.ktx.androidTargetSdk
import io.github.alexanderrotela20.convention.ktx.configureKotlinAndroid
import io.github.alexanderrotela20.convention.ktx.disableUnnecessaryAndroidTests
import io.github.alexanderrotela20.convention.ktx.libs
import io.github.alexanderrotela20.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply(libs.findPlugin("android-library").get().get().pluginId)
				apply(libs.findPlugin("kotlin-android").get().get().pluginId)
				apply(libs.findPlugin("kotlin-serialization").get().get().pluginId)
				apply(rotalexConvention("android.lint"))
			}

			extensions.configure<LibraryExtension> {
				configureKotlinAndroid(this)
				defaultConfig.targetSdk = androidTargetSdk
				defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
				testOptions.animationsDisabled = true
				resourcePrefix = path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_").lowercase() + "_"
			}
			extensions.configure<LibraryAndroidComponentsExtension> {
				//configurePrintApksTask(this)
				disableUnnecessaryAndroidTests(target)
			}
			dependencies {
				add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
				add("implementation", libs.findLibrary("kotlinx.serialization.json").get())

				add("androidTestImplementation", kotlin("test"))
				add("testImplementation", kotlin("test"))
			}
		}
	}
}
