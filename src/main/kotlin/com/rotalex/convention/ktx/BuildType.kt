package com.rotalex.convention.ktx

enum class BuildType(val applicationIdSuffix: String? = null) {
	DEBUG(".debug"),
	RELEASE(),
}