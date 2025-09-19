package com.rotalex.convention

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Applies a Rotalex convention plugin by its short name from the 'com.rotalex.convention' group.
 *
 * For example, to apply the `com.rotalex.convention.android.library` plugin,
 * you can use `convention("android.library")`.
 *
 * @param name The short name of the plugin (e.g., "android.library", "koin.annotations", "kotlin.multiplatform.library").
 */
fun PluginDependenciesSpec.convention(name: String): PluginDependencySpec {
    return id("com.rotalex.convention.$name")
}

fun PluginDependenciesSpec.androidApplication(): PluginDependencySpec {
    return convention("android.application")
}

fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec {
    return convention("android.library")
}

fun PluginDependenciesSpec.androidLibraryCompose(): PluginDependencySpec {
    return convention("android.library.compose")
}

fun PluginDependenciesSpec.androidLibraryNative(): PluginDependencySpec {
    return convention("android.library.native")
}

fun PluginDependenciesSpec.androidLint(): PluginDependencySpec {
    return convention("android.lint")
}

fun PluginDependenciesSpec.androidTest(): PluginDependencySpec {
    return convention("android.test")
}

fun PluginDependenciesSpec.jvmLibrary(): PluginDependencySpec {
    return convention("jvm.library")
}

fun PluginDependenciesSpec.kotlinMultiplatformLibrary(): PluginDependencySpec {
    return convention("kotlin.multiplatform.library")
}

fun PluginDependenciesSpec.kotlinMultiplatformLibraryCompose(): PluginDependencySpec {
    return convention("kotlin.multiplatform.library.compose")
}

fun PluginDependenciesSpec.koin(): PluginDependencySpec {
    return convention("koin")
}

fun PluginDependenciesSpec.koinAnnotations(): PluginDependencySpec {
    return convention("koin.annotations")
}

fun PluginDependenciesSpec.ksp(): PluginDependencySpec {
    return convention("ksp")
}
