repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.6.10"
    `maven-publish`
    signing
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "mx.com.inftel.kotlinx.lazy")
    }
}

val kotlinJavadoc by tasks.registering(Jar::class) {
    archiveBaseName.set(project.name)
    archiveClassifier.set("javadoc")
    from(file("$projectDir/javadoc/README"))
}

publishing {

    repositories {
        maven {
            setUrl(file("$projectDir/build/repo"))
        }
    }

    publications {
        create<MavenPublication>("kotlinLazy") {
            groupId = "mx.com.inftel.oss"
            artifactId = "kotlin-lazy"
            version = "1.0.0"

            from(components["java"])
            artifact(kotlinJavadoc)

            pom {
                name.set("Kotlin (Clearable) Lazy Value")
                description.set("Representation of a (clearable) value with lazy initialization in Kotlin.")
                url.set("https://github.com/santoszv/kotlin-lazy")
                inceptionYear.set("2021")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("santoszv")
                        name.set("Santos Zatarain Vera")
                        email.set("santoszv@inftel.com.mx")
                        url.set("https://www.inftel.com.mx")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/santoszv/kotlin-lazy")
                    developerConnection.set("scm:git:https://github.com/santoszv/kotlin-lazy")
                    url.set("https://github.com/santoszv/kotlin-lazy")
                }
            }

            signing.sign(this)
        }
    }
}

signing {
    useGpgCmd()
}