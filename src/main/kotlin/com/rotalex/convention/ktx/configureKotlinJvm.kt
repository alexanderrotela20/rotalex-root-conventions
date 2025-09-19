package com.rotalex.convention.ktx

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.configureKotlinJvm() {
	extensions.configure<JavaPluginExtension> {
		sourceCompatibility = JavaVersion.toVersion(jvmTargetVersion)
		targetCompatibility = JavaVersion.toVersion(jvmTargetVersion)
	}

	configureKotlin<KotlinJvmProjectExtension>()
}