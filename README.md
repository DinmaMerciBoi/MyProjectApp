# MyProjectApp - Step by step End-to-End Deployment process

## Introduction
The guide in the video is your first help. Again, the Terraform Scripts and manifest files have been provided in this repository. Feel free to edit as you desire.

## 1. Start a Terraform Server on AWS

## 2. Install Terraform as root user
```bash
sudo yum update –y
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/AmazonLinux/hashicorp.repo
sudo yum -y install terraform
```

## 3. Start a Jenkins Server on AWS

## 4. Install Jenkins as root user
```bash
https://www.jenkins.io/doc/book/installing/linux/
https://www.jenkins.io/doc/tutorials/tutorial-for-installing-jenkins-on-AWS/
$ sudo yum update –y
sudo wget -O /etc/yum.repos.d/jenkins.repo \
    https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
sudo yum upgrade
amazon-linux-extras install epel
sudo amazon-linux-extras install java-openjdk11 -y
yum install java-11-amazon-corretto -y
sudo yum install jenkins -y
sudo systemctl enable jenkins
sudo systemctl start jenkins
java -version
javac -version
systemctl status jenkins
```

## 5. Install and Configure Maven

Refer: https://maven.apache.org/install.html
Copy the download link from https://maven.apache.org/download.cgi

```bash
sudo su  & cd ~
cd /opt
wget https://dlcdn.apache.org/maven/maven-3/3.9.3/binaries/apache-maven-3.9.3-bin.tar.gz
tar -xzvf apache-maven-3.9.3-bin.tar.gz
mv apache-maven-3.9.3 maven
ll
cd maven
cd bin/
./mvn -v  
cd ~
ll -a      #It will show the hidden files also
vim .bash_profile
find / -name java-11*
#enter below lines below the 2nd fi
M2_HOME=/opt/maven
M2=/opt/maven/bin
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-11.0.19.0.7-1.amzn2.0.1.x86_64
PATH=$PATH:$HOME/bin:$JAVA_HOME:$M2_HOME:$M2
echo $PATH
source .bash_profile
echo $PATH
mvn -v
```

## 6. Configure Jenkins User Interface
```bash
Java_Home: /usr/lib/jvm/java-11-openjdk-11.0.19.0.7-1.amzn2.0.1.x86_64
```
```bash
MAVEN_HOME:/opt/maven     //You need to add this at Jenkins Job under Maven Installations
```
```bash
yum install git -y
```

## 7. Create a Test Job

## 8. Start Ansible Server on AWS

## 9. Install and COnfigure Ansible
```bash
sudo nano /etc/hostname
#Reboot
init 6
sudo -i
useradd ansadmin
passwd ansadmin
visudo
cd /etc/ssh
nano sshd_config
#PasswordAuthentication yes
service sshd reload
Sudo su - ansadmin
ssh-keygen
#Install Ansible
sudo su
amazon-linux-extras install ansible2
ansible --version
```

## 10. Intregrate Ansible with Jenkins

## 11. Install Docker in Ansible Server
```bash
cd /opt
sudo mkdir docker
sudo chown ansadmin:ansadmin docker
cd /opt/docker
sudo yum install docker
sudo usermod -aG docker ansadmin
id ansadmin
sudo service docker start
sudo systemctl start docker
#Reboot
init 6
Start docker
sudo su – ansadmin
cd /opt/docker/
```

## 12. Create Project Dockerfile in Ansible Server
```bash
vi Dockerfile
```

## 13. Create Ansible Playbook for Docker Tasks
See Manifests in Repo
```bash
sudo vi /etc/ansible/hosts
ssh-copy-id IPAddress
ansible-playbook app-ci.yml -–check
ansible-playbook app-ci.yml

```

## 14. Create the CI Job

## 15. Start the EKS Server on AWS

## 16. Provision EKS cluster with eksctl
* Install Kubectl
Refer: https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html
```bash
curl -O https://s3.us-west-2.amazonaws.com/amazon-eks/1.27.1/2023-04-19/bin/linux/amd64/kubectl
chmod +x ./kubectl 
mv kubectl /bin
```

* Install eksctl
Refer: https://github.com/eksctl-io/eksctl/blob/main/README.md#installation
```bash
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
cd /tmp
sudo mv /tmp/eksctl /bin
eksctl version
```

* Create and Attach roles to the EKS Server
```bash
AmazonEC2FullAccess
AWSCloudFormationFullAccess
IAMFullAccess
AdministratorAccess
```

* Provision EKS Cluster
```bash
eksctl create cluster --name myprojectapp-cluster \
--region us-east-2 \
--node-type t2.small
```

* Install AWS CLI
Refer: https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html
```bash
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

* Connect to your cluster
```bash
aws eks update-kubeconfig --region us-east-2 --name myprojectapp-cluster
kubectl get nodes
```

## 17. Integrate EKS Server with Ansible
```bash
vi /etc/ssh/sshd_config
passwd root
service sshd reload
```

* On the Ansible Server
```bash
vi /etc/ansible/hosts
ssh-copy-id root@EKS-Server-IP
```

* On the Ansible Server, create a playbook for the deployment
```bash
vi kube_deploy.yml
ansible-playbook kube_deploy.yml --check
ansible-playbook kube_deploy.yml
```

* On the EKS Server, create manifest files for the deployment
```bash
vi myapp-deployment.yml
vi myapp-service.yml
```

## 18. Create a CD Job on Jenkins

## 19. Intergrate the CI and the CD Jobs on Jenkins

## 20. Deploy/Test the Application

## Happy you got to this point. Hope it worked!

# Congratulations!

# Merciboi Systems Solutions
# info@merciboi.com
