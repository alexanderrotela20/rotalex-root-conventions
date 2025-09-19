package com.rotalex.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.rotalex.convention.ktx.androidNdkVersion

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File

class AndroidLibraryNativeConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			when {
				pluginManager.hasPlugin("com.android.application") ->
					configure<ApplicationExtension>(::configureAndroidNative)

				pluginManager.hasPlugin("com.android.library") ->
					configure<LibraryExtension>(::configureAndroidNative)

				else -> {
					throw GradleException("The plugin 'idroid.android.library.native' cannot be applied.")
				}
			}
		}
	}
}

private fun Project.configureAndroidNative(
	commonExtension: CommonExtension<*, *, *, *, *, *>
) {
	commonExtension.apply {
		splits {
			abi {
				isEnable = true
				reset()
				if (false) { // To Modify comming soon
					include("arm64-v8a", "x86_64", "armeabi-v7a", "x86")
					isUniversalApk = true
				} else {
					include("arm64-v8a")
				}
			}
		}

		externalNativeBuild {
			cmake {
				path = File("src/main/cpp/CMakeLists.txt")
			}
		}
		ndkVersion = androidNdkVersion
	}
}