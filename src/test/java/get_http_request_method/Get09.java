package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get09 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
	 		I send GET Request to the url
	 	Then
	 		Response body should be like that;
	 		{
			    "firstname": "Eric",
			    "lastname": "Smith",
			    "totalprice": 555,
			    "depositpaid": false,
			    "bookingdates": {
			        "checkin": "2016-09-09",
			        "checkout": "2017-09-21"
			     }
			     "additionalneeds": "Breakfast"
			}
     */
    @Test
    public void get09(){

        //1.Step: Set the url
        spec.pathParams("first", "booking", "second", 1);

        //2.Step: Set the expected data
        Map<String, String> expectedBookingdates = new HashMap<>();
        expectedBookingdates.put("checkin", "2020-08-18");
        expectedBookingdates.put("checkout", "2021-05-03");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jim");
        expectedData.put("lastname", "Ericsson");
        expectedData.put("totalprice", 747);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", expectedBookingdates);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println(expectedData);

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step: Do assertion
//        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
//        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
//        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
//        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
//        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        assertEquals(expectedBookingdates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingdates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));

    }

}