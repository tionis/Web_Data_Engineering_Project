# Implementation Specifics
The base project structure consists of a few packages
The application package implements the projects custom exceptions, the data initialization package and the service package.
The persistence package implements the entities package and the data repositories for database interaction.
The web package implements the top layer of the application. Here the dtos for the api and view are implemented, while web requests are handled by the relevant controllers.