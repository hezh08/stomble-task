# General
This program was made using Java. The REST framework is a Spring Boot application.

The program can be run by: \
 `./mvnw spring-boot:run` \
or alternatively: \
`java -jar task-0.0.1-SNAPSHOT.jar`


# API Documentations

## GET http://localhost:8080/spaceships
Gets details of all spaceships.
#### Example request
`GET http://localhost:8080/spaceships`
#### Example response
`[
    {
        "name": "Avalon",
        "model": "SpaceX",
        "status": "decommissioned",
        "identifier": 4,
        "locationIdentifier": 1
    },
    {
        "name": "Explorer",
        "model": "SpaceX",
        "status": "operational",
        "identifier": 5,
        "locationIdentifier": 2
    }
]`
## GET http://localhost:8080/spaceships/{id}
Gets details of one spaceship. \
Include id of spaceship at end of URL.
#### Example request
`GET http://localhost:8080/spaceships/4`
#### Example response
`[
    {
        "name": "Avalon",
        "model": "SpaceX",
        "status": "decommissioned",
        "identifier": 4,
        "locationIdentifier": 1
    } 
]`
## POST http://localhost:8080/spaceships/add
Add spaceships. \
Include parameters: name, model, city, planet, status
#### Example request
`POST http://localhost:8080/spaceships/add?name=Starship&model=SpaceX&city=Elon&planet=Mars&status=operational`
#### Example response
`{
    "name": "Starship",
    "model": "SpaceX",
    "status": "operational",
    "identifier": 7,
    "locationIdentifier": 2
}`
## PUT http://localhost:8080/spaceships/update-status
Updates status of one spaceship. \
Include parameters: id, status
#### Example request
`PUT http://localhost:8080/spaceships/update-status?id=7&status=maintenance`
#### Example response
`{
    "name": "Explorer2",
    "model": "SpaceX",
    "status": "maintenance",
    "identifier": 7,
    "locationIdentifier": 2
}`
## PUT http://localhost:8080/spaceships/travel
Spaceship travels to a destination. Must be an existing location. \
Include parameters: id, city, planet
#### Example request
`PUT http://localhost:8080/spaceships/travel?id=4&city=Atlanta&planet=Neptune`
#### Example response
`{
    "name": "Avalon",
    "model": "SpaceX",
    "status": "operational",
    "identifier": 4,
    "locationIdentifier": 3
}`
## DELETE http://localhost:8080/spaceships/remove
Removes spaceship. \
Include parameters: id
#### Example request
`DELETE http://localhost:8080/spaceships/remove?id=4`
#### Example response
`(nothing)`
## GET http://localhost:8080/locations
Gets details of all locations.
#### Example request
`GET http://localhost:8080/locations`
#### Example response
`[
    {
        "city": "Sydney",
        "planet": "Earth",
        "spaceportCapacity": 3,
        "identifier": 1
    },
    {
        "city": "Elon",
        "planet": "Mars",
        "spaceportCapacity": 1,
        "identifier": 2
    }
]`
## GET http://localhost:8080/locations/{id}
Gets detail of one location. \
Include id of spaceship at end of URL.
#### Example request
`GET http://localhost:8080/locations/1`
#### Example response
`{
    "city": "Sydney",
    "planet": "Earth",
    "spaceportCapacity": 3,
    "identifier": 1
}`
## POST http://localhost:8080/locations/add
Add a location. \
Include parameters: city, planet, spaceportCapacity
#### Example request
`POST http://localhost:8080/locations/add?city=Red&planet=Saturn&spaceportCapacity=5`
#### Example response
`{
    "city": "Red",
    "planet": "Saturn",
    "spaceportCapacity": 5,
    "identifier": 8
}`
## DELETE http://localhost:8080/locations/remove
Remove location. \
Include parameters: id
#### Example request
`DELETE http://localhost:8080/locations/remove?id=8`
#### Example response
`(nothing)`
