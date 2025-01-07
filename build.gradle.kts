plugins {
    id("java")

}

group = "ru.phestrix"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java{
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.jar{
    manifest {
        attributes["Main-Class"] = "Main"
    }
}

tasks.test {
    useJUnitPlatform()
}