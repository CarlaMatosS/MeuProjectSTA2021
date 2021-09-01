package meuprojeto;

import org.testng.annotations.Test;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 1)
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("Jsons/pet1.json");

        given() //Dado
                .contentType("application/json")
                .log().all()  //pedir pra logar , registrar
                .body(jsonBody)
                .when()  //Quando
                .post(uri)
                .then() // Então
                .log().all()
                .statusCode(200)
                .body("name", is("Akita"))
                .body("status", is("available"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("sta"))
        ;
    }

    @Test(priority = 2)
    public void consultarPet() {

        String petId = "278652334";
        given()
                .contentType("aplication/json")
                .log().all()
                .when()
                .get(uri + "/" + petId)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Akita"))
                .body("category.name", is("dog"))
                .body("status", is("available"))
        ;

    }

}
