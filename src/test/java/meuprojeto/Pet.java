package meuprojeto;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";
    String uri_token = "";

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

    /* @Test(priority = 3)
     public void incluirToken() throws IOException {
         String jsonBody = lerJson("Jsons/token.json");

         String uri1;
         given()
                 .contentType("aplication/json")
                 .log().all()
                 .when()
                 .get(uri1 + "/" +uri_token)
                 .then()
                 .log().all()
                 .statusCode(200)
                 .body("status", is("available"))
                 .path("category.name");

         System.out.println(" O token é:" + token);
     }

     @Test(priority = 4)
     public void extrairToken() {


                 given()
                         .contentType("aplication/json")
                         .log().all()
                         .when()
                         .get(uri1 + "/" + uri_token)
                         .then()
                         .log().all()
                         .statusCode(200)
                         .body("name", is("Akita"))
                         .body("category.name", is("dog"))
                         .body("status", is("available"))
                         .extract()
                         .path("category.name");


     }
 */
    @Test (priority = 3)
    public void alterarPet() throws IOException {
        String jsonBody = lerJson("Jsons/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Akita"))
                .body("status", is("sold"))
                ;
    }

}
