plugins {
    kotlin("jvm") version "2.0.+"
    id("fabric-loom") version "1.8.+"
    id("maven-publish")
}

java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

loom {
    runtimeOnlyLog4j.set(true)
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.kosmx.dev/")
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
    maven("https://api.modrinth.com/maven"){
        content {
            includeGroup("maven.modrinth")
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${project.properties["minecraft_version"]}")

    mappings("net.fabricmc:yarn:${project.properties["yarn_mappings"]}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.properties["loader_version"]}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.properties["fabric_version"]}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${project.properties["fabric_kotlin"]}")

    modImplementation("dev.kosmx.player-anim:player-animation-lib-fabric:${project.properties["player_anim_version"]}")

    modLocalRuntime("com.terraformersmc:modmenu:${project.properties["mod_menu_version"]}")
    modApi("me.shedaniel.cloth:cloth-config-fabric:${project.properties["cloth_config_version"]}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modCompileOnly("io.github.kosmx.emotes:emotesMain:${project.properties["emotecraft_version"]}")

    modImplementation("maven.modrinth:emotecraft:${project.properties["emotecraft_version"]}+${project.properties["minecraft_version"]}-fabric")
    modImplementation("maven.modrinth:flashback:${project.properties["flashback_version"]}")

}

base {
    archivesName.set("${project.properties["archive_base_name"]}-${project.properties["mod_version"]}+mc${project.properties["minecraft_version"]}")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filteringCharset = "UTF-8"

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }

    java {
        withSourcesJar()
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}
