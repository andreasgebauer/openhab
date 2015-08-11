#!/bin/bash

mvn clean package

cp /home/andreas/git/ag-openhab/bundles/ui/org.openhab.ui.webapp.angular/target/org.openhab.ui.webapp.angular-1.8.0-SNAPSHOT.jar /run/user/1000/gvfs/sftp\:host\=raspberrypi,user\=pi/home/pi/
ssh pi@raspberrypi "sudo mv org.openhab.ui.webapp.angular-1.8.0-SNAPSHOT.jar /usr/share/openhab/addons/"
