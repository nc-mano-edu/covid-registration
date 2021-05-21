#!/bin/bash

# build backend part
echo "Start backend service build..."
mvn clean package -DskipTests

sleep 3

# build frontend part
echo "Start frontend service build..."
cd covid-registration-client
ng build --prod