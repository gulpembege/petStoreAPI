package tests;

import baseUrls.SpecPetStore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PojoCategory;
import pojos.PojoPet;
import pojos.PojoTag;
import io.qameta.allure.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import static testDatas.PetStoreDatas.*;

public class UpdateTests extends SpecPetStore {

    @Test(description = "Update a pet with ID")
    public void put01(){

        String newName="umus";

        // prepare endpoint with specurl
        specPetStore.pathParam("pp1","pet");

        // prepare endpoint with specurl
        specPetStore.pathParam("pp1","pet");

        // prepare pojo datas
        PojoCategory category = new PojoCategory(2,"cats");
        List<PojoTag> pojoTag = new ArrayList<>();
        List<String> photoUrl = new ArrayList<>();

        pojoTag.add(new PojoTag(2,"catTag"));
        photoUrl.add(0,"https://tr.pinterest.com/pin/257127459966505816/");

        // prepare request body with pojos , update name pakos > umus
        PojoPet reqBody = new PojoPet(9,category,newName,photoUrl,pojoTag,"available");


        // prepare expected body with pojos
        PojoPet expBody = reqBody;


        // save response
        Response response = given().spec(specPetStore).body(reqBody).contentType(ContentType.JSON).when().put("/{pp1}");

        // assert status code is =200,
        // content-type is = application/json
        // connection is keep-alive

        response.then().assertThat().
                statusCode(successStatusCode).
                contentType(contentType).
                header("Connection",connection);

        // assert "name" equals newName

        PojoPet responsePojo = response.as(PojoPet.class);

        assertEquals(responsePojo.getName(),newName);

        response.prettyPrint();




    }


}
