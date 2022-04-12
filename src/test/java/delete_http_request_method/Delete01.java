package delete_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {

        /*
            Given
                https://jsonplaceholder.typicode.com/todos/198
            When
                 I send DELETE Request to the Url
             Then
                 Status code is 200
                 And Response body is { }
         */

    @Test
    public void del01(){

        //1.Step: Set the url
        spec.pathParams("first", "todos", "second", 198);

        //2.Step: Set the expected data
//        Map<String, Object> expectedMap = new HashMap<>();
//        System.out.println(expectedMap);

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().delete("/{first}/{second}");
        response.prettyPrint();

        //GSON
        Map<String, Object> actualMap = response.as(HashMap.class);
        System.out.println(actualMap);

        //4.Step: Do the assertion
        response.then().assertThat().statusCode(200);
//        assertEquals(expectedMap, actualMap);
        assertTrue(actualMap.size()==0);

    }
}