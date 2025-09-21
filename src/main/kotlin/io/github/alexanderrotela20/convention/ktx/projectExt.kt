package io.github.alexanderrotela20.convention.ktx

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

val Project.libs
	get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.androidCompileSdk: Int
	get() = Integer.valueOf(libs.findVersion("androidCompileSdk").get().toString())

val Project.androidTargetSdk: Int
	get() = Integer.valueOf(libs.findVersion("androidTargetSdk").get().toString())

val Project.androidMinSdk: Int
	get() = Integer.valueOf(libs.findVersion("androidMinSdk").get().toString())

val Project.androidNdkVersion: String
	get() = libs.findVersion("androidNdkVersion").get().toString()

val Project.jvmTargetVersion: String
	get() = libs.findVersion("jvmTarget").get().toString()

val Project.buildTools: String
	get() = libs.findVersion("buildToolsVersion").get().toString()

val Project.isRelease: Boolean
	get() = gradle.startParameter.taskNames.any {
		it.contains("Release")
	}
val Project.buildTime by lazy {
	DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
		.withZone(ZoneOffset.UTC)
		.format(Instant.now())
}

val rotalexConvention: (name: String) -> String = { "com.rotalex.convention.$it" }
