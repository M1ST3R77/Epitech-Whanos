# Explanation Guide - Kubernetes

## Setup

### Namespaces

For this app we need 2 namespaces :
- deployment
- app

```shell
kubectl create namespace deployment
kubectl create namespace app
```

### Repositories

For this app we need some repositories :
```shell
helm repo add stable https://charts.helm.sh/stable
helm repo add jenkins https://charts.jenkins.io
helm repo add twuni https://helm.twun.io
helm repo update
```

### Storages

#### Local Path

We need to install local-path storage class for jenkins defaults
```shell
kubectl apply -f https://raw.githubusercontent.com/rancher/local-path-provisioner/master/deploy/local-path-storage.yaml
kubectl patch storageclass local-path -p '{"metadata": {"annotations":{"storageclass.kubernetes.io/is-default-class":"true"}}}'
```

#### NFS

We need a nfs server to allow our nodes to share files like jenkins configuration
```shell
helm install nfs-server stable/nfs-server-provisioner -n deployment
```

#### Setup volumes

Let's deploy our volumes for jenkins configuration
```shell
kubectl apply -f /scripts/persistent-volume.yaml
kubectl apply -f /scripts/persistent-volume-claim.yaml
```

### Docker registry

We can install docker registry with helm in the namespace deployment
```shell
helm install docker-registry twuni/docker-registry -n deployment --set namespace=deployment
```

### Jenkins

We can now install jenkins with base configuration :
```shell
helm install jenkins jenkins/jenkins -n deployment -f /scripts/jenkins-values.yaml
```

## Deployment

To deploy apps, we use helm with templating (chart).
In this section, we will explain how chart work.

### Chart
We have 3 parts :
- Chart.yaml
- values.yaml
- templates

#### Chart.yaml
This file describe our chart app with some default values to describe the chart

#### templates
Here is our templates that will use to our apps with values located in values.yaml and modifiable by the user

We have a service to handle the access to our pods and a deployment to describe the pods of our apps

#### values.yaml

in our values, we can configure lots of feature

| Value                                       | Description                   | Default      |
|---------------------------------------------|-------------------------------|--------------|
| namespace                                   | Namespace of our app          | app          |
| deployment.replicas                         | Nb of replicas                | 1            |
| deployment.resources.requests.memory        | Memory allowed to the app     | 64Mi         |
| deployment.resources.requests.cpu           | Cpu allowed to the app        | 250m         |
| deployment.resources.requests.limits.memory | Max memory allowed to the app | 128Mi        |
| deployment.resources.requests.limits.cpu    | Max cpu allowed to the app    | 500m         |
| deployment.ports                            | Ports exposed of the app      | []           |
| service.restartPolicy                       | Restart policy                | Always       |
| image.pullPolicy                            | Image pull policy             | IfNotPresent |
