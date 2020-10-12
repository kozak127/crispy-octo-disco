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
A list of expressions in Polish notation to evaluate, one expression per line. Max 100k operations per line.
#### Output
Result of evaluated expression.

### problem-3
This is a spring-boot application, adding REST interface to problem-2. Please edit application.properties to your taste before running.
Example request: ```curl -i --user username --header 'Content-Type: application/json' --request POST --data '{"expressions": ["7 4 +"]}' http://localhost:8080/api/expression```