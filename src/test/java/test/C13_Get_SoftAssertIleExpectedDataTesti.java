package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C13_Get_SoftAssertIleExpectedDataTesti {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
    Response Body
    {
    "status": "success",
    "data": {
        "id": 3,
        "employee_name": "Ashton Cox",
        "employee_salary": 86000,
        "employee_age": 66,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
    }
     */
    @Test
    public void get01(){
        //1- url ve endpoint hazırla
        String url ="http://dummy.restapiexample.com/api/v1/employee/3";

        //2- expected data hazırla
        JSONObject expDataBody=new JSONObject();

        expDataBody.put("id", 3);
        expDataBody.put("employee_name", "Ashton Cox");
        expDataBody.put("employee_salary",86000);
        expDataBody.put( "employee_age", 66);
        expDataBody.put("profile_image", "");

        JSONObject expBody=new JSONObject();

        expBody.put("status", "success");
        expBody.put("data",expDataBody);
        expBody.put("message", "Successfully! Record has been fetched.");

        System.out.println(expBody);

        //3- response hazırla
        Response response=given().when().get(url);

        response.prettyPrint();

        //4- assertion

        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath=response.jsonPath();
        softAssert.assertEquals(jsonPath.get("status"),expBody.get("status"));
        softAssert.assertEquals(jsonPath.get("message"),expBody.get("message"));

        softAssert.assertEquals(jsonPath.get("data.id"),expBody.getJSONObject("data").get("id"));
        softAssert.assertEquals(jsonPath.get("data.employee_name"),expBody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(jsonPath.get("data.employee_salary"),expBody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(jsonPath.get("data.employee_age"),expBody.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(jsonPath.get("data.profile_image"),expBody.getJSONObject("data").get("profile_image"));


        softAssert.assertAll();

        /*
        JsonPath responseJsonPath=(response.jsonPath());

        assertEquals(expBody.get("status"),responseJsonPath.get("status"));
        assertEquals(expBody.get("message"),responseJsonPath.get("message"));

        assertEquals(expBody.getJSONObject("data").get("id"),responseJsonPath.get("data.id"));
        assertEquals(expBody.getJSONObject("data").get("employee_name"),responseJsonPath.get("data.employee_name"));
        assertEquals(expBody.getJSONObject("data").get("employee_salary"),responseJsonPath.get("data.employee_salary"));
        assertEquals(expBody.getJSONObject("data").get("employee_age"),responseJsonPath.get("data.employee_age"));
        assertEquals(expBody.getJSONObject("data").get("profile_image"),responseJsonPath.get("data.profile_image"));
         */
    }
}
