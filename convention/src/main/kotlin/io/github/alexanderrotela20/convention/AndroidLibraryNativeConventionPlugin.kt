package io.github.alexanderrotela20.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import io.github.alexanderrotela20.convention.ktx.androidNdkVersion
import io.github.alexanderrotela20.convention.ktx.configureKotlinAndroid
import io.github.alexanderrotela20.convention.ktx.disableUnnecessaryAndroidTests
import io.github.alexanderrotela20.convention.ktx.rotalexConvention

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File

class AndroidLibraryNativeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            when {
                pluginManager.hasPlugin("com.android.library") -> {
                    configure<LibraryAndroidComponentsExtension> {
                        this.finalizeDsl {
                            configureAndroidNative(it)
                        }
                    }
                }
                else -> {
                    throw GradleException("The plugin '${rotalexConvention("android-library-native")}' cannot be applied.")
                }
            }
        }
    }
}

private fun Project.configureAndroidNative(commonExtension: CommonExtension) {
    commonExtension.apply {
        splits.apply {
            abi {
                isEnable = true
                reset()
                    include("arm64-v8a", "x86_64", "armeabi-v7a", "x86")
                    isUniversalApk = true
            }
        }
        externalNativeBuild.apply {
            cmake {
                path = File("src/main/cpp/CMakeLists.txt")
            }
        }
        ndkVersion = androidNdkVersion
    }
}