# Computer Setup

## Arch Linux

### Installation

Install yay as an package manager that can download from AUR.

#### Docker

Docker Desktop is properietary and for commercial use you need a license for it. For private use it is free though.

#### Nerdctl

Nerdctl is basically the same as docker, but for containerd and fully open source.

```sh
# postgresql is added for psql cli
yay -S nerdctl cni-plugins postgresql
```

There are two options to run containers with:
- [Kubernetes (k3s)](Kubernetes)
- [Nerdctl](Nerdctl) 

#### Quarkus

```sh
curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio
```

##### Add quarkus to PATH

Fish shell:
```sh
fish_add_path .jbang/bin/
```

## MacOS

### Installation

First Brew needs to be installed in MacOs.

#### Docker

Docker Desktop is properietary and for commercial use you need a license for it. For private use it is free though.

#### Lima (Docker alternative)

Instead of Docker Desktop and all that we'll opt for Lima which is basically like WSL (Windows) or ContainerD (Linux).

```sh
brew install lima
limactl start
```

You can either directly from the terminal run lima nerdctl for docker commands or you can first write lima which will bring you into the virtual machine and there you can directly run nerdctl.


There are two options to run containers with:
- [Kubernetes (k3s)](Kubernetes)
- [Nerdctl](Nerdctl)

##### GraalWM (build for native)

Install:
```sh
brew install --cask graalvm/tap/graalvm-ce-java17
```

Set environment variables (with fish):
```sh
fish_add_path /Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.1.0/Contents/Home/bin/
set -Ux JAVA_HOME /Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.1.0/Contents/Home/
```

Install native image:
```sh
gu install native-image
```

##### Alias docker with nerdctl or GraalVM wont work with Quarkus
```sh
alias docker nerdctl.lima
```

#### DB tools

##### psql (CLI)

```sh
brew install postgresql
```

##### Gobang (TUI)

```sh
brew install tako8ki/tap/gobang
```

#### Quarkus

```sh
brew install quarkusio/tap/quarkus
```

# Virtualizing

## Kubernetes (Not working yet)

### k3s

```sh
# Arch linux
yay -S k3s-1.xx-bin kubectl helm k9s

# MacOS
brew install <some-k3s-package> <some-kubectl-package> helm k9s
```

```sh
sudo k3s server
```

### Rancher Desktop

**Gui for nerdctl in which one can run kubernetes as well.**
Here we can use Rancher Desktop, basically like Docker Desktop in many ways but Open Source instead of proprietary which Docker Desktop is.

```sh
# Arch linux
yay -S rancher-desktop

# MacOS
brew install rancher-desktop
```

#### Setup a HELM repo

```sh
helm repo add bitnami https://charts.bitnami.com/bitnami
```

#### Build

```sh
quarkus build --clean --refresh
nerdctl.lima --namespace k8s.io build -f api/src/main/docker/Dockerfile.jvm -t quarkus/cqrs-multi-module .
```

#### Run
```sh
kubectl run --image 
```

## Nerdctl

### Create Postgres DB

Setup postgresql container
```sh
# Linux
sudo nerdctl run -d -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -p 5432:5432 --name postgresql postgres

# MacOS
lima nerdctl run -d -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -p 5432:5432 --name postgresql postgres
```

Login to postgresql server
```sh
psql -U admin -W -p 5432 -h localhost
```

Create database
```sql
CREATE DATABASE db;
```
# Project setup

```sh
# Create project
quarkus create app com.test:reactive-example
cd reactive-example

# Install extensions
quarkus ext add quarkus-hibernate-reactive-panache
quarkus ext add quarkus-resteasy-reactive
quarkus ext add quarkus-resteasy-reactive-jackson
quarkus ext add reactive-pg-client

# Remove unecessary extension
quarkus ext rm quarkus-resteasy
```

## Run project

```sh
quarkus dev
```

Open swagger page: (openapi http://localhost:8080/q/swagger-ui)[http://localhost:8080/q/swagger-ui]

## Build native

If you have setup GraalVM you can:
```sh
quarkus build --native
```

## Docker / Nerdctl

### Build

```sh
# Docker
docker build -f api/src/main/docker/<dockerfile> -t <name-of-image> .

# nerdctl MacOS
nerdctl.lima build -f api/src/main/docker/<dockerfile> -t <name-of-image> .

# nerdctl Linux
nerdctl build -f api/src/main/docker/<dockerfile> -t <name-of-image> .
```

### Run
