package com.rotalex.convention.ktx

import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.addComposeMultiplatformDependencies() {
	sourceSets {
		val compose = extensions.getByType<ComposeExtension>().dependencies
		commonMain.dependencies {
			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.materialIconsExtended)
			implementation(compose.ui)
			implementation(compose.components.resources)
			implementation(compose.components.uiToolingPreview)
		}
		commonTest.dependencies {
//			implementation(libs.kotlin.test)
		}
		androidMain.dependencies {
			implementation(compose.preview)
		}

		named("desktopMain").dependencies {
			implementation(compose.desktop.currentOs)
		}
	}
}

