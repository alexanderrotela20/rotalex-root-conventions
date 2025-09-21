package io.github.alexanderrotela20.convention.ktx

enum class BuildType(val applicationIdSuffix: String? = null) {
	DEBUG(".debug"),
	RELEASE(),
}