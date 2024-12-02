def languages = [
    "haskell": "whanos-haskell",
    "python": "whanos-python",
    "java": "whanos-java",
    "ruby": "whanos-ruby",
    "nodejs": "whanos-nodejs"
]

pipeline {
    agent any
    stages {
        stage('Whanos base images build jobs') {
            steps {
                script {
                    languages.each { language, imageName ->
                        def folderPath = "/Whanos base images"
                        def jobName = imageName
                        def jobExists = Jenkins.instance.getItem(jobName)

                        if (!jobExists) {
                            def job = Jenkins.instance.createProject(
                                hudson.model.FreeStyleProject, jobName, folderPath)
                            def scriptPath = '/scripts/build.sh'
                            def process = ["bash", scriptPath].execute()
                            process.waitFor()
                            echo "Created job: $jobName"
                        } else {
                            echo "Job already exists: $jobName"
                        }
                    }
                }
            }
        }
    }
}