#!/bin/bash
mvn clean package

if [ -f $CATALINA_BASE/webapps/hitstpa.war ]; then
echo "Removing HITSTPA.war" 
sudo rm $CATALINA_BASE/webapps/hitstpa.war
fi

if [ -d $CATALINA_BASE/webapps/hitstpa ]; then
echo "Removing unpacked HITSTPA Webapp"
sudo rm -rf $CATALINA_BASE/webapps/hitstpa
fi

sudo cp target/HITSTPA-0.1.0.war $CATALINA_BASE/webapps/HITSTPA.war
sudo service tomcat7 restart
