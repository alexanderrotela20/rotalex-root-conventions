package com.rotalex.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.rotalex.convention.ktx.BuildType
import com.rotalex.convention.ktx.androidTargetSdk
import com.rotalex.convention.ktx.buildTime
import com.rotalex.convention.ktx.configureAndroidCompose
import com.rotalex.convention.ktx.configureKotlinAndroid
import com.rotalex.convention.ktx.libs
import com.rotalex.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidApplicationConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply(libs.findPlugin("android-application").get().get().pluginId)
				apply(libs.findPlugin("kotlin-android").get().get().pluginId)
				apply(libs.findPlugin("compose").get().get().pluginId)
				apply(libs.findPlugin("kotlin-serialization").get().get().pluginId)
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

			extensions.configure<ApplicationAndroidComponentsExtension> {
				//configurePrintApksTask(this)
				//configureBadgingTasks(extensions.getByType<BaseExtension>(), this)
			}
		}
	}

}
