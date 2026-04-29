# ALBUM COLLECTOR

Album Collector is a software that the user can use to track their music collection.
Records can be added and removed from the collection. In the future additional details can be added. Such as limited editions, first editions, signatures and so on.

Tech solutions:
- Frontend is React (Vite)
- - Frontend can be found here: https://github.com/JoniHurme/AlbumCollectorFrontend
- Backend is Java Spring
- Database is PostgreSQL


## How to run the application
Currently, the application is a local only deployment, and you have two choices on running it:
- Run the code from IDE and use it that way OR
- Deploy a Docker container

### Running the code from IDE
The application is a Spring Boot application, so you will need Java and Maven installed on your machine or in your IDE. And of course, PostgreSQL for the database and React for the frontend.

- Clone the repository to your Java IDE
- Clone the frontend to your frontend IDE
- Create a PostgreSQL server and make sure the server details match the application.properties file
- Start the PostgreSQL server
- - Inject a collection and a wishlist into the database. For Docker deployment, it is done automatically, but for IDE deployment, it is not.
- Start the frontend and the backend of the application in your IDE
- - The frontend will be running on localhost:5173
- - The backend will be running on localhost:8080
- - - The backend will be dependent on the PostgreSQL server
- Navigate to localhost:5173 in your browser and enjoy.

### Deploying a Docker container
- Clone the backend and the frontend repositories to your machine
- Change the .example files details to match your environment.
- - Docker-compose-example has a line you need to change depending on where you cloned the frontend to.
- - env.example has database details that you should change.
- Once you have the required files changed and Dockerfiles present, run docker compose up -d in the root directory of the project.
- The application should be running, and you should be able to see it in localhost:8085. If you need to change the port, it is in the docker-compose.yml file.

## How to use the application
- Depending on the way you are using the application, navigate to the address required.
- Add a record by filling in the form fields and clicking Add Record.
- Record row has a button to remove records.
- The wishlist checkbox can be used to add records to a wishlist, and the wishlist can be seen in the wishlist tab.
- Wishlist can be shared with other users by exporting it as a PDF.

## Hopefully upcoming features
- Sort and filter records.
- Add more detailed information for records.
- Add logs.


## Troubleshooting
Adding a record and it is not showing up?
- In docker deployment, it is possible that the backend CORS policy is blocking the request. In that case, you can change the RecordController.java files CrossOrigins() method to allow all origins.
- If it is being sent to the backend, check if the database is running.
- - If it is, then check the database connection details in either the application.properties file or .env file.
- - - Docker will check the .env file,
- - - IDE will check the application.properties file.

The Backend will not start?
- The backend is dependent on the PostgreSQL database. If it is not running, doesn't exist or the connection details are wrong, the backend will not start.