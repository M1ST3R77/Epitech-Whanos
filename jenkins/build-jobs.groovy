def languages = [
    "c": "whanos-c",
    "python": "whanos-python",
    "java": "whanos-java",
    "javascript": "whanos-javascript",
    "befunge": "whanos-befunge"
]

folder('Whanos base images') {}
folder('Projects') {}

freeStyleJob(languages["c"]) {
    steps {
        script {
            def folderPath = "/jenkins/Whanos base images"
            def jobName = languages["c"]
            def jobExists = Jenkins.instance.getItem(jobName)

            if (!jobExists) {
                def scriptPath = '/scripts/build.sh'
                def process = ["bash", scriptPath].execute()
                process.waitFor()
            }
        }
    }
}

freeStyleJob(languages["python"]) {
    steps {
        script {
            def folderPath = "/jenkins/Whanos base images"
            def jobName = languages["python"]
            def jobExists = Jenkins.instance.getItem(jobName)

            if (!jobExists) {
                def scriptPath = '/scripts/build.sh'
                def process = ["bash", scriptPath].execute()
                process.waitFor()
            }
        }
    }
}

freeStyleJob(languages["java"]) {
    steps {
        script {
            def folderPath = "/jenkins/Whanos base images"
            def jobName = languages["java"]
            def jobExists = Jenkins.instance.getItem(jobName)

            if (!jobExists) {
                def scriptPath = '/scripts/build.sh'
                def process = ["bash", scriptPath].execute()
                process.waitFor()
            }
        }
    }
}

freeStyleJob(languages["javascript"]) {
    steps {
        script {
            def folderPath = "/jenkins/Whanos base images"
            def jobName = languages["javascript"]
            def jobExists = Jenkins.instance.getItem(jobName)

            if (!jobExists) {
                def scriptPath = '/scripts/build.sh'
                def process = ["bash", scriptPath].execute()
                process.waitFor()
            }
        }
    }
}

freeStyleJob(languages["befunge"]) {
    steps {
        script {
            def folderPath = "/jenkins/Whanos base images"
            def jobName = languages["befunge"]
            def jobExists = Jenkins.instance.getItem(jobName)

            if (!jobExists) {
                def scriptPath = '/scripts/build.sh'
                def process = ["bash", scriptPath].execute()
                process.waitFor()
            }
        }
    }
}

// freeStyleJob('Whanos base images') {
//     steps {
//         script {
//             languages.each { language, imageName ->
//                 def folderPath = "/jenkins/Whanos base images"
//                 def jobName = imageName
//                 def jobExists = Jenkins.instance.getItem(jobName)
//
//                 if (!jobExists) {
//                     def scriptPath = '/scripts/build.sh'
//                     def process = ["bash", scriptPath].execute()
//                     process.waitFor()
//                 }
//             }
//         }
//     }
// }

freeStyleJob('Build all base images') {
    steps {
        script {
            languages.each { language, imageName ->
                def folderPath = "/jenkins/Whanos base images"
                def jobName = imageName
                def jobExists = Jenkins.instance.getItem(jobName)

                if (!jobExists) {
                    def scriptPath = '/scripts/build.sh'
                    def process = ["bash", scriptPath].execute()
                    process.waitFor()
                }
            }
        }
    }
}
