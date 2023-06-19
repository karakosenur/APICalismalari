package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C11_Get_ExpectedDataOlusturma {
    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine
    bir GET request yolladigimizda donen response body’sinin
    asagida verilen ile ayni oldugunu test ediniz
   Response body :
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */
    @Test
    public void get01(){
        //1- endpoint hazırla
        String url="https://jsonplaceholder.typicode.com/posts/22";

        //2- expected data hazirla
        JSONObject expectedData=new JSONObject();

        expectedData.put("userId",3);
        expectedData.put("id",22);
        expectedData.put("title","dolor sint quo a velit explicabo quia nam");
        expectedData.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        System.out.println("expected data : "+ expectedData);

        //3- responsı kaydet
        Response response=given().when().get(url);

        response.prettyPrint();//response classının sağladığı bir methoddur
        //response.prettyPeek();//prettyPrintten farklı olarak respone ile ilgili tum degerleri dondurur

        //4- assertion

        JsonPath respJP =response.jsonPath();

        assertEquals(expectedData.get("userId"),respJP.get("userId"));
        assertEquals(expectedData.get("id"),respJP.get("id"));
        assertEquals(expectedData.get("title"),respJP.get("title"));
        assertEquals(expectedData.get("body"),respJP.get("body"));
    }
}
