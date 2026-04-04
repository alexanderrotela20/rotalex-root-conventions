package io.github.alexanderrotela20.convention

import com.android.build.api.dsl.ApplicationExtension
import io.github.alexanderrotela20.convention.ktx.BuildType
import io.github.alexanderrotela20.convention.ktx.androidTargetSdk
import io.github.alexanderrotela20.convention.ktx.buildTime
import io.github.alexanderrotela20.convention.ktx.configureAndroidCompose
import io.github.alexanderrotela20.convention.ktx.configureKotlinAndroid
import io.github.alexanderrotela20.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(rotalexConvention("android.application"))
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }
        }
    }

}
