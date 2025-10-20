package io.github.alexanderrotela20.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import io.github.alexanderrotela20.convention.ktx.BuildType
import io.github.alexanderrotela20.convention.ktx.androidTargetSdk
import io.github.alexanderrotela20.convention.ktx.buildTime
import io.github.alexanderrotela20.convention.ktx.configureAndroidCompose
import io.github.alexanderrotela20.convention.ktx.configureKotlinAndroid
import io.github.alexanderrotela20.convention.ktx.libs
import io.github.alexanderrotela20.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidApplicationConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply("com.android.application")
				apply("org.jetbrains.kotlin.plugin.compose")
				apply("org.jetbrains.kotlin.android")
				apply("org.jetbrains.kotlin.plugin.serialization")
				apply(rotalexConvention("android.lint"))
				//apply("com.dropbox.dependency-guard")
			}

			extensions.configure<ApplicationExtension> {
				configureKotlinAndroid(this)
				configureAndroidCompose(this)

				defaultConfig {
					targetSdk = androidTargetSdk
					vectorDrawables.useSupportLibrary = true

					buildConfigField("String", "BUILD_TIME", "\"$buildTime\"")
				}
				androidResources {
					generateLocaleConfig = true
				}
				buildFeatures {
					resValues = true
					buildConfig = true
				}
				buildTypes {
					release {
						isMinifyEnabled = true
						isCrunchPngs = true
						isShrinkResources = true
						applicationIdSuffix = BuildType.RELEASE.applicationIdSuffix
						proguardFiles(
							getDefaultProguardFile("proguard-android-optimize.txt"),
							"proguard-rules.pro"
						)
					}
					debug {
						applicationIdSuffix = BuildType.DEBUG.applicationIdSuffix
					}
				}

				testOptions.animationsDisabled = true
				dependenciesInfo.includeInApk = false
			}
		}
	}

}
