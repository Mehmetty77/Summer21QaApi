package post_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingPostResponseBodyPojo;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostGetWithObjectMapperAndPojo01 extends HerOkuAppBaseUrl {
/*
        Given
	        https://restful-booker.herokuapp.com/booking
​
	        {
                "firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
                 },
                 "additianal needs":"Breakfast"
              }
        When
	 		I send POST Request to the Url
	   And
	        I send GET Request to the Url
	 	Then
	 		Status code is 200
	 	And
	 	    GET response body should be like {
											        "firstname": "Selim",
											        "lastname": "Ak",
											        "totalprice": 11111,
											        "depositpaid": true,
											        "bookingdates": {
											            "checkin": "2020-09-09",
											            "checkout": "2020-09-21"
											        },
                 "additianal needs":"Breakfast"
											    }
     */

    @Test
    public void postGetWithObjectMapperAndPojo01(){
        spec.pathParam("first","booking");

        BookingDatesPojo bookingDates=new BookingDatesPojo("2020-09-09","2020-09-21");

        BookingPojo requestBody=new BookingPojo("Selim","Ak",11111,true,bookingDates,"Breakfast");


        Response response =given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        BookingPostResponseBodyPojo postResponseBodyInPojo=JsonUtil.convertJsonToJava(response.asString(), BookingPostResponseBodyPojo.class);
        System.out.println(postResponseBodyInPojo);

       Integer bookingId=postResponseBodyInPojo.getBookingid();




    }



}
