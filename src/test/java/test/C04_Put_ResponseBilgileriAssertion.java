package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_Put_ResponseBilgileriAssertion {
    /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
      */

    @Test
    public void put01(){//put request update işlemi yapar
        //1- url hazırlama(endpoint ) ve request body hazırla
        String url="https://jsonplaceholder.typicode.com/posts/70";


        JSONObject  reqBody=new JSONObject();
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);

        System.out.println(reqBody);

        //2- expected data hazirla

        //3- response'i kaydettiğimiz adım
        //NOT : eger sorgumuzda bir request body gönderiyorsak gönderdigimiz datanin formatini
        //belirlemek zorundayiz. Bunu da given methodundan sonra pre-condition olarak belirtebiliriz

        Response response=given()//givenden sonra pre con
                            .contentType(ContentType.JSON)//postmanda body'yi secip raw'i secip concent type sectigimiz yer
                        .when()//whenden body ve put
                            .body(reqBody.toString())//reqBody json.orgdan geldiği için toStringe cevirmek lazım
                            .put(url);

        response.prettyPrint();

        //4- Assertion
        response//responsenin temel bilgilerini response.then ve assertThat ile test ediyoruz
                .then()//assert then olmadan  gelmez
                .assertThat()
                .statusCode(200)//status codeun 200 old test et(assertThat)
                .contentType("application/json; charset=utf-8")//con. type test eder
                .header("Server","cloudflare")//Server isimli Header’in degerinin cloudflare old
                .statusLine("HTTP/1.1 200 OK");// status line


    }
}
