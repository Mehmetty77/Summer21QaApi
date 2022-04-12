package get_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class DummyDTGet06 extends JsonPlaceHolderBaseUrl {
/*
        When
        I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos
        And Accept type is “application/json”
        Then
        HTTP Status Code should be 200
        And Response format should be JSON

        And make sure total number of users 200
     */

    @Test
    public  void test06(){

        spec.pathParam("first","todos");

        Response response=given().spec(spec).accept(ContentType.JSON).when().get("/{first}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("id", hasSize(200));




    }



}
