# API Documentations

## GET http://localhost:8080/spaceships
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
Include parameters: id
#### Example request
`DELETE http://localhost:8080/spaceships/remove?id=4`
#### Example response
`(nothing)`
## GET http://localhost:8080/locations
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
Include parameters: id
#### Example request
`DELETE http://localhost:8080/locations/remove?id=8`
#### Example response
`(nothing)`