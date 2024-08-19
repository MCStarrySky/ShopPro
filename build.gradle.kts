plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "2.0.11"
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
}

taboolib {
    description {
        dependencies {
            name("PlaceholderAPI").optional(true)
            name("Vault").optional(true)
            name("MMOItems").optional(true)
            name("Zaphkiel").optional(true)
            name("NeigeItems").optional(true)
        }
        contributors {
            name("xbaimiao")
        }
        val bukkitNodes = bukkitNodes as MutableMap<Any, Any>
        bukkitNodes["update-info"] = "修复提示信息错误"
    }
    env {
//        install(NMS_UTIL, UI)
//        install(EXPANSION_COMMAND_HELPER, BUKKIT_ALL)
//        install(DATABASE, CHAT, CONFIGURATION, LANG, NMS, KETHER)
        install(
            "bukkit-ui",
            "minecraft-command-helper",
            "bukkit-hook",
            "bukkit-util",
            "bukkit-xseries",
            "bukkit-xseries-item",
            "bukkit-xseries-skull",
            "platform-bukkit",
            "platform-bukkit-impl",
            "database-sql",
            "minecraft-chat",
            "basic-configuration",
            "minecraft-i18n",
            "minecraft-kether"
        )


        forceDownloadInDev = false
        repoTabooLib = "http://mcstarrysky.com:8081/repository/releases/"
    }
    version {
        taboolib = "6.2.0-beta18-dev"
    }
//    relocate("kotlin", "libs.kotlin171")
    relocate("com.xbaimiao.ktor", "com.github.xbaimiao.shoppro.stat")
}

repositories {
    maven("http://mcstarrysky.com:8081/repository/releases/") {
        isAllowInsecureProtocol = true
    }
    mavenCentral()
    maven("https://maven.xbaimiao.com/repository/maven-public/")
    maven("https://r.irepo.space/maven/")
}

dependencies {
    compileOnly("com.mojang:authlib:3.16.29")
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("public:MythicMobs:4.14.1")
    compileOnly("public:papi:1.0.0")
    compileOnly("public:vault:1.0.0")
    compileOnly("public:points:1.0.0")
    compileOnly("ink.ptms.core:v11902:11902-minimize:mapped")
    compileOnly("ink.ptms.core:v11902:11902-minimize:universal")
    compileOnly("public:Zaphkiel:1.0.0")
    compileOnly("pers.neige.neigeitems:NeigeItems:1.15.113")
    compileOnly("public:MMOItems:6.9.4")
//    taboo(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

