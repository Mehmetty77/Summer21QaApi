package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostWithPojo01 extends JsonPlaceHolderBaseUrl {

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
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
    @Test
    public void postWithPojo01(){

        //1.Step: Set the url
        spec.pathParam("first", "todos");

        //2.Step: Set the request body
        JsonPlaceHolderPojo requestBodyPojo = new JsonPlaceHolderPojo(55, "Tidy your room", false);
        System.out.println(requestBodyPojo);

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyPojo).when().post("/{first}");
        response.prettyPrint();

        //4.Step: Do assertion

        //1.Way:
        response.
                then().
                assertThat().
                statusCode(201).
                body("userId", equalTo(requestBodyPojo.getUserId()),
                        "title", equalTo(requestBodyPojo.getTitle()),
                        "completed", equalTo(requestBodyPojo.getCompleted()));

        //2.Way: Use De-serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println(actualData);

        assertEquals(requestBodyPojo.getUserId(), actualData.getUserId());
        assertEquals(requestBodyPojo.getTitle(), actualData.getTitle());
        assertEquals(requestBodyPojo.getCompleted(), actualData.getCompleted());
    }
}