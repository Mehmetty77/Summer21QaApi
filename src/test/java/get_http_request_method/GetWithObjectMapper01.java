package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtil;

import java.lang.annotation.Target;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class GetWithObjectMapper01 extends JsonPlaceHolderBaseUrl {
/*
Given
    https://jsonplaceholder.typicode.com/todos/198
When
            I send GET Request to the URL
        Then
            Status code is 200
            And response body is like {
                                        "userId": 10,
                                        "id": 198,
                                        "title": "quis eius est sint explicabo",
                                        "completed": true
                                      }
 */

    @Test
    public  void getWithObjectMapper(){

        spec.pathParams("first","todos","second",198);

        String expectedData="{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";

       HashMap<String ,Object> expectedDataMap= JsonUtil.convertJsonToJava(expectedData, HashMap.class);

        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

       HashMap<String,Object> actualDataMap= JsonUtil.convertJsonToJava(response.asString(),HashMap.class);
/*

 */
       assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));

    }



}
