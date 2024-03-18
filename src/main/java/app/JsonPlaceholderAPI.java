package app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface JsonPlaceholderAPI {
    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") int postId);

    @GET("/posts")
    Call<List<Post>> getAllPosts();
}

