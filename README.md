# LIMITING-SERVICE



This repo contains the implementation of a limiting service that allows throttling of client requests to a spring-boot based REST server.



### Prerequisites

- NodeJS 
- nmp



### HOW TO RUN

- Open a terminal, go to the project root and run the spring-boot server: `mvnw spring-boot:run`
- Open another terminal and install artillery Load Testing Tool: `npm i -g artillery`
- In the terminal where artillery was installed issue commands:  

​		`artillery quick --count 1 --num 10 http://localhost:8080/limit`

​		`artillery quick --count 1 --num 10 http://localhost:8080/no-limit`



### Note

Sample logs can be found under resources folder