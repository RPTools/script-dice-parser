import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


plugins {
    antlr
    `java-library`
    eclipse
    jacoco
    application
    id("com.diffplug.gradle.spotless") version "4.3.0"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "net.rptools.scriptparser"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    implementation("org.reflections", "reflections", "0.9.11")
    implementation("org.apache.commons", "commons-text", "1.6")
    implementation("org.apache.commons", "commons-text", "1.6")
    implementation("com.github.jknack:handlebars:4.1.2")
    implementation("org.apache.logging.log4j", "log4j-api", "2.11.0");
    implementation("org.apache.logging.log4j", "log4j-1.2-api", "2.11.0");
    implementation("com.google.inject:guice:4.2.3")
    implementation("com.google.inject.extensions:guice-assistedinject:4.2.3")
    implementation("com.google.inject.extensions:guice-testlib:4.2.3")
    implementation("commons-cli:commons-cli:1.4")
    implementation("com.google.code.gson", "gson", "2.8.6");
    testCompile("org.mockito:mockito-core:3.3.3");

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
        targetExclude("src/main/gen/*", "src/main/antlr/gen/*")
        licenseHeaderFile(file("build-resources/spotless.license.java"))
        googleJavaFormat("1.8")
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
    }

    format("misc") {
        target("**/*.gradle", "**/.gitignore")

        // spotless has built-in rules for most basic formatting tasks
        trimTrailingWhitespace()
        // or spaces. Takes an integer argument if you don't like 4
        indentWithSpaces(4)
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
    testLogging {
        events("passed", "skipped", "failed", "standard_error", "standard_out")
    }
}


jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("build/reports/jacoco")
}

application {
    mainClassName = "net.rptools.mtscript.Main"
}

tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "net.rptools.mtscript.Main"
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<JavaExec> {
    jvmArgs("--enable-preview")
}
