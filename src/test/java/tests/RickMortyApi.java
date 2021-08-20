package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RickMortyApi {

    @Test
    public void rickMortyApiTest () {
        Response response = given()
                .baseUri("https://rickandmortyapi.com/api/character/")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response.getBody().asString());

        String key1 = new JSONObject(response.getBody().asString()).getJSONObject("info").get("count").toString();
        System.out.println(key1);

        String key2 = new JSONArray(response.getBody().asString()).getJSONObject(0).getJSONArray("result").get("id");
        System.out.println(key2);


     //   String name1 = new JSONArray(response.getBody().asString()).getJSONArray(1).getJSONObject("result").get("name");
      //  System.out.println(name1);


    }
}
