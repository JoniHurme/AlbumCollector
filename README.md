# ALBUM COLLECTOR - Work in Progress

Album Collector is a software that the user can use to track their music collection.
Records can be added, modified and removed from the collection and in the future additional details can be added. Such as limited editions, first editions, signatures and so on.

Tech solutions:
- Frontend is Vaadin
- Backend is Java Spring
- Database is PostgreSQL

For now, the project has no ready to go build and if you want to try it you will need to configure the database yourself. Here is how to get started:
1. Clone the project
2. Start a postgreSQL server. It can be a docker container on your localhost.
3. Make sure the postgreSQL database has the following columns and types:
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
   
4. Inside the `application.properties` file have the following fields populated with the details of your postgreSQL server:
```
spring.datasource.url=jdbc:postgresql://YOURSERVER:YOURPORT/NAMEOFYOURDATABASE
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=YOURUSERNAME
spring.datasource.password=YOURPASSWORD
```
You can also create `application-user1.properties` file and put the details there. Just make sure the `application.properties` field `spring.profiles.active` is set to the user1.

## What features are coming
- Add many records at once
- Sort and filter records
- Add more detailed information for records.
- Record wishlist that can be shared
- Package the whole thing into a docker container

## Other stuff that needs doing:
- Clean up the Main view
- Working navigation
- Refactor components and elements

If you want to know what I will work on next see the issues tab.