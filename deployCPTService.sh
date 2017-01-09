#!/bin/bash
mvn clean package

if [ -f $CATALINA_BASE/webapps/CPTService.war ]; then
echo "Removing CPTService.war" 
sudo rm $CATALINA_BASE/webapps/CPTService.war
fi

if [ -d $CATALINA_BASE/webapps/CPTService ]; then
echo "Removing unpacked CPT Webapp"
sudo rm -rf $CATALINA_BASE/webapps/CPTService
fi

sudo cp target/CPTService-0.1.0.war $CATALINA_BASE/webapps/CPTService.war
sudo service tomcat7 restart
