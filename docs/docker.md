# Explanation Guide - Docker

## Explanation of Docker


Docker is a platform for creating, sharing and running applications in lightweight, isolated containers, guaranteeing portability and efficiency.


## Context


In this project, Docker is used to create two types of images: **base** and **standalone**, for each language managed by Whanos. These images include the contents of the selected project's repository, enabling it to be run in a Dockerized environment.


## Image Types


### **Standalone Images**

**Standalone images** are Docker images with a default runtime environment configuration for a language. They are used when :
- No specific modification of the runtime environment is required.
- No Dockerfile is present at the repository root.

These images offer a quick and simple solution for running applications directly, without additional customization.

### **Base Images**

**Base images** are Docker images that require additional configuration of their execution environment. They are distinguished by :
- The presence of a custom Dockerfile at the repository root to define specific instructions.
- Their use as a starting point for creating other images via the `FROM` instruction.

**Base images** share the default configuration of standalone images, but also include **`ONBUILD` instructions** for :
- Automatically copy repository content into the image.
- Execute the instructions needed to configure the application when the base image is used.

These features make **Base images** a flexible tool for projects requiring specific adjustments or dependencies.


## Language


### Befunge

Befunge is a programming language where code runs in a two-dimensional grid.

#### **Requirements**
- Use only official Docker images.
- The version used is **Befunge-93**.
- The project must contain a `main.bf` file in an `app` folder at the root.
- The execution must be done at root.
- The generated Docker image must be named **`whanos-befunge`**.

#### **Dockerfile contents**
Here are the steps for building the Docker image for Befunge :

1. Using the official **gcc** image to start a bash environment.
2. Copy the contents of the repository into the `/app` folder of the image.
3. Clone the repository containing the C code needed to run Befunge code.
4. Run the **Makefile** to compile and generate the Befunge executable, then copy it to the root.
5. Removes all unnecessary content from the image.
6. Switch to a new image based on **Alpine**, install **bash**, and add the **libc6-compat** library to support the executable.
7. Copy the Befunge executable from the gcc image to the `/app` folder.
8. Run the Befunge interpreter with the `main.bf` file.

#### **Note**
Unlike the Standalone image, the Base image does not isolate the installation of dependencies in a second image. All steps are executed in a single image.

### C

C is a programming language used to develop systems, applications and embedded software.

#### **Requirements**
- Use only official Docker images.
- The version used is **gcc-13.2**.
- The project must contain a `Makefile` file at the root.
- Compilation is done with `make`.
- The executable must be called `compiled-app`.
- The execution must be done with `./compiled-app`.
- The generated Docker image must be named **`whanos-c`**.

#### **Dockerfile contents**
Here are the steps for building the Docker image for C :

1. Using the official **gcc** image to start a bash environment.
2. Copy the contents of the repository into the `/app` folder of the image.
3. Run the **Makefile** to compile and generate the executable.
4. Removes all unnecessary content from the image.
5. Switch to a new image based on **Alpine**, install **bash**, and add the **libc6-compat** library to support the executable.
6. Copy the executable from the gcc image to the `/app` folder.
7. Run the executable `compiled-app`.

#### **Note**
Unlike the Standalone image, the Base image does not isolate the installation of dependencies in a second image. All steps are executed in a single image.

### Java

Java is a versatile, portable, object-oriented programming language for developing robust applications.

#### **Requirements**
- Use only official Docker images.
- The version used is **jdk-21**.
- The project must contain a `pom.xml` file in an `app` folder at the root.
- Use `Maven`.
- Compilation is done with `mvn package`.
- The executable must be called `app.jar`.
- The execution must be done with `java -jar app.jar`.
- The generated Docker image must be named **`whanos-java`**.

#### **Dockerfile contents**
Here are the steps for building the Docker image for Java :

