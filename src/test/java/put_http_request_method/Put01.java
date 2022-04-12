package put_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class Put01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198

	        {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
            }

        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void put01(){

        //1.Step: Set the URL
        spec.pathParams("first","todos", "second", 198);

        //2.Step: Set the request body
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap =  requestBody.expectedDataSetUpWithAllKeys(21, "Wash the dishes", false);

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do assertion
        //1.Way:
        response.
                then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(requestBodyMap.get("userId")),
                        "title", equalTo(requestBodyMap.get("title")),
                        "completed", equalTo(requestBodyMap.get("completed")));
        //2.Way: Use GSON: De-serialization:Converting Json Data to Java Object
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(requestBodyMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(requestBodyMap.get("title"), actualDataMap.get("title"));
        assertEquals(requestBodyMap.get("completed"), actualDataMap.get("completed"));

        //How to use GSON in Serialization: Java Object ==> Json Data
        Map<String, Integer> ages = new HashMap<>(); // { }
        ages.put("Ali Can", 13);
        ages.put("Veli Han", 15);
        ages.put("Ayse Kan", 21);
        ages.put("Mary Star", 65);
        System.out.println(ages);// { "Ali Can"=13, "Veli Han"=15, "Ayse Kan"=21, "Mary Star"=65 }

        //Convert ages map to json data

        //1.Step: Create Gson Object
        Gson gson = new Gson();
        //2.Step: By using "gson" object select method to convert Java Object to Json Data
        String jsonFromMap = gson.toJson(ages);
        System.out.println(jsonFromMap);

    }

}