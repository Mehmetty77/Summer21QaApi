package get_method;

import base_urls.DummyAPi3BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DummyDTGet01 extends DummyAPi3BaseUrl {

/*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
             Status Line should be HTTP/1.1 200 OK
         And
             There are 24 employees in total
     */

    @Test
    public void test01(){
        spec.pathParams("first", "api", "second", "v1","third", "employees");
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").body("data.id", hasSize(24));
    }

    /*
             When
                 I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
             Then
                 HTTP Status Code should be 200
             And
                 Content Type should be JSON
             And
                This user exists in the system
                {
                "id": 3,
                "employee_name": "Ashton Cox",
                "employee_salary": 86000,
                "employee_age": 66,
                "profile_image": ""
            },
         */
    @Test
    public void test02(){
        spec.pathParams("first", "api", "second", "v1", "third", "employees");
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("data.id", hasItem(3),
                "data.employee_name", hasItem("Ashton Cox"),"status",equalTo("success"),
                "message",equalTo("Successfully! All records has been fetched."));
    }

    /*
            When
                I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
            Then
                HTTP Status Code should be 200
            And
                Content Type should be JSON
            And
                Status Line should be HTTP/1.1 200 OK
            And
                User can see following employees in the system
                Doris Wilder, Jenette Caldwell and Bradley Greer
        */
    @Test
    public void test03(){
        spec.pathParams("first", "api", "second", "v1", "third", "employees");
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK").body(
                "data.employee_name", hasItems("Doris Wilder","Jenette Caldwell","Bradley Greer")
        );
    }
    @Test
    public void test04(){
        spec.pathParams("first", "api","second", "v1","third","employee","final",5);
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("status", equalTo("success")).body(
                "data.id",equalTo(5)
        ).body("data.employee_name",equalTo("Airi Satou")).body("data.employee_salary",equalTo(162700)).body(
                "data.employee_age",equalTo(33)
        ).body("message",equalTo("Successfully! Record has been fetched."));
    }
    /*
             When
                 I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employee
             Then
                 HTTP Status Code should be 200
             And
                 Content Type should be JSON
             And
                This user exists in the system
                {
                "id": 6,
                "employee_name": "Brielle Williamson",
                "employee_salary": 372000,
                "employee_age": 61,
            },
        "message": "Successfully! Record has been fetched."
            },
         */
    @Test
    public void test05(){
        spec.pathParams("first", "api","second", "v1","third","employee", "final","6");
        Response  response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("data.id",equalTo(6),
                "data.employee_name",equalTo("Brielle Williamson"),
                "data.employee_salary",equalTo(372000),
                "data.employee_age",equalTo(61),
                "message",equalTo("Successfully! Record has been fetched."));
    }


}
