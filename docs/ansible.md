# Explanation Guide - Ansible

## Explanation of Ansible


Ansible is an automation tool for managing IT tasks such as installing and configuring software on multiple machines simultaneously.


## Context


In this project, Ansible will be used to install and configure a Kubernetes cluster to manage images of selected projects and deploy them where possible.

**Here are the various installations required:**

- **Docker**
- **Helm**
- **Outils Kubernetes** (kubeadm, kubelet, kubectl)
- **Docker Registry**
- **Jenkins**

Docker, Helm and Kubernetes tools will be installed via `apt`, while Docker Registry and Jenkins will be installed using **Helm**.


## Architecture


The Ansible architecture consists of the following components :

- **`production` file** : Contains information about the machines to be managed (IP, users, path to private keys for SSH).

- **`playbook.yml` file** : This is the main Ansible file that defines roles and tasks.

- **`group_vars` folder** : Contains configuration files for common variables used during installation.

- **`roles` folder** : Contains the different roles for each type of machine (base, master, worker), each with specific tasks.


## Roles details


### Role **Base**

The **base** role tasks are performed on all machines. They include updating and installing the necessary software, as well as configuring settings for Kubernetes.

#### Tasks :
- **Initialize the machine** :
    - **Update** : Update packages via `apt`.
    - **Upgrade** : Upgrade packages via `apt`.
    - **Install packages** : Installation of the necessary packages.
    - **Set bash** : Switch to `root` user for command execution.

- **Load kernel modules for Kubernetes** :
    - **Create kernel modules configuration file** : Enables this configuration to be retained on reboot.
    - **Load kernel modules** : Modifies configuration without rebooting.

- **Set kernel parameters for Kubernetes** :
    - **Set kernel parameters for Kubernetes** : Create network configuration file.
    - **Apply sysctl settings** : Applying network parameters to Kubernetes.

- **Configuring containerd** :
    - **Install containerd** : Installation of `containerd` via `apt` to manage Kubernetes pods.
    - **Create containerd configuration directory** : Creation of the `containerd` configuration folder.
    - **Generate default containerd configuration with systemd cgroup** : Default generation of `containerd` using `systemd`.
    - **Modify containerd configuration to enable systemd cgroups** : Updated Kubernetes usage to use `systemd`.
    - **Restart containerd service** : Enables `containerd` to be updated via restart.

- **Install Docker dependencies** :
    - **Add Docker GPG apt Key** : Use of the official `Docker` key for installation.
    - **Add Docker Repository** :Added the `Docker` repository for downloading.
    - **Update apt and install docker-ce** : Updated `apt` and installed `docker-ce`.
    - **Install Docker Module for Python** : Install the `Python Docker` module to interact with Docker via Python

- **Install Kubernetes dependencies** :
    - **Create directory for Kubernetes keyrings** : Creation of a folder to store the installation key for Kubernetes tools.
    - **Add Kubernetes GPG key** : Use of `k8s` key for installation.
    - **Add Kubernetes apt repository** : Added `k8s` repository to enable Kubernetes tools to be downloaded.
    - **Download Helm installation script** : Download a script to install `Helm`.
    - **Run Helm installation script** : Install `Helm`.
    - **Add /usr/local/bin to PATH** : Add executable path to `PATH`.
    - **Install kubeadm, kubelet and kubectl** : Download the three Kubernetes tools.


### Role **Master**


The **master** role is intended for machines that will be master nodes in the Kubernetes cluster. These nodes manage the entire cluster and its components.

#### Tasks :
- **Initialisation du cluser Kubernetes** :
    - **Initialize Kubernetes master** : Run the `kubeadm init` command to initialize the Kubernetes cluster with a default network plugin.
    - **Get HOME directory of user** : Retrieve the `HOME` of the current user.
    - **Set up kubectl for the root and current user** : Configuration of `kubectl` for the root user and the current user in order to interact with the Kubernetes cluster.
    - **Install Calico network plugin** : Application of the Calico manifest for the pod network in Kubernetes.
    - **Get join command** : Retrieve the `join` command to add worker nodes to the cluster.
    - **Save join command to a temporary file on master** : Save the `join` command to a local file.
    - **Fetch join command to local group_vars** : Create a file in the `group_vars` folder to store the `join` command.

- **Configuration et déployement des scripts** :
    - **Copy scripts folder into master** : Copy the necessary scripts to the master machine.


### Role **Worker**


The **worker** role is intended for machines that will be execution nodes in the Kubernetes cluster. These nodes host the pods running the applications.

#### Tasks :
- **Liaison de la node worker au cluster** :
    - **Load join command from file** : Include the `join` command in ansible's variables.
    - **Join the cluster** : Execute the `join` command on the worker node to add it to the Kubernetes cluster.
