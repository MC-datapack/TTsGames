plugins {
    java
    //kotlin("jvm") version "2.1.0"
}

group = project.group.toString()
version = project.version.toString()

java {
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(project.property("java_version").toString().toInt()))
    }
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf(project.property("java_dir").toString()))
        }
        resources {
            setSrcDirs(listOf(project.property("resource_dir").toString(), project.property("generated_resource_dir").toString()))
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:${project.property("gson_version")}")
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching(project.property("main_json").toString()) {
        expand("version" to project.version)
    }
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes(
            "Main-Class" to project.property("main_class").toString()
        )
    }
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
    from(project.property("resource_dir").toString())
    from(project.property("generated_resource_dir").toString())
}
