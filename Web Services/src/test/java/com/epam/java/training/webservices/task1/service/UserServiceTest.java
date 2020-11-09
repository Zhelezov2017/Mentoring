package com.epam.java.training.webservices.task1.service;

import com.epam.java.training.webservices.task1.model.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.RestAssured.given;


public class UserServiceTest {

    @Test
    public void whenGetAll_thenStatusNoContent() {
        given().log().body()
                .contentType("application/json")
                .when().get("/users/getAll")
                .then().log().body()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void whenGetUserById_thenStatusNotFound() {
        given().log().body()
                .contentType("application/json")
                .when().get("/users/1")
                .then().log().body()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenCreateUser_thenStatusExpectationFailed() {
        given().log().body()
                .contentType("application/x-www-form-urlencoded")
                .when().post("/users")
                .then().log().body()
                .statusCode(HttpStatus.EXPECTATION_FAILED.value());
    }

    @Test
    public void whenUpdateUser_thenStatusInternalServerError() {
        User user = new User();

        given().log().body()
                .contentType("application/json").body(user)
                .when().put("/users")
                .then().log().body()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void whenDeleteUser_thenStatusExpectationFailed() {
        given().log().body()
                .contentType("application/json")
                .when().delete("/users/1")
                .then().log().body()
                .statusCode(HttpStatus.EXPECTATION_FAILED.value());
    }

    @Test
    public void whenGetUserLogo_thenStatusNotFound() {
        given().log().body()
                .contentType("application/json")
                .when().post("/users/1/uploadUserLogo")
                .then().log().body()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenUploadUserLogo_thenStatusNotFound() {
        given().log().body()
                .contentType("application/json")
                .when().get("/users/1/downloadUserLogo")
                .then().log().body()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}