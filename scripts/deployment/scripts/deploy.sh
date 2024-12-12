#!/bin/sh

LANGUAGE=$1
NAME=$2

IMAGE_NAME=whanos-$NAME-$LANGUAGE
IMAGE_PATH=docker-registry.deployment:5000/"$IMAGE_NAME"

helm upgrade --set dockerImage="$IMAGE_PATH" --set name="$IMAGE_NAME" -f ./whanos.yml "$IMAGE_NAME" /scripts/deployment/chart
