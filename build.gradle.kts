import de.florianreuth.baseproject.setupProject
import de.florianreuth.baseproject.configureApplication
import de.florianreuth.baseproject.configureShadedDependencies

plugins {
    id("de.florianreuth.baseproject")
}

setupProject()
configureApplication()

val shade = configureShadedDependencies()

dependencies {
    shade("org.apache.pdfbox:pdfbox:2.0.30")
}
