### Preconditions
 - java
 - maven

### Running Tests

- API Test
```
mvn clean test -Dcctest=src/test/resources/TestNGXMLs/API.xml
```

- UI Test
```
mvn clean test -Dcctest=src/test/resources/TestNGXMLs/UI.xml
```
- Both
```
mvn clean test -Dcctest=src/test/resources/TestNGXMLs/Regression.xml
```