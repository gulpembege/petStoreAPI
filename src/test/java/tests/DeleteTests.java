package tests;

import baseUrls.SpecPetStore;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static testDatas.PetStoreDatas.*;

public class DeleteTests extends SpecPetStore {

    @Test(description = "Delete a pet with valid ID")
    public void delete01(){

        int validId=9;

        // prepare endpoint with specurl
        specPetStore.pathParams("pp1","pet","pp2",validId);

        //save response
        Response response = given().spec(specPetStore).when().delete("/{pp1}/{pp2}");

        // assert status code is =200,
        // content-type is = application/json
        // connection is keep-alive
        // body message is equal to valid id

        response.then().assertThat().
                statusCode(successStatusCode).
                contentType(contentType).
                header("Connection",connection).body("message", Matchers.containsString(String.valueOf(validId)));



        // print response
        response.prettyPrint();

    }


    @Test(description = " Delete a pet with invalid ID")
    public void delete02(){

        int invalidId=9999;

        // prepare endpoint with specurl
        specPetStore.pathParams("pp1","pet","pp2",invalidId);

        //save response

        Response response = null;

        try {

            response = given()
                    .spec(specPetStore)
                    .when()
                    .delete("/{pp1}/{pp2}");

        } catch (Exception e) {

            if (e instanceof io.restassured.internal.http.HttpResponseException) {

                HttpResponseException httpResponseException = (HttpResponseException) e;
                Assert.assertEquals(httpResponseException.getStatusCode(), failStatusCode);
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
                    .header("Connection", "keep-alive");

        } else {
            Assert.fail("Test FAILED: Status Code = " + response.statusCode());
        }


    }

}
