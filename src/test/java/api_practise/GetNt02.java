package api_practise;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import pojos.JsonPlaceHolderNewPojo;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetNt02 extends JsonPlaceHolderBaseUrl {
   /*
         When
             I send a GET Request to the URL https://jsonplaceholder.typicode.com/comments
         Then
             HTTP Status Code should be 200
        And
             Search all ids that are less than 30
             the number of ids less than 30 should be 29
     */

    @Test
    public void getNt02(){

        spec.pathParam("first","comments");

        Response response=given().spec(spec).when().get("/{first}");
        //response.prettyPrint();
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);

        JsonPath json= response.jsonPath();
        List<Integer> ids=json.getList("findAll{it.id<30}.id");

        System.out.println(ids);
        Assert.assertTrue(ids.size()==29,"The data does not match");


    }

    @Test
    public void test02(){
        spec.pathParam("first","comments");

        Response response=given().spec(spec).when().get("/{first}");

        JsonPlaceHolderNewPojo [] comments=response.as(JsonPlaceHolderNewPojo[].class);

        int count=0;
        for (int i=0;i<comments.length;i++){
            if(comments[i].getId()<30){
                count++;
            }
            System.out.println(i+"id"+comments[i].getId());
        }

        Assert.assertTrue(count==29);


    }


}
