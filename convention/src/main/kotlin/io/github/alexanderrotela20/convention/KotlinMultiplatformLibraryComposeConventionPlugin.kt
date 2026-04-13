package io.github.alexanderrotela20.convention

import io.github.alexanderrotela20.convention.ktx.libs
import io.github.alexanderrotela20.convention.ktx.rotalexConvention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(rotalexConvention("kotlin.multiplatform.library"))
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.compose")
            }
            configure<KotlinMultiplatformExtension> {

                sourceSets {
                    val compose = extensions.getByType<ComposePlugin.Dependencies>()
                    commonMain.dependencies {
                        implementation(libs.findLibrary("jetbrains.compose.runtime").get())
                        implementation(libs.findLibrary("jetbrains.compose.foundation").get())
                        implementation(libs.findLibrary("jetbrains.compose.material.icons.extended").get())
                        implementation(libs.findLibrary("jetbrains.compose.material3").get())
                        implementation(libs.findLibrary("jetbrains.compose.ui").get())
                        implementation(libs.findLibrary("jetbrains.compose.ui.util").get())
                        implementation(libs.findLibrary("jetbrains.compose.ui.tooling.preview").get())
                    }
                    named("desktopMain").dependencies {
                        implementation(compose.desktop.currentOs)
                        implementation(libs.findLibrary("jetbrains.compose.components.splitpane.desktop").get())
                    }
                }

            }
        }
    }
}



