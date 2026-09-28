plugins {
    java
}

val applicationMain = property("application_main") as String
tasks.jar {
    manifest {
        attributes("Main-Class" to applicationMain)
    }
}
