package com.rotalex.convention.ktx

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureKotlinAndroid(
	commonExtension: CommonExtension<*, *, *, *, *, *>
) {
	commonExtension.apply {
		compileSdk = androidCompileSdk
		buildToolsVersion = buildTools

		defaultConfig {
			minSdk = androidMinSdk
		}

		compileOptions {
			sourceCompatibility = JavaVersion.toVersion(jvmTargetVersion)
			targetCompatibility = JavaVersion.toVersion(jvmTargetVersion)
		}
	}

	configureKotlin<KotlinAndroidProjectExtension>()


}