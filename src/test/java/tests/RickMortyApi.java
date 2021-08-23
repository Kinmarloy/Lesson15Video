package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aspectj.weaver.ast.Var;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Objects;

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

        //System.out.println(response.getBody().asString());

 /*       String key1 = new JSONObject(response.getBody().asString()).getJSONObject("info").get("count").toString();
        System.out.println(key1);

        String key2 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(0).toString();
        System.out.println(key2);

        String earth137 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(0).getJSONObject("origin").get("name").toString();
        System.out.println(earth137);

        String infoMorty = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(1).toString();
        System.out.println("ИНФО " + infoMorty);

        String nameMorty7 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(1).get("name").toString();
        System.out.println(nameMorty7);

        String episode = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(1).getJSONArray("episode").toString();
        System.out.println(episode);


  */
        /** ЗАДАНИЕ 1 - Найти информацию по персонажу Морти Смит.  */

        //ВАРИАНТ 1

        String name = "Morty Smith";
        int m = 0;
        var nameMortyy = "".toString();

            while ( !Objects.equals(nameMortyy, name)) {
                nameMortyy = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(m).get("name").toString();
                m++;
            }
        String infoMortyy = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(m-1).toString();
        System.out.println("ЗАДАНИЕ 1 " + infoMortyy);


        //ВАРИАНТ2
        var nameMortyy2 = "".toString();
        int x = 0;
            if (nameMortyy2 != "Morty Smith"){
                nameMortyy2 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(x).get("name").toString();
                x = x+1;
            }
        String infoMortyy2 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(x).toString();
        System.out.println("ЗАДАНИЕ 1 " + infoMortyy2);

        /** ЗАДАНИЕ 2 - Выбрать из ответа последний эпизод где появлялся Морти.  */


  /*

        String infoMorty7 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(0).get("name").toString();

        String nameMorty9 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(1).get("name").toString();
        System.out.println(nameMorty9);


   */


  /*      // Выбрать из ответа последний эпизод где появлялся Морти.
        int n = 1;

        String episode1 = new JSONObject(response.getBody().asString()).getJSONArray("results").getJSONObject(1).getJSONArray("episode").get(n).toString();
        System.out.println(episode1);


   */


     //   String name1 = new JSONArray(response.getBody().asString()).getJSONArray(1).getJSONObject("result").get("name");
      //  System.out.println(name1);


    }
}
