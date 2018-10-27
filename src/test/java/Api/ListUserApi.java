package Api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.RestAssured.given;

public class ListUserApi {


    public void getListUsers(){

        Response response = given()
                .header("Content-Type","application/json") // headet tanımı yapıyoruz
                .queryParam("delay","3")//parametre ekleniyor
                .expect()
                .statusCode(200).//dönen response un 200 olduğunu doğruluyor.
                when()
                .get("https://reqres.in/api/users");
        response.prettyPrint();

        //response u string olarak convert ediyoruz
        String responseBody = response.getBody().asString();
        // string response u JsonPath 'e convert ediyoruz
        JsonPath jsonPath = new JsonPath(responseBody);
        // doğrulaycağımız parametreyi bir değişkene alıyoruz artık istediğimiz
        // şekilde doğrulama yapabiliriz.
        int succeeded = Integer.parseInt(jsonPath.get("per_page").toString());
        Assert.assertTrue( succeeded>0);

        response. then()
                .assertThat()
                .body(matchesJsonSchema(new File("src\\test\\java\\JsonSchemas\\ListUserJsonSchemas.json")));
    }
}
