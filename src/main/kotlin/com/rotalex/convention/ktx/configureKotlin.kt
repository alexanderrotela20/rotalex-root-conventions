package com.rotalex.convention.ktx

import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension



internal inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() = configure<T> {
	// Override by setting warningsAsErrors=true in your gradle.properties
	val warningsAsErrors: String? by project
	val _compilerOptions = when (this) {
		is KotlinAndroidProjectExtension -> compilerOptions
		is KotlinJvmProjectExtension -> compilerOptions
		else -> TODO("Unsupported project extension $this ${T::class}")
	}
		_compilerOptions.apply {
			jvmTarget = JvmTarget.fromTarget(jvmTargetVersion)
			allWarningsAsErrors = warningsAsErrors.toBoolean()
			freeCompilerArgs.add("-Xcontext-parameters")
			//freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",)
	}
}
