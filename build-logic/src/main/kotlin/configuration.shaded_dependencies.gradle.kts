// https://github.com/florianreuth/BaseProject

plugins {
    id("base.java")
}

val shadedDependencies = configurations.create("shadedDependencies") {
    isCanBeResolved = true
    isCanBeConsumed = true
}

configurations.implementation {
    extendsFrom(shadedDependencies)
}

tasks.jar {
    dependsOn(shadedDependencies)
    from({
        shadedDependencies.map {
            zipTree(it)
        }
    }) {
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
