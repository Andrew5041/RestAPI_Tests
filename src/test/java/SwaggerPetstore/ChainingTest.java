package SwaggerPetstore;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ChainingTest extends BaseApiTest{


    @Test
    public void shouldCreateCheckAndDeleteThePet(){

        long uniqueId = System.currentTimeMillis();

        Pet pet = new Pet(uniqueId, "Andzia", "available");

        // 2. CREATE (POST /pet)
        // Wysyłamy całego zwierzaka, odbieramy jego ID z odpowiedzi

        long petId = given()
                .spec(requestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .assertThat()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .path("id");


        // 3. READ (GET /pet/{id})
        given()
                .spec(requestSpec)
                .pathParam("id", petId)
                .when()
                .get("/pet/{id}")
                .then()
                .assertThat()
                .spec(responseSpec)
                .statusCode(200)
                .body("name", equalTo("Andzia"));

        // 4. UPDATE (PUT /pet)
        // Zmieniamy status w obiekcie i wysyłamy go ponownie
        pet.setStatus("sold");

        given()
                .spec(requestSpec)
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .assertThat()
                .spec(responseSpec)
                .statusCode(200)
                .body("status", equalTo("sold"));

        // 5. DELETE (DELETE /pet/{id})

        given()
                .spec(requestSpec)
                .pathParam("id", petId)
                .when()
                .delete("/pet/{id}")
                .then()
                .assertThat()
                .spec(responseSpec)
                .statusCode(200);

    }
}
