# Guide to setting up a Kubernetes cluster with Ansible

## Step 1: Install Ansible
Let's start by installing ansible on your machine.


```bash
sudo apt update
sudo apt install ansible -y
```

## Step 2: Start your 2 virtual machines

1. **Create two virtual machines:**
   - A master machine
   - A worker machine

2. **Configure them to enable SSH connection with an RSA private key.**
   - RSA private key generation (if you don't already have one) :

        ```bash
        ssh-keygen -t rsa -b 4096 -f id_rsa
        ```
   - Copying public keys on virtual machines :

        ```bash
        ssh-copy-id -i id_rsa.pub <machine_user>@<machine_ip>
        ```

## Step 3: Connection configuration for Ansible

Create the empty file joiner.yml in the group_vars folder.

Create the file all.yml in the group_vars folder.


Typical structure of the group_vars/all.yml file :

```yaml
master_ip: 127.0.0.1
worker_ip: 192.0.0.2
ansible_user_master: ubuntu
ansible_user_worker: ubuntu
private_key_path_master: ./id_rsa
private_key_path_worker: ./id_rsa
```

## Step 4: Launching ansible
Run from project root the following command:

```bash
ansible-playbook -i ansible/production ansible/playbook.yml
```

This command will install everything needed to create a Kubernetes cluster with :

   - A master node
   - A worker node

## Step 5: Check installation

1. **Connect to the master machine via SSH :**

    ```bash
    ssh -i id_rsa <machine_user>@<master_ip>
    ```

2. **Check the status of Kubernetes nodes:**

    ```bash
    kubectl get nodes
    ```

The guide is now complete and you have successfully set up a kubernetes cluster.