package api_practise;

import base_urls.DummyAPi3BaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetNt01 extends DummyAPi3BaseUrl {

/*
     When
         I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/
     Then
         HTTP Status Code should be 200
     And
         Content Type should be JSON
     And
        Make sure Rhona Davidson earns more than Herrod Chandler
       {
        "id": 7,
        "employee_name": "Herrod Chandler",
        "employee_salary": 137500,
        "employee_age": 59,
        "profile_image": ""
    },
    {
        "id": 8,
        "employee_name": "Rhona Davidson",
        "employee_salary": 327900,
        "employee_age": 55,
        "profile_image": ""
    },
 */

    @Test
    public  void getNt01(){

        spec.pathParams("first","api","second","v1","third","employee","final",7);

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        //response.prettyPrint();

        // response1= RestAssured.get("http://dummy.restapiexample.com/api/v1/employees");
        //response1.prettyPrint();

        //response.then().assertThat().contentType(ContentType.JSON).statusCode(200);

        JsonPath json=response.jsonPath();

        System.out.println("person's salary :"+json.getInt("data.employee_salary"));

        spec.pathParams("first","api","second","v1","third","employee","final",8);

        Response response2=given().spec(spec).when().get("/{first}/{second}/{third}/{final}");

        JsonPath json2= response2.jsonPath();

        Assert.assertTrue("The expected data does not match  actual data",(json.getInt("data.employee_salary"))<(json2.getInt("data.employee_salary")));



    }


}
