import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.10"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "QueryService"
    description = "Its query displaying service based on spring boot and Rest Apis"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
             name = "Build"
             goals = "clean install"
             runnerArgs = "-Dmaven.test.skip=true"
             jdkHome = "%env.JDK_17_0%"
        }
       
    }

    triggers {
        vcs {
            enabled = false
            branchFilter = """
                +:*
                +:new
            """.trimIndent()
        }
    }
     failureConditions {
        executionTimeoutMin = 2
        testFailure = false
    }


    features {
        perfmon {
        }
        freeDiskSpace {
            requiredSpace = "1kb"
            failBuild = true
        }
    }
    requirements {
    matches("teamcity.agent.jvm.os.family", "Linux")
}
})
