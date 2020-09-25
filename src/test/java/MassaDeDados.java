public class MassaDeDados {
    //API
    String api = "x-api-key";
    // Chave
    String chave = "5bb18b8c-e8a9-4c64-8508-7b576554b23f";
    //Cadastro
    String vote_id;
    String urlCadastro = "user/passwordlesssignup";
    String corpoCadastro = "{\"email\": \"teste@gmail.com\", \"appDescription\": \"teste the cat api\"}";
    //Votação
    String corpoVotacao = "{\"image_id\":\"d01\",\"value\":true,\"sub_id\":\"demo-d754d8\"}";
    //Favorita
    String corpoFavorita = "{\"image_id\": \"H0T5RQVp0\"}";
    //Desfavorita
    String corpoDesfavorita = "favourites/{favourite_id}";

}
