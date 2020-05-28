
plugins {
    antlr
    `java-library`
    eclipse
    jacoco
    id("com.diffplug.gradle.spotless") version "3.28.0"
}

group = "net.rptools.scriptparser"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    compile("org.reflections", "reflections", "0.9.11")
    compile("org.apache.commons", "commons-text", "1.6")
    compile("com.github.jknack:handlebars:4.1.2")
    implementation("org.apache.logging.log4j", "log4j-api", "2.11.0");
    implementation("org.apache.logging.log4j", "log4j-1.2-api", "2.11.0");
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_14
}


tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
    outputDirectory = file("${project.buildDir}/generated-src/antlr/main/net/rptools/mtscript/parser".toString());
}

spotless {

    java {
        target("src/**/*.java")
        licenseHeaderFile(file("build-resources/spotless.license.java"))
        googleJavaFormat()
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
        paddedCell()
    }

    format("misc") {
        target("**/*.gradle", "**/.gitignore")

        // spotless has built-in rules for most basic formatting tasks
        trimTrailingWhitespace()
        // or spaces. Takes an integer argument if you don't like 4
        indentWithSpaces(4)
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
        paddedCell()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed", "standard_error", "standard_out")
    }
}


jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("build/reports/jacoco")
}

