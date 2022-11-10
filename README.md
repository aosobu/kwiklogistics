# Kwik Logistics
is a java based application that manages the delivery of medications using drones

## Pre-requisites
- Docker
- Docker compose
- Java 8
- Maven 3.8.5

## Database
H2 in-memory database is used for this project.
Feel free to modify database properties in application.properties file
to suit your preference.
NoSQL databases will not work by default.

## How to run via docker image
Download image from dockerhub via command

    docker pull aosobudev20222/kwik-logistics:latest

Start the application with the command

    docker start kwik-logistics

Download postman collection at url

    https://documenter.getpostman.com/view/20194362/2s8YekTFcj


## How to run locally
Pull code with git via 

    https://bitbucket.airtel.africa/bitbucket/users/13401547/repos/kwik-logistics/browse

import into any IDE of your choice.
Check the prerequisites section for information on java and maven versions


## Assumptions
- The minimum capacity of a drone is 50 grams
- The default state of newly registered drone is IDLE
- The default state of a newly registered medication order is IDLE
- The minimum battery capacity of a drone is 1 percent

##Testing
 Drone with serial number, XVH5547674734UTPO, is loaded at start up.
 (use same serial number above to test for loaded medications for a given drone)

To simulate loading a drone with low battery capacity use serial number XVH5547674736UTPO

The remaining drones are defaulted to idle

To load a drone hit the url localhost:9092/kwiklogisics/drone/load with the 
serial numbers XVH5547674738UTPO

To load a medication hit the url localhost:9092/kwiklogisics/medication/register with payload as shown in postman collection
If you encounter an error after first attempt, ensure the name of the image has been changed before reattempting.

You can use any of the above serial numbers to check the battery status and available drones.

The link to the postman collection will be shared shortly

##Postman Collection

The collection can be accessed at https://documenter.getpostman.com/view/20194362/2s8YekTFcj



 