package tests;

import baseUrls.SpecPetStore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PojoCategory;
import pojos.PojoPet;
import pojos.PojoTag;
import io.qameta.allure.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.*;
import static testDatas.PetStoreDatas.*;

public class CreateTests extends SpecPetStore {


    @Test(description = "Create a new pet with full form data")
    public void post01(){

        // prepare endpoint with specurl
        specPetStore.pathParam("pp1","pet");

        // prepare pojo datas
        PojoCategory category = new PojoCategory(2,"cats");
        List<PojoTag> pojoTag = new ArrayList<>();
        List<String> photoUrl = new ArrayList<>();

        pojoTag.add(new PojoTag(2,"catTag"));
        photoUrl.add(0,"https://tr.pinterest.com/pin/44262008832059982/");

        // prepare request body with pojos
        PojoPet reqBody = new PojoPet(9,category,"pakos",photoUrl,pojoTag,"available");


        // prepare expected body with pojos
        PojoPet expBody = reqBody;


        // save response
        Response response = given().spec(specPetStore).body(reqBody).contentType(ContentType.JSON).when().post("/{pp1}");

        // assert status code is =200,
        // content-type is = application/json
        // connection is keep-alive

        response.then().assertThat().
                statusCode(successStatusCode).
                contentType(contentType).
                header("Connection",connection);

        // assert response body and expected body

        // first - convert response json format to pojo format
        PojoPet responsePojo = response.as(PojoPet.class);

        // then - assert with testNG assertions
        assertEquals(responsePojo.getId(),expBody.getId());
        assertEquals(responsePojo.getCategory().getId(),expBody.getCategory().getId());
        assertEquals(responsePojo.getCategory().getName(),expBody.getCategory().getName());
        assertEquals(responsePojo.getName(),expBody.getName());
        assertEquals(responsePojo.getPhotoUrls(),expBody.getPhotoUrls());
        assertEquals(responsePojo.getTags().get(0).getId(),expBody.getTags().get(0).getId());
        assertEquals(responsePojo.getTags().get(0).getName(),expBody.getTags().get(0).getName());
        assertEquals(responsePojo.getStatus(),expBody.getStatus());



        response.prettyPrint();

    }

    @Test(description = "Create a new pet image with ID")
    public void post02(){  //


        specPetStore.pathParams("pp1","pet","pp2","9","pp3","uploadImage");

       File file = new File("src/test/java/testDatas/d4d78c8ded922347d0ee8e5e87c9944f.jpg");

        Response response = given().spec(specPetStore).contentType("multipart/form-data").multiPart("file",file).multiPart("additionalMetadata","pakosFoto").when().post("/{pp1}/{pp2}/{pp3}");

        // assert status code is =200,
        // content-type is = application/json
        // and body mesagge contains "uploaded"

        response.then().assertThat().
                statusCode(successStatusCode).
                contentType(contentType).body("message",containsString("uploaded"));

        response.prettyPrint();


    }






}
