# ALBUM COLLECTOR - Work in Progress

Album Collector is a software that the user can use to track their music collection.
Records can be added, modified and removed from the collection and in the future additional details can be added. Such as limited editions, first editions, signatures and so on.

Tech solutions:
- Frontend is Vaadin
- Backend is Java Spring
- Database is PostgreSQL

For now, the project has no ready to go build and if you want to try it you will need to configure the database yourself. Here is how to get started:

You will need the JDK. This can be done automatically in your IDE or you can install it from Oracle's website.
A tool like docker is useful for the PostgreSQL server.

1. Clone the project
2. Start a PostgreSQL server. It can be a docker container on your localhost.
3. Copy `application-example.properties` and change the database details.
4. After the database details are set you can start the application in your IDE and go to `localhost:8080` in your browser.
```
spring.datasource.url=jdbc:postgresql://YOURSERVER:YOURPORT/NAMEOFYOURDATABASE
spring.datasource.username=YOURUSERNAME
spring.datasource.password=YOURPASSWORD
```
You can also create `application-user1.properties` file and put the details there. Just make sure the `application.properties` field `spring.profiles.active` is set to the user1.

## How to use

In the home view you see the amount of records you have and how many favourites you have. Below is `add a record` button that you can use to add records.
In the collection view you can see your records and click on them to see more details or remove a record.


## What features are coming
- Add many records at once
- Sort and filter records
- Add more detailed information for records.
- Record wishlist that can be shared
- Package the whole thing into a docker container

## Other stuff that needs doing:
- Refactor components and elements
- Migrating the ui from Vaadin to React
  - Helps to separate the frontend from the backend.

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
   