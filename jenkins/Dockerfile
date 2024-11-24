FROM jenkins/jenkins:latest
COPY plugins.txt /var/jenkins_home/plugins.txt
RUN jenkins-plugin-cli --plugin-file /var/jenkins_home/plugins.txt