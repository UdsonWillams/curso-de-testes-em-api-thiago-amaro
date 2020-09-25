import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TesteTheCatApi {

    String vote_id;
    @Test
    public void cadastro() {

        String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
        String corpo = "{\"email\": \"teste@gmail.com\", \"appDescription\": \"teste the cat api\"}";
        //DADO QUE
        //GIVEN
        //QUANDO ESTIVER COM
        //WHEN
        //ENTÃO
        //THEN
        Response response = given().contentType("application/json").body(corpo).
        when().post(url);

        response.then().body("message", containsString("SUCCESS")).statusCode(200);

        System.out.println("RETORNO => " + response.body().asString());
    }
    @Test
    public void votacao() {

        String url = "https://api.thecatapi.com/v1/votes/";

        Response response = given().contentType("application/json")
                .body("{\"image_id\":\"d01\",\"value\":true,\"sub_id\":\"demo-d754d8\"}").
                when().post(url);

        response.then().body("message", containsString("SUCCESS")).statusCode(200);

        System.out.println("RETORNO => " + response.body().asString());
        String id = response.jsonPath().getString("id");
        vote_id = id;
        System.out.println("RETORNO => " + id);
    }
    //@Test
    //public void deletaVotacao(){
   //     votacao();
   //     deletaVoto();
    //}
    @Test
    public void deletaVoto() {
        String url = "https://api.thecatapi.com/v1/votes/{vote_id}";
        Response response =
                given()
                .contentType("application/json")
                .header("x-api-key", "$$.env.x-api-key")
                .pathParam("vote_id", vote_id)
                .when().delete(url);
        System.out.println("RETORNO => " + response.body().asString());
        response.then().body("message", containsString("SUCCESS")).statusCode(200);

    }
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
