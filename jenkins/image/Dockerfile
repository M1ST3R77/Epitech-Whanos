FROM jenkins/jenkins:jdk17
RUN jenkins-plugin-cli --plugins              \
    kubernetes                                \
    workflow-aggregator                       \
    git                                       \
    configuration-as-code::1903.v004d55388f30 \
    cloudbees-folder                          \
    credentials                               \
    github                                    \
    instance-identity                         \
    job-dsl                                   \
    script-security                           \
    structs                                   \
    role-strategy                             \
    ws-cleanup                                \
    docker-plugin                             \
    kubernetes-cli
