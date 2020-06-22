# Deployment Docs
For deployment simply grab the latest jar artifact build and run it.  
Per default a example types list is loaded, and a default database is constructed.
Following parameters can be modified before running the server:
 - Server Port can be changed by setting the environment variable `SERVER_PORT`. The default value is 8080
 - The types can be configured by setting the environment variable `TYPES` to a comma limited list of the types. Per default a few types are loaded.
 - The database construction can be disabled by specifying the environment variable ``
 - The database initialization can be disabled by setting the environment variable 'SPRING_PROFILES_ACTIVE' to 'noinit'
 