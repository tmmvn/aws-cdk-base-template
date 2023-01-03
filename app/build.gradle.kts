task("createCommit", Zip::class) {
  archiveFileName.set("commit.zip")
  destinationDirectory.set(layout.buildDirectory.dir("../../initial"))
  val tree = fileTree(project.rootDir) {
    exclude("initial", "**/cdk.out/**")
  }
  from(tree)
}

tasks.named("run") {
  dependsOn("createCommit")
}

plugins {
  java
  application
}

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  // CDK
  implementation("software.constructs:constructs:10.1.206")
  implementation("software.amazon.awscdk:aws-cdk-lib:2.58.1")
}

group = "tbd"
version = "beta"
description = "CdkBaseApp"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

application { // Define the main class for the application.
  mainClass.set("com.tbd.backend.CdkBaseApp")
}

/**
 * Copies files from "build cdk.out" to "root cdk.out" directory
 * Avoids need to cdk synth first, then getting an error due to the cdk.out directory not existing, and having to run
 * deploy with -app setting to get stuff to work.
 * NOTE: There might be some other workarounds
 * */
tasks.register<Copy>("finalCopy") {
  println("Copying CDK output files!!!")
  val sourceDir = layout.buildDirectory.dir("../cdk.out")
  val targetDir = layout.buildDirectory.dir("../../cdk.out")
  println("from:$sourceDir to:$targetDir")
  from(sourceDir)
  include("**/*.*")
  into(targetDir)
}

tasks.named("run") { finalizedBy("finalCopy") }
