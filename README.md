# Note-creator
My project

Project for creating, updating, showing and deleting notes using database.
Tools used in the project:
-Java
-Spring Boot
-Spring MVC
-Hibernate
-Maven
-Docker
-Git
-HTML/CSS
-Thymeleaf
-MySQL

I've used Java 15 in the project, but Java 11 is enough.
MySQL database is run using Docker container.

How to run the project:
1. In the console/terminal type: docker run -p 0.0.0.0:3306:3306 --name notesdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=notesdb -d mysql
2. Open database interface of your own and create a connection. Essential data for the connection: user: root, password: root, database: notesdb
3. After cloning the repository, in your IDE open the folder as a project clicking on the pom.xml file.(Built by Maven)
4. To make sure everything is up to date Build the project using in IntelliJ shortcut Ctrl + F9.
5. Run the Spring boot application in a chosen environment. For example in my IntelliJ IDEA to run the application you can use the shortcut Shift + F10
6. The main page of the project is set on: http://localhost:8080/  (setup in application properties)  To do every possible move in the application I've used Postman to do HTTP requests.
Functions usage:
1. To see all the available notes go to http://localhost:8080/. Click on each of the titles to see the content.
2. To add a note do the Postman request using POST method, go to http://localhost:8080/notes/create and in the request body as raw json file type title and content,
for example: 
Method: POST 
URL: http://localhost:8080/notes/create
{
    "title": "hi",
    "content": "everyone"
}
3. To update the content of a note do the Postman request using PUT method, go to http://localhost:8080/notes/update and in the request body as raw json file type title and content,
for example: 
Method: PUT
URL: http://localhost:8080/notes/update
{
    "title": "hi",
    "content": "everyone"
} 
4. To delete the note do the Postman request using PUT method(in order to save in database - variable deleted informs if it's still available and it's not shown on the website anymore), go to http://localhost:8080/notes/update and in the request body as raw json file type title,
for example: 
Method: PUT
URL: http://localhost:8080/notes/delete
{
    "title": "hi"
} 




