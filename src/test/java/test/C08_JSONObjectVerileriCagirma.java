package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JSONObjectVerileriCagirma {
        /*
    {
    "firstName": "John",
    "lastName": "doe",
    "age": 26,
    "address": {
        "streetAddress": "naist street",
        "city": "Nara",
        "postalCode": "630-0192"
                },
    "phoneNumbers": [
                    {
                        "type": "iPhone",
                        "number": "0123-4567-8888"
                    },
                    {
                        "type": "home",
                        "number": "0123-4567-8910"
                    }
                    ]
    }
     */
    @Test
    public void jsonObje01(){
        JSONObject ceptel=new JSONObject();


        ceptel.put("type", "iPhone");
        ceptel.put("number", "0123-4567-8888");

        JSONObject evTel=new JSONObject();

        evTel.put("type", "home");
        evTel.put("number", "0123-4567-8910");

        JSONArray phoneNumbers=new JSONArray();

        phoneNumbers.put(0,ceptel);
        phoneNumbers.put(1,evTel);

        JSONObject adress=new JSONObject();//JSONObject javadaki karşılığı maptir

        adress.put("streetAddress", "naist street");
        adress.put("city","Nara");
        adress.put("postalCode", "630-0192");

        JSONObject  kisiBilgisi=new JSONObject();
        kisiBilgisi.put("firstName", "John");
        kisiBilgisi.put("lastName","doe");
        kisiBilgisi.put("age", 26);
        kisiBilgisi.put("address",adress);
        kisiBilgisi.put("phoneNumbers",phoneNumbers);

        System.out.println(kisiBilgisi);

        /*
        {"firstName":"John"
        ,"lastName":"doe",
        "address":
                {"streetAddress":"naist street",
                "city":"Nara",
                "postalCode":"630-0192"},
        "age":26,
        "phoneNumbers":[
                        {"number":"0123-4567-8888",
                        "type":"iPhone"},
                        {"number":"0123-4567-8910",
                        "type":"home"}]}

         */

        System.out.println("isim : " +kisiBilgisi.get("firstName"));
        System.out.println("soyisim : " +kisiBilgisi.get("lastName"));
        System.out.println("yas : " + kisiBilgisi.get("age"));
        System.out.println("sokak adi : " + kisiBilgisi.getJSONObject("address").get("streetAddress"));
        //getJSONObject bunu dedigimizde bundan baska bişey de cagirabildigimizi anliyor
        System.out.println("sehir : " + kisiBilgisi.getJSONObject("address").get("city"));
        System.out.println("posta kodu : " + kisiBilgisi.getJSONObject("address").get("postalCode"));

        System.out.println("telefon no : " + kisiBilgisi
                        .getJSONArray("phoneNumbers")
                        .getJSONObject(0)
                        .get("number"));

        System.out.println("telefon no : " + kisiBilgisi
                .getJSONArray("phoneNumbers")
                .getJSONObject(0)
                .get("type"));

        System.out.println("telefon no : " + kisiBilgisi
                .getJSONArray("phoneNumbers")
                .getJSONObject(1)
                .get("number"));
        System.out.println("telefon no : " + kisiBilgisi
                .getJSONArray("phoneNumbers")
                .getJSONObject(1)
                .get("type"));

    }
}
