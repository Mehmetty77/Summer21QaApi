package get_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DummyDTGet05 extends JsonPlaceHolderBaseUrl {
/*
        When
        I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos
        And Accept type is “application/json”
        Then
        HTTP Status Code should be 200
        And Response format should be JSON
        And there should be 200 todos
        And "numquam repellendus a magnam" should be one of the todos
        And 194, 192, and 186 should be among the userIds
     */


    @Test
    public void test05(){
        spec.pathParam("first","todos");

        Response response=given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("id",hasSize(200),"title",hasItem("numquam repellendus a magnam") , "id", hasItems(194,192,186));




    }




}
