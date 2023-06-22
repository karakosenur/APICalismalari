package test;

import baseUrl.HerrokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_TestDataKullanimi extends HerrokuappBaseUrl {
    /*
       https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
       bir POST request gonderdigimizde donen response’un status kodunu ve id haric
       body'sinin asagidaki gibi oldugunu test edin.
       Request body
             {
             "firstname" : "Ali",
             "lastname" : “Bak",
             "totalprice" : 500,
             "depositpaid" : false,
             "bookingdates" : {
                         "checkin" : "2021-06-01",
                         "checkout" : "2021-06-10"
                           },
             "additionalneeds" : "wi-fi"
              }
       Expected Body
       {
       "bookingid":24,
       "booking":{
               "firstname":"Ali",
               "lastname":"Bak",
               "totalprice":500,
               "depositpaid":false,
               "bookingdates":{
                               "checkin":"2021-06-01",
                               "checkout":"2021-06-10"
                               },
               "additionalneeds":"wi-fi"
                  }
       }
        */
    @Test
    public void post01(){
        //1- url ve reqBody hazırla
        specHerokuapp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp=new TestDataHerokuapp();

        JSONObject reqBody=testDataHerokuapp.bookingOlusturJson();//objeye atıyoruz cünkü assertte
                                                                  // ve response de kolayda kullanmak icin

        //2- expected data hazırla
        JSONObject expData=testDataHerokuapp.expectedBodyOlusturJson();

        //3- response hazırla
        Response response =given()
                .spec(specHerokuapp)
                .contentType(ContentType.JSON)//put request old için sontentType ve body yazıyoruz
                .when()
                .body(reqBody.toString())//toStringe donusmek zorunda cunku jsonObject
                .post("/{pp1}");
        response.prettyPrint();//burda response kendi methodlarını kullanıyoruz

        //4- assertion
        JsonPath respJP=response.jsonPath();//response bodysine girmek path harici olmuyor
                                            //booking.firstname bunu yapmak için path yapmak lazım
                                            //***junit yada testng asserti kullanacaksak path yapmamız lazım

        assertEquals(testDataHerokuapp.basariliStatusCode,response.getStatusCode());
        assertEquals(expData.getJSONObject("booking").get("firstname"),respJP.get("booking.firstname"));
        assertEquals(expData.getJSONObject("booking").get("lastname"),respJP.get("booking.lastname"));
        assertEquals(expData.getJSONObject("booking").get("totalprice"),respJP.get("booking.totalprice"));
        assertEquals(expData.getJSONObject("booking").get("depositpaid"),respJP.get("booking.depositpaid"));
        assertEquals(expData.getJSONObject("booking").get("additionalneeds"),respJP.get("booking.additionalneeds"));


        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                respJP.get("booking.bookingdates.checkin"));
        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                respJP.get("booking.bookingdates.checkout"));//pathde aralara nokta koymamız yeterli oluyor,
                                                             // jsonObject te içine girmemiz gerek









    }
}
