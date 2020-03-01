# CityRoad Application
A Java program to determine if two cities are connected. Two cities are considered connected if there’s a series of roads that can be traveled from one city to another. List of roads is available in a file. File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.
## Prerequisite
To use this project, you are going to need:
* Java 1.8
* Maven
* Any Java IDE

## How To Run Tests Locally
1. Run CityRoad
2. On a browser, Go to http://localhost:8080/swagger-ui.html
3. Click on city-road-controller
4. Click on /connected
5. Click on try it out
6. Fill in the value for the query params origin and destination.
7. Click Execute, then check Response body text box should return "YES" or "NO".