package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.TestDataJsonPlace;


import static io.restassured.RestAssured.given;

public class C18_Get_TestDataClassKullanimi extends JsonPlaceHolderBaseUrl {
    /*
  https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
  request yolladigimizda donen response’in status kodunun 200 ve
  response body’sinin asagida verilen ile ayni oldugunu test ediniz
   Response body = Expected Body
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */

    @Test
    public void get01(){
        //1- url hazrla
        specJsonPlace.pathParams("pp1","posts","pp2",22);

        //2- expected data hazrla

        TestDataJsonPlace testDataJsonplace=new TestDataJsonPlace();

        JSONObject expData=testDataJsonplace.expectedBodyOlusturJSON();

        //3- response kaydet
        Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        //4- assertion

        JsonPath respJP=response.jsonPath();
        Assert.assertEquals(testDataJsonplace.basariliStatusCode,response.getStatusCode());

        Assert.assertEquals(expData.get("userId"),respJP.get("userId"));
        Assert.assertEquals(expData.get("id"),respJP.get("id"));
        Assert.assertEquals(expData.get("title"),respJP.get("title"));
        Assert.assertEquals(expData.get("body"),respJP.get("body"));

    }
}
