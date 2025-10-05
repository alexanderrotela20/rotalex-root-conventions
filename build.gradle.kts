import com.vanniktech.maven.publish.GradlePlugin
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
	alias(libs.plugins.mavenPublish)
}

group = "io.github.alexanderrotela20.convention"
version = "1.0.2"

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
	compileOnly(libs.android.tools.common)
	compileOnly(libs.compose.gradlePlugin)
	compileOnly(libs.compose.multiplatform.gradlePlugin)
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
			implementationClass = "io.github.alexanderrotela20.convention.AndroidApplicationConventionPlugin"
		}
		register("androidLibrary") {
			id = libs.plugins.convention.android.library.asProvider().get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.AndroidLibraryConventionPlugin"
		}
		register("androidLibraryCompose") {
			id = libs.plugins.convention.android.library.compose.get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.AndroidLibraryComposeConventionPlugin"
		}
		register("androidLibraryNative") {
			id = libs.plugins.convention.android.library.native.get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.AndroidLibraryNativeConventionPlugin"
		}
		register("androidTest") {
			id = libs.plugins.convention.android.test.get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.AndroidTestConventionPlugin"
		}
		register("androidLint") {
			id = libs.plugins.convention.android.lint.get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.AndroidLintConventionPlugin"
		}
		register("jvmLibrary") {
			id = libs.plugins.convention.jvm.library.get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.JvmLibraryConventionPlugin"
		}
		register("kotlinMultiplatformLibrary") {
			id = libs.plugins.convention.kotlin.multiplatform.library.asProvider().get().pluginId
			implementationClass =
				"io.github.alexanderrotela20.convention.KotlinMultiplatformLibraryConventionPlugin"
		}
		register("kotlinMultiplatformLibraryCompose") {
			id = libs.plugins.convention.kotlin.multiplatform.library.compose.get().pluginId
			implementationClass =
				"io.github.alexanderrotela20.convention.KotlinMultiplatformLibraryComposeConventionPlugin"
		}
		register("koin") {
			id = libs.plugins.convention.koin.asProvider().get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.KoinConventionPlugin"
		}
		register("koinAnnotations") {
			id = libs.plugins.convention.koin.annotations.get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.KoinAnnotationsConventionPlugin"
		}
		register("ksp") {
			id = libs.plugins.convention.ksp.get().pluginId
			implementationClass = "io.github.alexanderrotela20.convention.KspConventionPlugin"
		}
	}

}

project.configure<MavenPublishBaseExtension> {
	coordinates(
		groupId = project.group.toString(),
		artifactId = project.name,
		version = project.version.toString()
	)
	pom {
		name = "Gradle Conventions for Rotalex Projects"
		description = "A reusable set of Gradle convention plugins to streamline the build process for my projects."
		inceptionYear = "2025"
		url = "https://github.com/alexanderrotela20/rotalex-root-conventions"
		licenses {
			license {
				name.set("Apache-2.0")
				url.set("https://www.apache.org/licenses/LICENSE-2.0")
			}
		}
		developers {
			developer {
				id.set("alexanderrotela20")
				name.set("Alexander Rotela")
			}
		}

		scm {
			connection.set("scm:git:https://github.com/alexanderrotela20/rotalex-root-conventions.git")
			developerConnection.set("scm:git:ssh://github.com/alexanderrotela20/rotalex-root-conventions.git")
			url.set("https://github.com/alexanderrotela20/rotalex-root-conventions")
		}

	}
	publishToMavenCentral(true)
	signAllPublications()
}
