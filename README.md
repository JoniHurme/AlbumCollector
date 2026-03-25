# ALBUM COLLECTOR - Work in Progress

Album Collector is a software that the user can use to track their music collection.
Records can be added, modified and removed from the collection, and in the future additional details can be added. Such as limited editions, first editions, signatures and so on.

Tech solutions:
- Frontend is React (Vite)
- - Frontend can be found here: https://github.com/JoniHurme/AlbumCollectorFrontend
- Backend is Java Spring
- Database is PostgreSQL


For now there is no simple way to showcase the application. You will need some know how so you can run it locally.
- Clone the repository to your Java IDE
- Clone the frontend to your frontend IDE
- Create a PostgreSQL server and make sure the server details match the application.properties file
- Start the PostgreSQL server
- Start the frontend and the backend of the application in your IDE
- Navigate to localhost:5173 in your browser

## Other stuff that needs doing:
- Refactor components and elements
- Sort and filter records
- Add more detailed information for records.
- Record wishlist that can be shared
- Package the whole thing into a docker container

If you want to know what I will work on next, see the issues tab.

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
   