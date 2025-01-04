package tests;

import baseUrls.SpecPetStore;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static testDatas.PetStoreDatas.*;


public class ReadTests extends SpecPetStore {



    @Test(description = "Find pet by valid status")
    public void get01(){

        // prepare endpoint with specurl
        specPetStore.pathParams("pp1","pet","pp2","findByStatus").queryParam("status","sold");


        //save response
        Response response = given().spec(specPetStore).when().get("/{pp1}/{pp2}");

        // assert status code is =200,
        // content-type is = application/json
        // connection is keep-alive


        response.then().assertThat().
                statusCode(successStatusCode).
                contentType(contentType).
                header("Connection",connection);


        // print response
        response.prettyPrint();

    }

    @Test(description = "Find pet by valid ID")
    public void get02(){

        specPetStore.pathParams("pp1","pet","pp2","9");
        Response response = given().spec(specPetStore).when().get("/{pp1}/{pp2}");

        // assert status code is =200,
        // content-type is = application/json
        // connection is = keep-alive
        // response body name is "pakos"


        response.then().assertThat().
                statusCode(successStatusCode).
                contentType(contentType).
                header("Connection",connection).body("name", equalTo("pakos"));


        response.prettyPrint();
    }


    @Test(description = "Find pet by invalid ID")
    public void get03() {

        specPetStore.pathParams("pp1", "pet", "pp2", "9999");

        Response response = null;

        try {

            response = given()
                    .spec(specPetStore)
                    .when()
                    .get("/{pp1}/{pp2}");

        } catch (Exception e) {

            if (e instanceof io.restassured.internal.http.HttpResponseException) {

                HttpResponseException httpResponseException = (HttpResponseException) e;
                Assert.assertEquals(httpResponseException.getStatusCode(), failStatusCode);
                Assert.assertTrue(httpResponseException.getMessage().contains("Not Found"));
                System.out.println("Test Passed! Status code is = " + failStatusCode);
                return;


            } else {

                Assert.fail("Test FAILED: " + e.getMessage());
            }
        }

        if (response != null && response.statusCode() == 404) {
            System.out.println("Test Passed! Status code is = " + failStatusCode);
            response.then().assertThat()
                    .contentType("application/json")
                    .header("Connection", "keep-alive")
                    .body("message", equalTo("Pet not found"));
        } else {

            Assert.fail("Test FAILED: Status Code = " + response.statusCode());
        }



    }


 }











