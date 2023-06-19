package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C06_Post_ResponseBodyTesti {
    /*  https://jsonplaceholder.typicode.com/posts
         url’ine asagidaki body ile bir POST request gonderdigimizde
        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }
        donen Response’un,
        status code’unun 201,
        ve content type’inin application/json
        ve Response Body'sindeki,
        "title"'in "API" oldugunu
        "userId" degerinin 100'den kucuk oldugunu
        "body" nin "API" kelimesi icerdigini
        test edin.
      */
    @Test
    public void post01(){
        //1- url(endpoint) ve request body hazırla
        String url="https://jsonplaceholder.typicode.com/posts";
        JSONObject reqBody=new JSONObject();
        reqBody.put("title","API");
        reqBody.put("body","API ogrenmek ne guzel");
        reqBody.put("userId",10);

        System.out.println("reqBody : " + reqBody);

        //2- expected data hazırla (verilmişse)
        //3- response'i kaydet
        Response response=given()
                                .contentType(ContentType.JSON)//pre condition isterse body ve post yazılır
                         .when()
                                .body(reqBody.toString())
                                .post(url);


        response.prettyPrint();

        //4- assertion
        response
                .then()//assert then olmadan  gelmez
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("userId", Matchers.lessThan(100))//100den küçük mü test ediyoruz
                .body("title",Matchers.equalTo("API"))
                .body("body",Matchers.containsString("API"));
    }
}
