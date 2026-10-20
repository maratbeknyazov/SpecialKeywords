plugins {
    kotlin("jvm") version "2.2.0"
}

repositories {
    mavenCentral()
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// One JavaExec task per lab task: run with input fed from inputs/taskNN.txt
fun makeRun(n: Int) {
    val num = "%02d".format(n)
    tasks.register<JavaExec>("run$num") {
        group = "run"
        mainClass = "task$num.MainKt"
        classpath = sourceSets["main"].runtimeClasspath
        standardInput = System.`in`
    }
}
(1..15).forEach { makeRun(it) }

// Prints the runtime classpath so a script can run java directly.
tasks.register("printClasspath") {
    doLast { println(sourceSets["main"].runtimeClasspath.asPath) }
}
