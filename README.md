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

    docker pull aosobudev20222/kwiklogistics.jar

Start the application with the command

    docker run -p 9092:9092 aosobudev20222/kwiklogistics.jar

Download postman collection at url

    https://documenter.getpostman.com/view/20194362/2s8YekTFcj


You can access use the collection to access the application

Note:
When testing the endpoint
localhost:9092/kwiklogisics/register/medication

Kindly add image before trying it out

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

##Postman Collection

The collection can be accessed at https://documenter.getpostman.com/view/20194362/2s8YekTFcj


##Build docker image locally
After importing the project into IDE of your choice.

Go to your terminal and navigate to the directory of the application.

Execute the commannds listed below sequentially.

mvn clean install
(generates jar file to be used by docker build command)

docker build -t kwiklogistics.jar .
(generates docker image)

docker image ls
(You should have a jar with name kwiklogistics.jar on the listing with CREATED column entry in minutes)

docker run -p 9092:9092 kwiklogistics.jar
(you should see the spring service application start up in the terminal)





 