1. Using the official **maven** image to start a bash environment.
2. Copy the contents of the repository into the `/app` folder of the image.
3. Starts installation and compilation with **mvn** and `pom.xml` and copies the executable to the root.
4. Removes all unnecessary content from the image.
5. Switch to a new image based on **openjdk**, install **bash**.
6. Copy the executable from the maven image to the `/app` folder.
7. Run the executable `app.jar`.

#### **Note**
Unlike the Standalone image, the Base image does not isolate the installation of dependencies in a second image. All steps are executed in a single image.

### Javascript

JavaScript is a lightweight, interpreted programming language mainly used to make web pages interactive, with both client- and server-side functionality.

#### **Requirements**
- Use only official Docker images.
- The version used is **node-20.9**.
- The project must contain a `package.json` file at the root.
- Use `npm`.
- The execution must be done with `node .`.
- The generated Docker image must be named **`whanos-javascript`**.

#### **Dockerfile contents**
Here are the steps for building the Docker image for Javascript :

1. Using the official **node** image to start a bash environment.
2. Copy the contents of the repository into the `/app` folder of the image.
3. Run `npm` to install the dependencies.
4. Run `node` to launch the project.

### Python

Python is a versatile and readable programming language. It is used in fields as diverse as web development, data science, automation and artificial intelligence.

#### **Requirements**
- Use only official Docker images.
- The version used is **python-3.12**.
- The project must contain a `requirements.txt` file at the root.
- Use `pip`.
- The execution must be done with `python -m app`.
- The generated Docker image must be named **`whanos-python`**.

#### **Dockerfile contents**
Here are the steps for building the Docker image for Python :

1. Using the official **python** image to start a bash environment.
2. Copy the contents of the repository into the `/app` folder of the image.
3. Run `pip` to install the dependencies.
4. Run `python` to launch the project.

### C++

C++ is a high-performance, object-oriented, compiled programming language that extends the functionality of C with modern concepts such as classes, templates and dynamic memory management.

#### **Requirements**
- Use only official Docker images.
- The project must contain a `CMakeLists.txt` file at the root.
- Compilation is done with `cmake`.
- The executable must be called `compiled-app`.
- The execution must be done with `./compiled-app`.
- The generated Docker image must be named **`whanos-cpp`**.

#### **Dockerfile contents**
Here are the steps for building the Docker image for C++ :

1. Using the official **gcc** image to start a bash environment.
2. Copy the contents of the repository into the `/app` folder of the image.
3. Install cmake on the image builder.
4. Run the **CMakeLists.txt** with `cmake --build` to compile and generate the executable.
5. Removes all unnecessary content from the image and copy the executable to the root of the image builder.
6. Switch to a new image based on **Alpine**, install **bash**, and add the **libc6-compat**, **libstdc++** and **libgcc** library to support the executable.
7. Copy the executable from the gcc image to the `/app` folder.
8. Run the executable `compiled-app`.

#### **Note**
Unlike the Standalone image, the Base image does not isolate the installation of dependencies in a second image. All steps are executed in a single image.

### Go

Go is a simple, compiled programming language designed by Google to facilitate the development of high-performance, scalable software, with automatic memory management and integrated tools.

#### **Requirements**
- Use only official Docker images.
- The project must contain a `main.go` file in an `app` folder at the root.
- Compilation is done with `go`.
- The executable must be called `compiled-app`.
- The execution must be done with `./compiled-app`.
- The generated Docker image must be named **`whanos-go`**.

#### **Dockerfile contents**
Here are the steps for building the Docker image for Go :

1. Using the official **golang** image to start a bash environment.
2. Copy the contents of the repository into the `/app` folder of the image.
3. Run `go build` to compile and generate the executable.
4. Removes all unnecessary content from the image.
5. Switch to a new image based on **Alpine**, install **bash**.
6. Copy the executable from the gcc image to the `/app` folder.
7. Run the executable `compiled-app`.

#### **Note**
Unlike the Standalone image, the Base image does not isolate the installation of dependencies in a second image. All steps are executed in a single image.