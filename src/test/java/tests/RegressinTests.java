package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static utils.FileUtils.readStringFromFile;

public class RegressinTests {

    @BeforeAll
    static void setup () {
        RestAssured.filters(new AllureRestAssured()); //ЧТО ЭТО ЗА ФИЛЬТР?
        RestAssured.baseURI = "https://reqres.in/";

    }

    @Test
    void successUsersListTest (){
        given()
                .when()
                .get("/api/users?page=2")
        .then()
                .statusCode(200)
                .log().body()
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void successLoginTestV1 () throws IOException {

        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/loginData.json"))));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body.toString())
        .when()
                .post("/api/login")
        .then()
                .statusCode(200)
                .log().all()
                .extract().response(); //("token", is(notNullValue()));
        Assertions.assertTrue(new JSONObject(response.getBody().asString()).get("token").toString() != null);

        System.out.println(response);
    }

    @Test
    void successLoginWithDataInFileTestV2() {
        String data = readStringFromFile("./src/test/resources/loginData.json");
        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .log().body()
                .body("token", is(notNullValue()));
    }


    @Test
    void unSuccessLoginTest() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"peter@klaven\" }")
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .log().body()
                .body("error", is("Missing password"));
    }

}
