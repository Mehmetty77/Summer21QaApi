package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;
public class PostDeleteWithPojo01 extends JsonPlaceHolderBaseUrl {


    /*
    Given
        https://jsonplaceholder.typicode.com/todos
        {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false
        }
    When
        I send POST Request to the Url
        I send Delete Request to the Url
    Then
        response body is like { }
 */
    @Test
    public void postDeleteWithPojo01(){

        //1.Step: Set the url
        spec.pathParam("first", "todos");

        //2.Step: Set the post request body
        JsonPlaceHolderPojo postReqBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //3.Step: Send the request and get the response body
        Response response = given().spec(spec).contentType(ContentType.JSON).body(postReqBody).when().post("/{first}");
        response.prettyPrint();

        //Note:Response body has "id", I need "id" value to use "delete()".
        //     So I should get "id" value from response body.
        JsonPath json = response.jsonPath();
        Integer id = json.getInt("id");

        //Set the url for delete() method
        spec.pathParams("first", "todos", "second", id);// https://jsonplaceholder.typicode.com/todos/201

        //Send the delete request and get the response
        Response response2 = given().spec(spec).when().delete("/{first}/{second}");
        response2.prettyPrint();

        //Convert response2 to a Map
        Map<String, Object> response2Map = response2.as(HashMap.class);

        assertTrue(response2Map.size()==0);

        assertTrue(response2Map.isEmpty());
    }
}

