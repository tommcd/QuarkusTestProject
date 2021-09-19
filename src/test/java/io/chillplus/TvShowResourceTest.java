package io.chillplus;

import io.chillplus.model.TvShow;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(TvShowResource.class)
public class TvShowResourceTest {

    public static final String DEFAULT_TITLE = "AAA";

    @BeforeEach
    public void beforeEach() {
        given()
        .when()
                .delete()
        .then()
                .statusCode(200);
    }

    @Test
    public void createTvShow() {
        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));

        TvShow tvShow = new TvShow();
        tvShow.title = DEFAULT_TITLE;

        given()
                .body(tvShow)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("title", is(tvShow.title));

        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(1));

        TvShow tvShowWithId = new TvShow();
        tvShow.id = 1L;
        tvShow.title = DEFAULT_TITLE;

        given()
                .body(tvShowWithId)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(400);
    }

    @Test
    public void checkTvShowTitleIsNotBlank() {
        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));

        TvShow tvShow = new TvShow();
        tvShow.title = "";

        given()
                .body(tvShow)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(400);

        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));
    }

    @Test
    public void getAllTvShows() {
        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));

        TvShow bbShow = new TvShow();
        bbShow.title = "AA";

        given()
                .body(bbShow)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("title", is(bbShow.title));

        TvShow aaShow = new TvShow();
        aaShow.title = "BB";

        given()
                .body(aaShow)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("title", is(aaShow.title));

        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(2));
    }

    @Test
    public void getOneTvShow() {
        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));

        TvShow tvShow = new TvShow();
        tvShow.title = DEFAULT_TITLE;

        given()
                .body(tvShow)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("title", is(tvShow.title));

        given()
        .when()
                .get("/{id}", 1)
        .then()
                .statusCode(200);
    }

    @Test
    public void getNonExistingTvShow() {
        given()
        .when()
                .get("/42")
        .then()
                .statusCode(404);
    }

    @Test
    public void deleteAllTvShows() {
        TvShow tvShow = new TvShow();
        tvShow.title = DEFAULT_TITLE;

        given()
                .body(tvShow)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("title", is(tvShow.title));

        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(1));

        given()
        .when()
                .delete()
        .then()
                .statusCode(200);

        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));
    }

    @Test
    public void deleteOneTvShow() {
        TvShow tvShow = new TvShow();
        tvShow.title = DEFAULT_TITLE;

        given()
                .body(tvShow)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        .when()
                .post()
        .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("title", is(tvShow.title));

        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(1));

        given()
        .when()
                .delete()
        .then()
                .statusCode(200);

        given()
        .when()
                .get()
        .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));
    }

}
