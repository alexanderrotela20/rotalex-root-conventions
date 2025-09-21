# Gradle Convention Plugins for My Projects 🚀

A reusable set of Gradle convention plugins to streamline the build process for my projects.

## 📜 Description

This project centralizes the build logic for my Android and Kotlin Multiplatform (KMP) projects,
ensuring consistency and reducing boilerplate code in the `build.gradle.kts` files.

## ✨ Features

- **Android Application Plugin:** Basic configuration for Android applications.
- **Android Library Plugin:** Basic configuration for Android libraries.
- **Compose Plugin:** Adds Compose-specific dependencies and configurations.
- **Kotlin Multiplatform Plugin:** Adds KMP-specific dependencies and configurations.

## 🚀 Usage

To use these plugins, import the desired convention helper and apply it in your module's `build.gradle.kts` file. This provides a cleaner, more readable way to apply conventions.

```kotlin
import io.github.alexanderrotela20.androidLibrary
import io.github.alexanderrotela20.androidLibraryCompose

plugins {
	androidLibrary()
	androidLibraryCompose()
}
```

## 📦 Publishing

This project is set up to be published to a Maven repository. To publish a new version, ensure your credentials are configured and run the following command:

```bash
./gradlew publishToMavenCentral
```
> [!NOTE]
> Remember to update the version number in the project's build configuration before publishing.

## 📄 License

    Copyright 2025 Alexander Rotela

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
