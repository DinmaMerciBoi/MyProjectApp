//Jenkins pipeline script
//Groovy script  
node 
{
def mavenHome = tool name: 'maven3.6.3'
stage('1. CodeClone') 
{
git credentialsId: 'Github_credentials', url: 'https://github.com/myLandmakTechnology/maven-web-app.git'
}
stage('2. Build')
{
sh "${mavenHome}/bin/mvn package -f boa.xml"
}

stage('3. CodeQuality') 
{
sh "${mavenHome}/bin/mvn sonar:sonar"
}
stage('4.UploadNexus') 
{
sh "${mavenHome}/bin/mvn deploy"
}
stage('5. Approval') 
{
echo "Approved. Ready for deployment"
}
stage('6. DeployTomcat') 
{

deploy adapters: [tomcat9(credentialsId: 'Tomcat-Credentials', path: '', url: 'http://54.163.156.108:8888/')], contextPath: null, war: 'target/*war'
}
stage('7. Notification') 
{
emailextrecipients([developers()])
}
}

stage('Deploy-prod'){
 	steps{
 		timeout(time:5, unit:'DAYS'){
 			input message: 'Approval for production'
 		}
 	}
 	steps{
 		deploy adapters: [tomcat9(credentialsId: 'Tomcat-Credentials', path: '', url: 'http://54.163.156.108:8888/')], contextPath: null, war: 'target/*war'

 	}
 }
}


node {
	stage('clone'){
		steps{
			timeout(time:5, unit:'DAYS'){
 			input message: 'Approval for clonning'
 		}
		}
		steps{
			git credentialsId: 'GitHUB-CREDENTIALS', url: 'https://github.com/Landmark-Technologies/maven-web-application'
		}
	}
}
