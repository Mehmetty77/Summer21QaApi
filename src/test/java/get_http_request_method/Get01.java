package get_http_request_method;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

        /*
            We will use Gherkin Language.
            In Gherkin Language we use some keywords: Given, When, Then, And
            Given : It declares pre-requisites
            When : It is used to declare actions
            Then : It is for outputs
            And : It is used for multiple Given, When, Then
        */

    /*
        Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User send a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    @Test
    public void get01(){

        //1)Set the url
        String url = "https://restful-booker.herokuapp.com/booking/3";

        //2)Set the expected data

        //3)Type automation script to send GET Request and to get response
        Response response = given().when().get(url);
        response.prettyPrint();

        //4)Do assertion
         /*
             If you have multiple errors on Assertion, execution will be stopped in the first error.Next codes will not be executed.
             You will not get any error messages about the second, third, etc. errors.
             That kind of assertions are called "Hard Assertion"
             Do we have Soft Assertion? Yes.
             In Soft Assertion(Verification), all codes will be executed and you will get report for all assertions
         */
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //How to print status code, status line, content type, etc. on the console
        System.out.println("Status Code is " + response.getStatusCode());
        System.out.println("Status Line is " + response.getStatusLine());
        System.out.println("Content Type is " + response.getContentType());
        System.out.println("Time is " + response.getTime());
        System.out.println("Headers are \n" + response.getHeaders());
        System.out.println("Via is " + response.getHeader("Via"));
    }
}