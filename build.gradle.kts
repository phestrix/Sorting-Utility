plugins {
    id("java")

}

group = "ru.phestrix"


repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

java{
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.jar{
    manifest {
        attributes["Main-Class"] = "Main"
    }
}