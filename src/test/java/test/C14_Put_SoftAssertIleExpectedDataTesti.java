package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C14_Put_SoftAssertIleExpectedDataTesti {
            /*
            C14_Put_SoftAssertIleExpectedDataTesti
        http://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki body’ye sahip bir PUT
        request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
        Request Body
        {
        "status": "success",
        "data": {
            "name": “Ahmet",
            "salary": "1230",
            "age": "44",
            "id": 40
        }
        }
        Response Body
         {
            "status": "success",
            "data": {
                "name": “Ahmet",
                "salary": "1230",
                "age": "44",
                "id": 40 }
        },
        "message": "Successfully! Record ha
        s been updated."}
             */

    @Test
    public void put01(){
        //1- url hazırla request body hazırla
        String url ="http://dummy.restapiexample.com/api/v1/update/21";

        JSONObject reqBody=new JSONObject();
        reqBody.put( "name", "Ahmet");
        reqBody.put( "salary", "1230");

       // JSONObject dataBody=new JSONObject();
       // dataBody.put("name", "Ahmet");
       // dataBody.put("salary", "1230");
       // dataBody.put("age", "44");
       // dataBody.put("id",40 );

        //JSONObject reqBody=new JSONObject();
//
        //reqBody.put("status", "success");
        //reqBody.put("data",dataBody);
//
        //System.out.println(reqBody);

        //2- expected data hazırla

        //3- response hazırla
        Response response=given()
               .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .put(url);
//
        response.prettyPrint();
    }





        }
