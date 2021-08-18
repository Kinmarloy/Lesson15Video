package tests;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SelenoidTests {
    // open https://selenoid.autotests.cloud/status
    // statusCode: 200
    // "errors":[]
    /*
    	"browsers": {
		"android": 0,
		"chrome": 0,
		"firefox": 0,
		"opera": 0,
		"safari": 0
	},
     */

    @Test
    void successStatusTest() {
        given()
                .when()
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Morty Smith")) // (qa.guru 15 - 1:42))
                .body("status", is("Alive"));  // ТАК МОЖНО ПРОВЕРЯТЬ СРАЗУ НЕСКОЛЬКО ЗНАЧЕНИЙ?
                                                          // КАК ВЫВЕСТИ К КОНСОЛЬ РЕЗУЛЬТАТЫ, ЧТО name = Morty Smith
    }

    @Test
    void successStatusTest2() {
        Response response =   given()
                .when()
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
                //.body("name", is("Morty Smith"));     //ПОЧЕМУ ТУТ НЕ РАБОТАЕТ ТАК ЖЕ, КАКАЯ ЛОГИКА?
                 assertThat(response.path("name"), is("Morty Smith"));
                 assertThat(response.path("status"), is("Alive"));

        System.out.println(response.asString());

        int statusCode = response.statusCode();
        System.out.println("\n" + statusCode);

    }

    @Test
    void successStatusTestWithAssert() {
        Boolean result = given()
                .when()
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .path("name");       // КАК ПРОПИСАТЬ ЗНАЧЕНИЕ КЛЮЧА? QA GURU - 15 - 1:48


        System.out.println(result);
        assertThat(result, is(true));


    }


    @Test
    void successStatusReadyWithAssertThat1Test() {
        ExtractableResponse<Response> result = given()   // ЧТО ЗНАЧИТ  - ExtractableResponse<Response>
                .when()
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .log().body()
                .statusCode(200)
                .extract();

        assertThat(result.path("name"), is("Morty Smith"));

    }
    /*
    @Test
    void successStatusWithoutGivenWhenTest() {
        get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .statusCode(200);
    }

    @Test
    void successStatusWithAuthTest() {
        given().when()
                .get("https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .statusCode(200);
    }

    @Test
    void successStatusWithBasicAuthTest() {
        given()
                .auth().basic("user1","1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .statusCode(200);
    }

    @Test
    void successStatusResponseTest() {
        Response response = given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response);             // bad log - io.restassured.internal.RestAssuredResponseImpl@ed41518
        System.out.println(response.toString());  // bad log - io.restassured.internal.RestAssuredResponseImpl@ed41518
        System.out.println(response.asString());  // {"value":{"message":"Selenoid 98f495722e60da4b35c14814bae240fe6ec75abc built at 2020-09-02_11:14:20AM","ready":true}}
    }

    @Test
    void successStatusResponseWithLogTest() {
        given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    @Test
    void successStatusReadyTest() {
        given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    void successStatusReadyWithAssertThatTest() {
        Boolean result = given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .path("value.ready");

        assertThat(result, is(true));
    }

    @Test
    void successStatusReadyWithAssertThat1Test() {
        ExtractableResponse<Response> result = given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud:4444/wd/hub/status")
                .then()
                .log().body()
                .statusCode(200)
                .extract();

        assertThat(result.path("value.ready"), is(true));
    }
 */

}
