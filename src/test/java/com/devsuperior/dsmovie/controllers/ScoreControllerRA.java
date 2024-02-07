package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ScoreControllerRA {

    private String clientUsername, clientPassword, adminUsername, adminPassword;
    private String clientToken, adminToken, invalidToken;
    private Long existingId, nonExistingId, dependableId;
    private String movieName;
    private Map<String, Object> postScoreInstance, postScoreInstanceWithoutId, postScoreInstanceNegativeScore;


    @BeforeEach
    public void setUp() throws Exception {
        baseURI = "http://localhost:8080";

        clientUsername = "alex@gmail.com";
        clientPassword = "123456";
        adminUsername = "maria@gmail.com";
        adminPassword = "123456";

        nonExistingId = 0L;
        dependableId = 1L;
        existingId = 2L;

        clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
        adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
        invalidToken = adminToken + "xpto";

        postScoreInstance = new HashMap<>();
        postScoreInstance.put("movieId", nonExistingId);
        postScoreInstance.put("score", 3.0);

        postScoreInstanceWithoutId = new HashMap<>();
        postScoreInstanceWithoutId.put("score", 3.0);

        postScoreInstanceNegativeScore = new HashMap<>();
        postScoreInstanceNegativeScore.put("movieId", existingId);
        postScoreInstanceNegativeScore.put("score", -2.0);
    }
    @Test
    public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {
        JSONObject newScore = new JSONObject(postScoreInstance);
        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
				.body(newScore)
                .accept(ContentType.JSON)
                .when()
                .put("/scores")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {
        postScoreInstance.remove("movieId");
        JSONObject newScore = new JSONObject(postScoreInstance);
        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newScore)
                .accept(ContentType.JSON)
                .when()
                .put("/scores")
                .then()
                .assertThat()
                .statusCode(422);
    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {
        postScoreInstance.put("movieId", existingId);
        postScoreInstance.put("score", -2.0);
        JSONObject newScore = new JSONObject(postScoreInstance);
        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newScore)
                .accept(ContentType.JSON)
                .when()
                .put("/scores")
                .then()
                .assertThat()
                .statusCode(422);
    }
}
