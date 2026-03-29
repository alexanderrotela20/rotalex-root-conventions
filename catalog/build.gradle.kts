import com.vanniktech.maven.publish.VersionCatalog

plugins {
    `version-catalog`
    alias(libs.plugins.mavenPublish)
}

catalog {
    versionCatalog { from(files("libs.versions.toml")) }
}

mavenPublishing {
    configure(VersionCatalog())
    publishToMavenCentral(true)
    signAllPublications()
}