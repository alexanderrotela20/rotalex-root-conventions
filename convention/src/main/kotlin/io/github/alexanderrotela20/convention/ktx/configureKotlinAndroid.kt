package io.github.alexanderrotela20.convention.ktx

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension) {
    commonExtension.apply {
        compileSdk = androidCompileSdk
        buildToolsVersion = buildTools

        defaultConfig.apply {
            minSdk = androidMinSdk
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.toVersion(jvmTargetVersion)
            targetCompatibility = JavaVersion.toVersion(jvmTargetVersion)
        }
    }

    configureKotlin()
}