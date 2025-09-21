package io.github.alexanderrotela20.convention.ktx

import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.configureKotlinMultiplatformTargetsAndSourceSets() {
	androidTarget()
	jvm("desktop")

	sourceSets {
		val desktopMain by named("desktopMain")
		commonMain.dependencies { }
		androidMain.dependencies { }
		desktopMain.dependencies {

		}
	}

}