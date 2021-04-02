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
1. Run `mvn clean iinstall`
2. Start application with IDE or use `mvn spring-boot:run`

Application will be available on `http://localhost:8080`