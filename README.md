# Build Conventions for Rotalex Projects

A comprehensive suite of Gradle convention plugins and version catalogs designed to standardize and simplify build configurations for Rotalex Projects.

## Features

### Version Catalog
Centrally manage dependencies and plugins. Ensures consistency across multiple repositories by consuming the published artifact.

### Convention Plugins
Reusable configurations for various module types:
- **Android:** Application, Library, Compose, Native (NDK), Test, and Lint support.
- **Kotlin Multiplatform:** Base configurations for KMP and Compose Multiplatform.
- **JVM:** Standardized setup for pure Kotlin/Java libraries.
- **Tools:** Pre-configured support for Koin (DI), Koin Annotations, and KSP.

## Usage

### 1. Version Catalog
Add the remote catalog to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    versionCatalogs {
        create("rootLibs") { 
            from("io.github.alexanderrotela20:version-catalog:${latest-version}")
        }
    }
}
```

> [!IMPORTANT]  
> The catalog **must** be named `rootLibs` for the convention plugins to correctly resolve internal dependencies and SDK versions.

### 2. Convention Plugins
Apply the plugins in your module's `build.gradle.kts` using catalog aliases:

```kotlin
plugins {
    alias(rootLibs.plugins.convention.android.library)
    alias(rootLibs.plugins.convention.android.library.compose)
    alias(rootLibs.plugins.convention.koin)
}
```

## Publishing

To publish new versions to Maven Central:

```bash
./gradlew publishToMavenCentral
```

## License

Copyright 2025 Alexander Rotela. Licensed under the Apache License, Version 2.0.
