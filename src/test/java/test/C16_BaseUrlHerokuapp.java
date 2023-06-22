package test;

import baseUrl.HerrokuappBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends HerrokuappBaseUrl {
    /*
    1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
        gonderdigimizde donen response’un status code’unun 200 oldugunu ve
        Response’ta 12 booking oldugunu test edin


        {
        "firstname" : "Ahmet",
        "lastname" : “Bulut",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
        "checkin" : "2021-06-01",
        "checkout" : "2021-06-10"
        },
        "additionalneeds" : "wi-fi"
}
     */

    @Test
    public void get01() {
        specHerokuapp.pathParam("pp1","booking");

        Response response=given().spec(specHerokuapp).when().get("/{pp1}");
        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .body("additionalneeds", Matchers.hasSize(12));
    }

    /*
     2- https://restful-booker.herokuapp.com/booking
        endpointine asagidaki body’ye sahip bir POST
        request gonderdigimizde donen response’un
        status code’unun 200 oldugunu ve “firstname”
        degerinin “Ahmet” oldugunu test edin
     */
    @Test
    public void post01(){
        specHerokuapp.pathParam("pp1","booking");

        Response response=given().spec(specHerokuapp).when().get("/{pp1}");
        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .body("firstname", Matchers.equalTo("Ahmet"));

    }
}
