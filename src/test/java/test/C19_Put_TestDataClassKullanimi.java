package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.TestDataJsonPlace;


import static io.restassured.RestAssured.given;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
    request yolladigimizda donen response’in
    status kodunun 200, content type’inin “application/json; charset=utf-8”,
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body
        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
    Expected Data
        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
  */
    @Test
    public void put01(){
        //1- url hazırla reqBody hazırla(3p de reqBody olmak zorunda)
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlace testDataJsonplace=new TestDataJsonPlace();

        JSONObject reqBody=testDataJsonplace.requestBodyOlusturJSON();

        //2- expected data hazırla
        JSONObject expData=testDataJsonplace.requestBodyOlusturJSON();

        //3- response kaydet
        Response response =given()
                    .spec(specJsonPlace)
                    .contentType(ContentType.JSON)//put request old için sontentType ve body yazıyoruz
                .when()
                .body(reqBody.toString())//toStringe donusmek zorunda cunku jsonObject
                .put("/{pp1}/{pp2}");
        response.prettyPrint();

        //4- assertion

        JsonPath respJP=response.jsonPath();

        Assert.assertEquals(testDataJsonplace.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(testDataJsonplace.contentType,response.getContentType());
        Assert.assertEquals(testDataJsonplace.connectionHeaderDegeri,response.getHeader("Connection"));

        Assert.assertEquals(expData.get("userId"),respJP.get("userId"));
        Assert.assertEquals(expData.get("id"),respJP.get("id"));
        Assert.assertEquals(expData.get("title"),respJP.get("title"));
        Assert.assertEquals(expData.get("body"),respJP.get("body"));

    }

}
