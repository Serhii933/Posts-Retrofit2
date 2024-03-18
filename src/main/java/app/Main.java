package app;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);


        int postId = 1;
        Call<Post> callSinglePost = jsonPlaceholderAPI.getPostById(postId);
        callSinglePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Код помилки: " + response.code());
                    return;
                }

                Post post = response.body();
                System.out.println("Один пост:");
                System.out.println("ID: " + post.getId());
                System.out.println("UserID: " + post.getUserId());
                System.out.println("Заголовок: " + post.getTitle());
                System.out.println("Текст: " + post.getText());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                System.out.println("Помилка: " + t.getMessage());
            }
        });

        
        Call<List<Post>> callAllPosts = jsonPlaceholderAPI.getAllPosts();
        try {
            Response<List<Post>> response = callAllPosts.execute();
            if (!response.isSuccessful()) {
                System.out.println("Код помилки: " + response.code());
                return;
            }

            List<Post> posts = response.body();
            System.out.println("\nУсі пости:");
            for (Post post : posts) {
                System.out.println("ID: " + post.getId());
                System.out.println("UserID: " + post.getUserId());
                System.out.println("Заголовок: " + post.getTitle());
                System.out.println("Текст: " + post.getText());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

