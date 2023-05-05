FROM docker:rc-git

FROM tomcat:8.0.20-jre8
COPY target/tesla.war /usr/local/tomcat/webapps/tesla.war
