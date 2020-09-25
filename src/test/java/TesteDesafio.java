import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

public class TesteDesafio {
    String vote_id;

    @Test
    public void favoritaDesfavorita(){
        favorita();
        desfavorita();
    }

    public void favorita() {

        Response response =
                given()
                        .contentType("application/json")
                        .header("x-api-key", "5bb18b8c-e8a9-4c64-8508-7b576554b23f")
                        .body("{\"image_id\": \"H0T5RQVp0\"}")
                        .when().post("https://api.thecatapi.com/v1/favourites");
        String id = response.jsonPath().getString("id");
        vote_id = id;

        System.out.println(vote_id);
        System.out.println("RETORNO FAVORITA => " + response.body().asString());
        response.then().statusCode(200).body("message", containsString("SUCCESS"));

    }
    public void desfavorita() {

        Response response =
                given()
                        .contentType("application/json")
                        .header("x-api-key", "5bb18b8c-e8a9-4c64-8508-7b576554b23f")
                        .pathParam("favourite_id", vote_id)
                        .when().delete("https://api.thecatapi.com/v1/favourites/{favourite_id}");

        System.out.println("RETORNO DESFAVORITA => " + response.body().asString());
        response.then().statusCode(200).body("message", containsString("SUCCESS"));
    }
}
