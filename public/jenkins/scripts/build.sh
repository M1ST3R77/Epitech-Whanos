#!/bin/bash

LANGUAGE=$1
NAME=$2

IMAGE_NAME=whanos-$NAME-$LANGUAGE
IMAGE_PATH=docker-registry.deployment:5000/"$IMAGE_NAME"

if [[ -f Dockerfile ]]; then
        docker build . -t "$IMAGE_NAME"
else
        docker build . -f /var/jenkins_home/jenkins/images/"$LANGUAGE"/Dockerfile.standalone -t "$IMAGE_NAME"
fi

if ! docker push "$IMAGE_PATH"; then
        exit 1
fi
