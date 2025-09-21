package io.github.alexanderrotela20.convention.ktx


import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension


internal fun Project.configureAndroidCompose(
	commonExtension: CommonExtension<*, *, *, *, *, *>
) {
	commonExtension.apply {

		buildFeatures {
			compose = true
		}

		dependencies {
			val bom = libs.findLibrary("androidx-compose-bom").get()
			add("implementation", platform(bom))
			add("androidTestImplementation", platform(bom))
			add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
			add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
		}

	}

	extensions.configure<ComposeCompilerGradlePluginExtension> {
		reportsDestination.set(layout.buildDirectory.dir("compose_compiler"))
		metricsDestination.set(layout.buildDirectory.dir("compose_compiler"))
	}
}
