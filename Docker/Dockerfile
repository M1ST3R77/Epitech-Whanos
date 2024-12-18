FROM jenkins/jenkins:latest

USER root
RUN apt-get update
RUN apt-get -y install apt-transport-https ca-certificates curl software-properties-common
RUN mkdir -p /etc/apt/keyrings \
    && chmod 755 /etc/apt/keyrings
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg
RUN curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.31/deb/Release.key | gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg
RUN echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian bookworm stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null
RUN echo "deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.31/deb/ /" | tee /etc/apt/sources.list.d/kubernetes.list
RUN apt-get update
RUN apt-get install -y docker-ce kubectl
RUN echo '{"insecure-registries":["203.0.113.10:30001"]}' > /etc/docker/daemon.json
RUN usermod -aG docker jenkins
RUN curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 | bash

COPY plugins.txt /var/jenkins_home/plugins.txt
RUN jenkins-plugin-cli --plugin-file /var/jenkins_home/plugins.txt