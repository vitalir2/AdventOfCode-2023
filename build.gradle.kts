plugins {
    kotlin("jvm") version "1.9.20"
}

dependencies {
    val kotest = "5.8.0"
    testImplementation("io.kotest:kotest-runner-junit5:$kotest")
    testImplementation("io.kotest:kotest-assertions-core:$kotest")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
