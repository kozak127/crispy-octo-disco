# crispy-octo-disco
Test task for recruitment purposes

### Steps to compile
To compile all the projects in this repository, just type in terminal:
```./gradlew clean build```

### Steps to run
All projects are build in their respective directories. Search under build/libs -> there should be executable jar file. They do not require additional parameters to run.

### problem-1
#### Input
The values ```k, a[0], a[1], ... a[n - 1]``` , one value per line. Empty lines will be ignored. All values have to be 32-bit signed integers. Numbers of values should be minimum 2 and max 10e6.
#### Output
Number of pairs matching the following criteria: ```(a[i], a
                                                 [j]) where i < j and a[i] + a[j] = k```
                                                 
### problem-2
#### Input
A list of expressions in Reverse Polish Notation to evaluate, one expression per line. Max 100k operations per line.
#### Output
Result of evaluated expression.

### problem-3
This is a Spring Boot application that is adding REST interface to problem-2 (and redesigning class structure a little). Please edit application.properties to your taste before running. Command to run this application: ```./gradlew :problem-3:bootRun```
####Example request: 
```curl -i --user username --header 'Content-Type: application/json' --request POST --data '{"expressions": ["7 4 +"]}' http://localhost:8080/api/expression```

####Used technologies:
- Spring Boot
- Spring Actuator
- Spring Cloud
- Spring Security (a little)
- SpringFox with Swagger2
- SonarQube with Jacoco
- Junit5 with AssertJ
- Lombok