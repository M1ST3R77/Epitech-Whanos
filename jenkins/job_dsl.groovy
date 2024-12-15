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
                    git {
                        remote {
                            name('origin')
                            url("$GITHUB_NAME")
                            credentials('jenkins-ssh-key')
                        }
                        branch('main')
                    }
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
