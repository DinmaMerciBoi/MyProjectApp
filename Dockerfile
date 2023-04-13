FROM tomcat:8.0.20-jre8
COPY target/*.war /usr/local/tomcat/webapps/tesla33.war
