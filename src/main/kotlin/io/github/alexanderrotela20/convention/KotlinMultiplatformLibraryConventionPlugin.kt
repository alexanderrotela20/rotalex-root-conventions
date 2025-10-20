package io.github.alexanderrotela20.convention

import com.android.build.gradle.LibraryExtension
import io.github.alexanderrotela20.convention.ktx.androidCompileSdk
import io.github.alexanderrotela20.convention.ktx.androidMinSdk
import io.github.alexanderrotela20.convention.ktx.buildTools
import io.github.alexanderrotela20.convention.ktx.jvmTargetVersion
import io.github.alexanderrotela20.convention.ktx.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply("org.jetbrains.kotlin.multiplatform")
				apply("com.android.library")
				apply("org.jetbrains.kotlin.plugin.serialization")
			}

			configure<KotlinMultiplatformExtension> {
				jvmToolchain(17)

				androidTarget()
				jvm("desktop")

				sourceSets {
					val desktopMain by named("desktopMain")
					commonMain.dependencies {
						implementation( libs.findLibrary("kotlinx.coroutines.core").get())
						implementation(libs.findLibrary("kotlinx.datetime").get())
						implementation(libs.findLibrary("kotlinx.serialization.json").get())
					}
					androidMain.dependencies {
						implementation( libs.findLibrary("kotlinx.coroutines.android").get())
					}
					desktopMain.dependencies {
						implementation( libs.findLibrary("kotlinx.coroutines.swing").get())
					}
				}
			}
			configure<LibraryExtension> {
				compileSdk = androidCompileSdk
				buildToolsVersion = buildTools
				defaultConfig {
					minSdk = androidMinSdk
				}
				buildTypes {
					release {
						isMinifyEnabled = false
					}
				}
				compileOptions {
					sourceCompatibility = JavaVersion.toVersion(jvmTargetVersion)
					targetCompatibility = JavaVersion.toVersion(jvmTargetVersion)
				}
			}
		}
	}

}