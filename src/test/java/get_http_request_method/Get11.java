package get_http_request_method;

import base_urls.GoRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestApiBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 20
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 31
        And
            We have at least one "active" status
        And
            "Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra" are among the users
        And
            The female users are more than male users
     */
    @Test
    public void get11(){

        //1.Step:Set the URL
        spec.pathParam("first", "users");

        //2.Step: Set the expected data

        //3.Step: Send the GET Request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertion
        //1.Way:
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(20),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(20),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra"));

        //2.Way: Use JsonPath to assert
        JsonPath json = response.jsonPath();

        assertEquals(20, json.getInt("meta.pagination.limit"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1", json.getString("meta.pagination.links.current"));
        assertEquals(20, json.getList("data.id").size());
        assertTrue(json.getList("data.status").contains("active"));

        List<String> expectedNamesList = Arrays.asList("Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra");
        assertTrue(json.getList("data.name").containsAll(expectedNamesList));

        //The number of females are more than the number of males
        //1. Way: Use for loop
        List<String> genderList = json.getList("data.gender");
        System.out.println(genderList);

        int femaleCounter = 0;
        for(String w : genderList){
            if(w.equals("female")){
                femaleCounter++;
            }
        }
        assertTrue(femaleCounter>genderList.size()-femaleCounter);

        //2.Way: Use groovy
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        System.out.println(femaleList.size());

        List<String> maleList = json.getList("data.findAll{it.gender='male'}.gender");
        System.out.println(maleList.size());

        assertTrue(femaleList.size() >= (maleList.size()));

        //The number of active status is more than 5
        //1.Way: Used loop
        List<String> statusList = json.getList("data.status");
        System.out.println(statusList);

        int statusCounter = 0;
        for(String w : statusList){
            if(w.equals("active")){
                statusCounter++;
            }
        }
        assertTrue(statusCounter>5);

        //2.Way: Use groovy
        List<String> listOfActiveStatus = json.getList("data.findAll{it.status='active'}.status");
        System.out.println(listOfActiveStatus);

        assertTrue(listOfActiveStatus.size()>5);
    }

}