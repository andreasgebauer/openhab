#!/bin/bash

mvn clean package

cp target/*.jar /home/andreas/workspace/homeautomation/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/org.openhab.ui.webapp.angular/WEB-INF/lib