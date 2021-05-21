# COVID-Registration project

#### backend (covid-registration)
Backend part of solution

Technology:
 - Java 8
 - Spring Boot
 - PostgreSQL
 - Maven

#### frontend (covid-registration-client)

Technology:
 - Angular 11
 
#### How to run COVID Registration Clinic application with Docker

Run `./build.sh` from the root
This script build an application

After success application build run it using docker compose:
`docker compose up --build -d`

**!IMPORTANT** If `./build.sh` doesn't work, follow the next steps:
1. build backend part with: `mvn clean package -DskipTests`
2. navigate to `covid-registration-client` folder and start build frontend part with: `ng build --prod`

#### How to run only Backend
**Prerequisites:**
- JDK8 should be installed
- PostgreSQL10 should be installed and started

**How to run:**
Just run with IDE or use `mvn spring-boot:run`

Backend will be available on `http://localhost:8080`

#### How to run only Frontend
**Prerequisites:**
- Node.js should be installed
- Angular CLI should be installed

**How to run:**
1. Navigate to `covid-registration-client` folder
2. Run `ng serve --open`, the application will start with Live Reloading mode

Frontend will be available on `http://localhost:4200`

#### How to run both of Frontend and Backend
**Prerequisites:**
- JDK8 should be installed
- PostgreSQL10 should be installed and started
- Node.js should be installed
- Angular CLI should be installed

**How to run:**
1. Run `mvn clean install`
2. Start application with IDE or use `mvn spring-boot:run`

Application will be available on `http://localhost:8080`

----
UI Design Example: http://zabota63.com/