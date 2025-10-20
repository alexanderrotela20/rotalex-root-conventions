enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
}

dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
		maven { url = uri("https://jitpack.io") }
		maven { url = uri("https://repo.gradle.org/gradle/libs-releases/") }
	}
	versionCatalogs {
		create("libsa") {
			from(files("gradle/libs.versions.toml"))
		}
	}
}

rootProject.name = "rotalex-root-conventions"