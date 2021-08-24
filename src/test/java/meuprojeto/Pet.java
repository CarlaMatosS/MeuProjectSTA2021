package meuprojeto;

import org.testng.annotations.Test;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("Jsons/pet1.json");

        given() //Dado
                .contentType("application/json")
                .log().all()  //pedir pra logar , registrar
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)

        ;
    }

}
