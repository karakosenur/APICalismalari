package testDataKlasoru;

import org.json.JSONObject;

public class TestDataHerokuapp {

    public int basariliStatusCode=200;

    public JSONObject bookingDatesOlusturJson(){

        JSONObject bookingDates=new JSONObject();

        bookingDates.put("checkin" , "2021-06-01");
        bookingDates.put("checkout" , "2021-06-10");

        return bookingDates;
    }

    public JSONObject bookingOlusturJson(){

        JSONObject booking=new JSONObject();

        booking.put("firstname" , "Ali");
        booking.put("lastname","Bak");
        booking.put("totalprice",500);
        booking.put("depositpaid",false);
        booking.put("additionalneeds","wi-fi");
        booking.put("bookingdates",bookingDatesOlusturJson());

        return booking;
    }

    public JSONObject expectedBodyOlusturJson(){
        JSONObject expData=new JSONObject();

        expData.put("bookingid",24);
        expData.put("booking",bookingOlusturJson());

        return expData;
    }
}
