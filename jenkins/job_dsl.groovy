def languages = [
    "c",
    "python",
    "java",
    "javascript",
    "befunge"
]

folder('Whanos base images') {}
folder('Projects') {}


languages.each{ language ->
    freeStyleJob("Whanos base images/whanos-$language") {
        steps {
            shell("docker build /images/$language -f /images/$language/Dockerfile.base -t whanos-$language")
        }
    }
}

freeStyleJob('Whanos base images/Build all base images') {
    publishers {
        languages.each{ language ->
            downstream("Whanos base images/whanos-$language", 'SUCCESS')
        }
    }
}

freeStyleJob('link-project') {
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam('DISPLAY_NAME', '', 'Display name for the job')
    }
    steps {
        dsl {
            text('''
            freeStyleJob("Projects/$DISPLAY_NAME") {
                triggers {
                    scm("* * * * *")
                }
                scm {
                    github("$GITHUB_NAME")
                }
                wrappers {
                    preBuildCleanup()
                }
                steps {
                    shell('ls')
                }
            }
            ''')
        }
    }
}

//
// // freeStyleJob('Whanos base images') {
// //     steps {
// //         script {
// //             languages.each { language, imageName ->
// //                 def folderPath = "/jenkins/Whanos base images"
// //                 def jobName = imageName
// //                 def jobExists = Jenkins.instance.getItem(jobName)
// //
// //                 if (!jobExists) {
// //                     def scriptPath = '/scripts/build.sh'
// //                     def process = ["bash", scriptPath].execute()
// //                     process.waitFor()
// //                 }
// //             }
// //         }
// //     }
// // }
//
// // freeStyleJob('Build all base images') {
// //     steps {
// //         script {
// //             languages.each { language, imageName ->
// //                 def folderPath = "/jenkins/Whanos base images"
// //                 def jobName = imageName
// //                 def jobExists = Jenkins.instance.getItem(jobName)
// //
// //                 if (!jobExists) {
// //                     def scriptPath = '/scripts/build.sh'
// //                     def process = ["bash", scriptPath].execute()
// //                     process.waitFor()
// //                 }
// //             }
// //         }
// //     }
// // }
// //
// // freeStyleJob('link-project') {
// //     steps {
// //         script {
// //
// //         }
// //     }
// // }
