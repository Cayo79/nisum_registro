# nisum_registro

Java 8
Spring-Boot (v2.2.1.Build-SNAPSHOT)
Gradle

# Compile and execute program

for compile this project you need Java y Gradle pre-install.
In to directory *nisum_registro* and execute


*Note*:
You have need available port *8080* in the PC where execute this API

# View where available API

URL to request the REST operations and Methods
```bash
GET --header 'Accept: application/json' 'http://localhost:8080/usuario'
    Response an List of Usuario Info (JSON)

GET --header 'Accept: application/json' 'http://localhost:8080/usuario/{id}'
    Response a Usuario Info (JSON) if exists, where id is a Integer
    If id not exists send message error

POST --header 'Accept: application/json' 'http://localhost:8080/usuario/filtro'
    Send in the body a Usuario Info (JSON), at least one field for the search
    Response an List of Usuario Info (JSON)

DELETE --header 'Accept: application/json' 'http://localhost:8080/usuario/{id}'
    Response a message successful if exists and otherwise message error, where id is a Integer

POST --header 'Accept: application/json' 'http://localhost:8080/usuario
    Send in the body a Usuario Info (JSON)
    For Insert id must be null otherwise
    For Update if id exists response succesful message and Person Info (JSON) otherwise message error

# Codigos de Respuestas

200 OK - Successful, possibilities:
    Response List of all Persons
    Response at request Person (include List empty)
    Delete Person Info
201 Created - Response Message Successful and Person Info (JSON) - Create or Update
400 Bad Request - Response Message Error when Person Exists
404 Not Data Found - Response Message Error Person Not Exists
406 Not Acceptable - Response Message Error when hair colour its not valid

# Made by:

Christian Ayo Roca
christian.ayo@gmail.com

For Clone this project in GitHub
URL: https://github.com/Cayo79/nisum_registro.git
