package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Mary" and last name is "Ericsson"
     */
    @Test
    public void get05(){

        //1.Step: Set the URL
        spec.
                pathParam("first", "booking").
                queryParams("firstname", "Mary", "lastname", "Ericsson");

        //2.Step: Set the expected data

        //3.Step: Send request get data
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.Step: Do assertion
        response.then().assertThat().statusCode(200);

        assertTrue(response.asString().contains("bookingid"));
    }
}