import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.vanniktech.maven.publish.GradlePlugin
import com.vanniktech.maven.publish.JavadocJar

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    alias(libs.plugins.mavenPublish)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(libs.versions.jvmTarget.get())
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation.set(true)
        failOnWarning.set(true)
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.convention.android.application.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.AndroidApplicationConventionPlugin"
            displayName = "Android Application Convention Plugin"
            description = "Gradle convention plugin for Android applications."
        }

        register("androidApplicationCompose") {
            id = libs.plugins.convention.android.application.compose.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.AndroidApplicationComposeConventionPlugin"
            displayName = "Android Application Compose Convention Plugin"
            description = "Gradle convention plugin for Android applications with Jetpack Compose support."
        }

        register("androidLibrary") {
            id = libs.plugins.convention.android.library.asProvider().get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.AndroidLibraryConventionPlugin"
            displayName = "Android Library Convention Plugin"
            description = "Gradle convention plugin for Android libraries."
        }
        register("androidLibraryCompose") {
            id = libs.plugins.convention.android.library.compose.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.AndroidLibraryComposeConventionPlugin"
            displayName = "Android Library Compose Convention Plugin"
            description = "Gradle convention plugin for Android libraries with Jetpack Compose support."
        }
        register("androidLibraryNative") {
            id = libs.plugins.convention.android.library.native.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.AndroidLibraryNativeConventionPlugin"
            displayName = "Android Library Native Convention Plugin"
            description = "Gradle convention plugin for Android libraries with Native (C/C++) support."
        }
        register("androidTest") {
            id = libs.plugins.convention.android.test.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.AndroidTestConventionPlugin"
            displayName = "Android Test Convention Plugin"
            description = "Gradle convention plugin for Android test modules."
        }
        register("androidLint") {
            id = libs.plugins.convention.android.lint.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.AndroidLintConventionPlugin"
            displayName = "Android Lint Convention Plugin"
            description = "Gradle convention plugin for Android Lint configuration."
        }
        register("jvmLibrary") {
            id = libs.plugins.convention.jvm.library.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.JvmLibraryConventionPlugin"
            displayName = "JVM Library Convention Plugin"
            description = "Gradle convention plugin for JVM libraries."
        }
        register("kotlinMultiplatformLibrary") {
            id = libs.plugins.convention.kotlin.multiplatform.library.asProvider().get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.KotlinMultiplatformLibraryConventionPlugin"
            displayName = "Kotlin Multiplatform Library Convention Plugin"
            description = "Gradle convention plugin for Kotlin Multiplatform libraries."
        }
        register("kotlinMultiplatformLibraryCompose") {
            id = libs.plugins.convention.kotlin.multiplatform.library.compose.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.KotlinMultiplatformLibraryComposeConventionPlugin"
            displayName = "Kotlin Multiplatform Library Compose Convention Plugin"
            description = "Gradle convention plugin for Kotlin Multiplatform libraries with Compose Multiplatform support."
        }
        register("koin") {
            id = libs.plugins.convention.koin.asProvider().get().pluginId
            implementationClass = "io.github.alexanderrotela20.convention.KoinConventionPlugin"
            displayName = "Koin Convention Plugin"
            description = "Gradle convention plugin for Koin dependency injection."
        }
        register("koinAnnotations") {
            id = libs.plugins.convention.koin.annotations.get().pluginId
            implementationClass =
                "io.github.alexanderrotela20.convention.KoinAnnotationsConventionPlugin"
            displayName = "Koin Annotations Convention Plugin"
            description = "Gradle convention plugin for Koin Annotations support."
        }
        register("ksp") {
            id = libs.plugins.convention.ksp.get().pluginId
            implementationClass = "io.github.alexanderrotela20.convention.KspConventionPlugin"
            displayName = "KSP Convention Plugin"
            description = "Gradle convention plugin for Kotlin Symbol Processing (KSP)."
        }
    }
}

mavenPublishing {
    configure(
        GradlePlugin(
            javadocJar = JavadocJar.Javadoc(),
            sourcesJar = true,
        )
    )
    publishToMavenCentral(true)
    signAllPublications()
}
