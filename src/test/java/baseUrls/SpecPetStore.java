package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class SpecPetStore {

    protected RequestSpecification specPetStore;

    @BeforeMethod
    public void setUp(){
        specPetStore=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").
                                              setContentType(ContentType.JSON).
                                              build();
    }
}
