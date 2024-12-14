# Explanation Guide - Jenkins

## Explanation of Jenkins


Jenkins is a automation server that helps developers automate the process of building, testing, and deploying software projects.


## Context


In this project, Jenkins will be used to automatically containerize and deploy all the applications using some Freestyle Jobs.

## Architecture


The Ansible architecture consists of the following components :

- **`config.yml` file** : Define the authorizations and the security of the project.

- **`job_dsl.groovy` file** : Contains all the freestyle jobs that jenkins can launch.

- **`plugins.txt` file** : Has all the plugins needed by Jenkins.

## Role details


### Role **Admin**

Jenkins only have one role which is Admin. But Admins can create more roles.

## Folders


### Folder **Whanos base images**

It will contains all the language base images created during jobs executions.

### Folder **Projects**

It will contains all the projects link to whanos by the jobs.

## Jobs

### Whanos base images build jobs

The first job builds a base image corresponding at the language selected

### Build all base images

This job will trigger all the language base images jobs created

### Link project

The third job links a specified project in the parameters to the Whanos infrastructure
by creating a job.

### Jobs created by the 'link-project' job

The last job created by 'link-project' checks every minute for changes in the repository.
When a change is detected on the default branch:
- containerizes the respository’s application according to the specifications described in
  the Whanos images specifications section
- if applicable, deploys the application into a Kubernetes cluster
