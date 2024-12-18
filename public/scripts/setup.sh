#!/bin/sh

# Create "deployment" and "app" namespace
kubectl create namespace deployment
kubectl create namespace app

# Init repositories
helm repo add stable https://charts.helm.sh/stable
helm repo add jenkins https://charts.jenkins.io
helm repo add twuni https://helm.twun.io
helm repo update


# Setup volumes

## Setup Local Path
### Install Local Path Storage Class
kubectl apply -f https://raw.githubusercontent.com/rancher/local-path-provisioner/master/deploy/local-path-storage.yaml
### Make local-path as default
kubectl patch storageclass local-path -p '{"metadata": {"annotations":{"storageclass.kubernetes.io/is-default-class":"true"}}}'

## Setup nfs
### Install nfs
helm install nfs-server stable/nfs-server-provisioner -n deployment

### Create volumes
kubectl apply -f /scripts/persistent-volume.yaml
kubectl apply -f /scripts/persistent-volume-claim.yaml

# Install Jenkins and Docker registry
helm install jenkins jenkins/jenkins -n deployment -f /scripts/jenkins-values.yaml
helm install docker-registry twuni/docker-registry -n deployment --set namespace=deployment,service.type=NodePort,service.nodePort=30001