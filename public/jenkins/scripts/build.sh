#!/bin/bash

LANGUAGE=$1
NAME=$2

IMAGE_NAME=whanos-$NAME-$LANGUAGE
IMAGE_PATH=203.0.113.10:30001/"$IMAGE_NAME"

if [[ -f Dockerfile ]]; then
        docker build . -t "$IMAGE_NAME"
else
        docker build . -f /var/jenkins_home/jenkins/images/"$LANGUAGE"/Dockerfile.standalone -t "$IMAGE_NAME"
fi

docker image tag "$IMAGE_NAME" "$IMAGE_PATH"

if ! docker image push "$IMAGE_PATH"; then
        exit 1
fi
