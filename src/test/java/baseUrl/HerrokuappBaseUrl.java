package baseUrl;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerrokuappBaseUrl {

    protected RequestSpecification specHerokuapp;

    @Before
    public void setUp() {//class levelde olusturup atamayi before icinde yapiyoruz
        specHerokuapp= new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();


    }

}
