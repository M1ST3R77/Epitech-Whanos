# Kubernetes

## Jenkins

### Installation

To install jenkins, you just must run this commands :
```shell
helm repo add jenkins https://charts.jenkins.io
helm repo update
helm install jenkins/jenkins --set namespace=deployment
```

## Docker registry

To install Docker Registry, you just must run this commands :
```shell
helm repo add twuni https://helm.twun.io
helm repo update
helm install twuni/docker-registry --set namespace=deployment
```


Script to setup docker image
```shell
helm upgrade --set dockerImage=docker-registry.deployment:5000/whanos-<name>-<language> --set name=whanos-<name>-<language> -f ./whanos.yml whanos-<name>-<language> /scripts/deployment/chart
```