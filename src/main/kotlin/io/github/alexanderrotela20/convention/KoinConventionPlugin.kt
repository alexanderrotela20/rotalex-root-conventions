package io.github.alexanderrotela20.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import io.github.alexanderrotela20.convention.ktx.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


class KoinConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			if (
				plugins.hasPlugin("com.android.library")
					.or(plugins.hasPlugin("com.android.application"))
					.and(plugins.hasPlugin("org.jetbrains.kotlin.multiplatform"))
			) {
				configure<KotlinMultiplatformExtension> {
					sourceSets {
						androidMain {
							dependencies {
								implementation(libs.findBundle("koin-android").get())
							}
						}
						commonMain {
							dependencies {
								implementation(
									project.dependencies.platform(
										libs.findLibrary("koin.bom").get()
									)
								)
								implementation(libs.findBundle("koin-multiplatform").get())
							}
						}
					}
				}
			} else {
				if (plugins.hasPlugin("com.android.library")) {
					configure<LibraryExtension> {
						dependencies {
							add("implementation", platform(libs.findLibrary("koin.bom").get()))
							add("implementation", libs.findBundle("koin-android").get())
						}
					}
				} else if (plugins.hasPlugin("com.android.application")) {
					configure<ApplicationExtension> {
						dependencies {
							add("implementation", platform(libs.findLibrary("koin.bom").get()))
							add("implementation", libs.findBundle("koin-android").get())
						}
					}
				}
			}
		}
	}
}