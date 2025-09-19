package com.rotalex.convention

import com.google.devtools.ksp.gradle.KspExtension
import com.rotalex.convention.ktx.libs
import com.rotalex.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class KoinAnnotationsConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply(rotalexConvention("ksp"))
			extensions.configure<KspExtension> {
				arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
				arg("KOIN_CONFIG_CHECK", "true")
				//arg("KOIN_DEFAULT_MODULE", "true")
			}
			dependencies {
				add("implementation", platform(libs.findLibrary("koin.bom").get()))
				add("implementation", libs.findBundle("koin").get())
				add("api", libs.findLibrary("koin.annotations").get())
				add("ksp", libs.findLibrary("koin.ksp.compiler").get())
			}
		}
	}
}