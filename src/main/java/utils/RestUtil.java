package utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtil {

    // Only developing utilities for GET requests.
    // Typically, in our automation framework, we handle GET, POST, PUT, and DELETE with their respective overloaded methods.

    public static Response getMethod(String host, String resource){
        RestAssured.baseURI = host;
        Response rs = given().relaxedHTTPSValidation()
                .when().get(resource)
                .then().assertThat().statusCode(200)
                .and().log().all()
                .and().extract().response();
        return rs;
    }

    /**
     * This method does a http call
     * no Resource required. Doesn't log the response
     * @param host The String Host/
     * @return Response in RestAssured response Format
     **/
    public static Response getMethod(String host){
        RestAssured.baseURI = host;
        Response rs = given().relaxedHTTPSValidation()
                .when().get()
                .then().assertThat().statusCode(200)
                .and().extract().response();
        return rs;
    }

    public static Response getMethod(String host, String resource, Map<String, Object> queryParams){
        RestAssured.baseURI = host;
        Response rs = given().relaxedHTTPSValidation().queryParams(queryParams)
                .when().get(resource)
                .then().assertThat().statusCode(200)
                .and().log().all()
                .and().extract().response();
        return rs;
    }

    public static String getJsonParameterByPath(String JsonString, String path) {
        JsonPath js = new JsonPath(JsonString);
        return (js.get(path)).toString();
    }
}
