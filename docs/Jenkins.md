# Jenkins

In order for the applications to be automatically containerized and deployed, a Jenkins environment has been set up

## How to use Jenkins

Jenkins board allows us to launch asked commands

![Jenkins Board](JenkinsBoard.png)

`Build all base images` to build all languages images\
`link-project` to link a project to Whanos

### Build all base images

To build all languages images
- click on `Whanos base images` folder
- click on `Build all base images` job
- click on `Launch build` on the left of the screen

![Jenkins WBI](JenkinsWBI.png)

### Link project

To link a project to Whanos
- click on `link-project` job
- click on `build with parameters` on the left of the screen
- specify the GitHub repository name and the project name displayed
- click on green `Build` button

![Jenkins link project](JenkinsLP.png)
