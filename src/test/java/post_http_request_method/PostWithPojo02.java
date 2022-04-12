package post_http_request_method;


import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingDatesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWithPojo02 extends HerOkuAppBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking
​
           {
               "firstname": "Suleyman",
               "lastname": "Alptekin",
               "totalprice": 999,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2021-09-21",
                   "checkout": "2021-12-21"
                },
                "additionalneeds": "Breakfast with white tea, Dragon juice"
            }
       When
            I send POST Request to the URL
            I send GET Request to the URL to get the newly created data
        Then
            Status code is 200
        And
            Response body is like {
                                       "firstname": "Suleyman",
                                       "lastname": "Alptekin",
                                       "totalprice": 999,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2021-09-21",
                                           "checkout": "2021-12-21"
                                       },
                                       "additionalneeds": "Breakfast with white tea, Dragon juice"
                                  }
    */
    @Test
    public void postWithPojo02() {

        //1.Step: Set the url
        spec.pathParam("first", "booking");

        //2.Step: Set the post request body
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo postRequestBody = new BookingPojo("Suleyman", "Alptekin", 999, true, bookingdates, "Breakfast with white tea, Dragon juice");

        //3.Step: Send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(postRequestBody).when().post("/{first}");
        response.prettyPrint();

        //Note: After creating new data in DB, you will need "bookingid" to be able to use GET Method
        //      So you need to get "bookingid" from POST Response body
        JsonPath json = response.jsonPath();
        Integer bookingid = json.getInt("bookingid");

        //Set the url for GET Method
        spec.pathParams("first", "booking", "second", bookingid);

        //Send GET Request and get the response
        Response response2 = given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();

        //Convert response2 to Pojo
        BookingPojo actualData = response2.as(BookingPojo.class);

        assertEquals(200, response2.getStatusCode());

        assertEquals(postRequestBody.getFirstname(), actualData.getFirstname());
        assertEquals(postRequestBody.getLastname(), actualData.getLastname());
        assertEquals(postRequestBody.getTotalprice(), actualData.getTotalprice());
        assertEquals(postRequestBody.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(postRequestBody.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(postRequestBody.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
    }
}