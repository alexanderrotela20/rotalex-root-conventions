package io.github.alexanderrotela20.convention.ktx

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureKsp(commonExtension: CommonExtension<*, *, *, *, *, *>) {
	commonExtension.apply {
		sourceSets.named("main").configure {
			kotlin.srcDir("build/generated/ksp/main/kotlin")
		}
		/*sourceSets.getByName("androidTest") {
			kotlin.srcDir("build/generated/ksp/androidTest/kotlin")
		}
		sourceSets.getByName("test") {
			kotlin.srcDir("build/generated/ksp/test/kotlin")
		}*/

	}
}