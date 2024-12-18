#!/bin/sh

LANGUAGE="$(/var/jenkins_home/whanos/scripts/language_detector.sh)"
NAME=$1

/var/jenkins_home/whanos/scripts/build.sh "$LANGUAGE" "$NAME"

IMAGE_NAME=whanos-$NAME-$LANGUAGE
IMAGE_PATH=203.0.113.10:30001/"$IMAGE_NAME"

scp -i /var/jenkins_home/whanos/id_rsa ./whanos.yml ubuntu@203.0.113.20:/tmp/whanos.yml
ssh -i /var/jenkins_home/whanos/id_rsa ubuntu@203.0.113.20 -o StrictHostKeyChecking=no "helm upgrade -n app --set dockerImage="$IMAGE_PATH" --set name="$IMAGE_NAME" -f /tmp/whanos.yml "$IMAGE_NAME" /var/jenkins_home/whanos/chart"
ssh -i /var/jenkins_home/whanos/id_rsa ubuntu@203.0.113.20 -o StrictHostKeyChecking=no "rm -f /tmp/whanos.yml"
