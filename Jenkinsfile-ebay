node {
  def mavenHome = tool name: 'maven3.8.2'
  stage('1-Clone') {
    git credentialsId: 'GitHUB-CREDENTIALS', url: 'https://github.com/Landmark-Technologies/maven-web-application' 
  }
  stage('2-mavenBuild') {
     sh "${mavenHome}/bin/mvn clean package"
  }
  /*
  stage('3-CodeQuality') {
     sh "${mavenHome}/bin/mvn sonar:sonar"
  }
  stage('4-UploadArticats') {
    sh "${mavenHome}/bin/mvn deploy"
  }
 
  stage('5-Deploy-UAT') {
    deploy adapters: [tomcat9(credentialsId: 'deploy', path: '', url: 'http://54.204.83.221:8000/')], contextPath: null, war: 'target/*.war'
  }
  stage('6-EmailN') {
    emailext body: '''Hello Everyone,

Build from Ebay pipeline project.

Landmark Tecxhnologies''', subject: 'Build status', to: 'developers'
  }
  stage('Approval'){
    timeout(time:8, unit: 'HOURS' ) {
      input message: 'Please verify and approve'
    }

  }
  stage('prod-Deploy'){
     deploy adapters: [tomcat9(credentialsId: 'deploy', path: '', url: 'http://54.204.83.221:8000/')], contextPath: null, war: 'target/*.war'
  }
  */
  
  /*
  */

}
