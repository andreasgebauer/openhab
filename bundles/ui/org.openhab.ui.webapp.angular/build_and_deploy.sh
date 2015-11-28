#!/bin/bash

set -e

mvn clean package

scp /home/andreas/git/ag-openhab/bundles/ui/org.openhab.ui.webapp.angular/target/org.openhab.ui.webapp.angular-1.8.0-SNAPSHOT.jar pi@raspberrypi:/home/pi/
ssh pi@raspberrypi "sudo mv org.openhab.ui.webapp.angular-1.8.0-SNAPSHOT.jar /usr/share/openhab/addons/"
echo "Successfully copied to raspi"
