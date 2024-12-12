#!/bin/sh

# Create "deployment" and "app" namespace
kubectl create namespace deployment
kubectl create namespace app

# Init repositories
helm repo add jenkins https://charts.jenkins.io
helm repo add twuni https://helm.twun.io
helm repo update

# Install Jenkins and Docker registry
helm install jenkins/jenkins --set namespace=deployment
helm install twuni/docker-registry --set namespace=deployment
