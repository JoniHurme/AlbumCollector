# ALBUM COLLECTOR - Work in Progress

Album Collector is a software that the user can use to track their music collection.
Records can be added, modified and removed from the collection and in the future additional details can be added. Such as limited editions, first editions, signatures and so on.

Tech solutions:
- Frontend is React (Vite)
- Backend is Java Spring
- Database is PostgreSQL

For now, the project has no ready to go build and if you want to try it you will need to configure the database yourself. Here is how to get started:

You will need the JDK and Node.js. This can be done automatically in your IDE or you can install it from official websites.
A tool like docker is useful for the PostgreSQL server.

1. Clone the project
2. Start a PostgreSQL server. It can be a docker container on your localhost.
3. Copy `application-example.properties` and change the database details.
4. Build the project using Maven: `./mvnw clean install` (This will also build the React frontend).
### Option 2: Running with Docker (Recommended)

You can run the entire system (Frontend, Backend, and Database) using one Docker Compose command. This requires Docker and Docker Compose (or Podman with podman-compose) to be installed.

1.  Build and start the containers:
    ```bash
    docker compose up --build
    ```
2.  Open your browser and navigate to `http://localhost`.

The system consists of:
-   **Frontend**: React app served by Nginx on port 80.
-   **Backend**: Spring Boot app on port 8080.
-   **Database**: PostgreSQL on port 5432.

The frontend is configured to proxy all `/api` requests to the backend container automatically.
```
spring.datasource.url=jdbc:postgresql://YOURSERVER:YOURPORT/NAMEOFYOURDATABASE
spring.datasource.username=YOURUSERNAME
spring.datasource.password=YOURPASSWORD
```
You can also create `application-user1.properties` file and put the details there. Just make sure the `application.properties` field `spring.profiles.active` is set to the user1.

## How to use

In the React app you see the collection in a table. You can add new records using the form at the top and delete records using the button on each row.
A star (★) indicates a favourite record.


## Other stuff that needs doing:
- Refactor components and elements
- Sort and filter records
- Add more detailed information for records.
- Record wishlist that can be shared
- Package the whole thing into a docker container

If you want to know what I will work on next see the issues tab.

## Troubleshooting

Make sure the postgreSQL database has the following columns and types:
- id `integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY`
- artist `varchar(25)`
- title `varchar(25)`
- genre `varchar(25)`
- favourite `boolean`
- medium `varchar(8)`
- year `integer`

So you can just run the following with your table name:
   ```
       CREATE TABLE YOURTABLENAME(
       id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
       artist VARCHAR(25),
       title VARCHAR(25),
       genre VARCHAR(25),
       favourite BOOLEAN,
       medium VARCHAR(8),
       year INTEGER
       )
   