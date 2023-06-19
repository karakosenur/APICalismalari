package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgileriAssertion {
    /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
        gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        oldugunu test edin.
     */

    @Test
    public void get01(){
        // 1- url hazırla

        String url="https://restful-booker.herokuapp.com/booking/10";

        //2- expected data hazırla

        //3- response kaydet

        Response response=given().when().get(url);

        response.prettyPrint();//api'da consola yazdırmak için kullanılan method
                                //normalde yazdırmaya gerek yok biz gorelim diye yazdiriyoruz

        response//responsenin temel bilgilerini response.then ve assertThat ile test ediyoruz
                .then()
                .assertThat()
                .statusCode(200)//status codeun 200 old test et(assertThat)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");


    }
}
