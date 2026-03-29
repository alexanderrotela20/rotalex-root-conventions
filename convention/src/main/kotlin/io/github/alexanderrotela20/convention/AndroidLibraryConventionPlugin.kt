package io.github.alexanderrotela20.convention

import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import io.github.alexanderrotela20.convention.ktx.configureKotlinAndroid
import io.github.alexanderrotela20.convention.ktx.disableUnnecessaryAndroidTests
import io.github.alexanderrotela20.convention.ktx.libs
import io.github.alexanderrotela20.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply(rotalexConvention("android.lint"))
            }

            configure<LibraryAndroidComponentsExtension> {
                this.finalizeDsl {
                    configureKotlinAndroid(it)
                }
                disableUnnecessaryAndroidTests(target)
            }
            dependencies {
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
                add("implementation", libs.findLibrary("kotlinx.serialization.core").get())
            }
        }
    }
}
