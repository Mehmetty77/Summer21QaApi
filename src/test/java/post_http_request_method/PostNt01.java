package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderNewPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostNt01 extends JsonPlaceHolderBaseUrl {
/*
        Given    https://jsonplaceholder.typicode.com/comments
            {
                "name": "This class has smart people",
                "postId": 80,
                "id": 503,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
            When I send Post Request to the URL
            Then the status code should be 201
            Response should be like
                 {
                "name": "This class has smart people",
                "postId": 80,
                "id": 503,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
         */

    @Test
    public void postNt01(){

        spec.pathParam("first","comments");

        Map<String , Object> expectedDataMap=new HashMap<>();
        expectedDataMap.put("name","This class has smart people");
        expectedDataMap.put("postId",80);
        expectedDataMap.put("id",501);
        expectedDataMap.put("body","This class has smart people");
        expectedDataMap.put("email","techproedstudents@gmail.com");


        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");

        response.then().assertThat().statusCode(201);
        Map<String , Object> actualDataMap=response.as(HashMap.class);

        assertEquals(expectedDataMap,actualDataMap);
        assertEquals (expectedDataMap.get("id"),actualDataMap.get("id"));
        assertEquals(expectedDataMap.get("name"), actualDataMap.get("name"));
        assertEquals(expectedDataMap.get("postId"), actualDataMap.get("postId"));
        assertEquals(expectedDataMap.get("body"), actualDataMap.get("body"));
        assertEquals(expectedDataMap.get("email"), actualDataMap.get("email"));
    }
    /*
        Given    https://jsonplaceholder.typicode.com/comments
            {
                "name": "This class has smart people",
                "postId": 85,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
            When I send Post Request to the URL
            Then the status code should be 201
            Response should be like
                 {
                "name": "This class has smart people",
                "postId": 85,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
         Make sure you use pojo
         to make your post request
         */
    @Test
    public void test02 (){
        spec.pathParam("first","comments");
        JsonPlaceHolderNewPojo comment1 = new  JsonPlaceHolderNewPojo();
        comment1.setName("This class has smart people");
        comment1.setPostId(85);
        comment1.setId(501);
        comment1.setBody("Congratulations Everyone");
        comment1.setEmail("techproedstudents@gmail.com");
        Response response = given().spec(spec).contentType(ContentType.JSON).body(comment1).when().post("/{first}");
        response.then().assertThat().statusCode(201);
        JsonPlaceHolderNewPojo comment12 = response.as( JsonPlaceHolderNewPojo.class);
        System.out.println("Name : "+comment12.getName());
        System.out.println("Id : "+comment12.getId());
        System.out.println("email : "+comment12.getEmail());
        System.out.println("postId : "+comment12.getPostId());
        System.out.println("Body : "+comment12.getBody());
        assertEquals(comment1.getId(),comment12.getId());
        assertEquals(comment1.getName(),comment12.getName());
        assertEquals(comment1.getBody(),comment12.getBody());
        assertEquals(comment1.getPostId(),comment12.getPostId());
        assertEquals(comment1.getEmail(),comment12.getEmail());
    }
}