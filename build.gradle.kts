plugins {
    id("base.java")
    id("base.application")
    id("configuration.shaded_dependencies")
}

dependencies {
    shadedDependencies(libs.pdfbox)
}
