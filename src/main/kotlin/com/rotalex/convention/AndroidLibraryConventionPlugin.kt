package com.rotalex.convention

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.rotalex.convention.ktx.androidTargetSdk
import com.rotalex.convention.ktx.configureKotlinAndroid
import com.rotalex.convention.ktx.disableUnnecessaryAndroidTests
import com.rotalex.convention.ktx.libs
import com.rotalex.convention.ktx.rotalexConvention
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
