#swaggerurl: 
http://localhost:8080/swagger-ui/index.html

##Security
username: admin
password: password

##maven
mvn clean package

##local -- localhost:8080/v1/api/employees

##Docker
docker build --tag=employee-service:latest .