import de.florianreuth.baseproject.core.configureApplication
import de.florianreuth.baseproject.core.configureShadedDependencies
import de.florianreuth.baseproject.setupProject

plugins {
    id("de.florianreuth.baseproject")
}

setupProject()
configureApplication()

val shade = configureShadedDependencies()

dependencies {
    shade("org.apache.pdfbox:pdfbox:3.0.8")
}
