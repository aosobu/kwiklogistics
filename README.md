# Kwik Logistics
is a java based application that manages the delivery of medications using drones

## Pre-requisites
- Docker
- Docker compose
- Java 8 - check java version
- Maven 3 - check maven version

## How to run
Build the jar using command,

    mvn clean install -DskipTests

Start the application with the command,

    docker-compose up

## Assumptions
- The minimum capacity of a drone is pegged at 50 grrams
- The default state of registered drone is IDLE
 