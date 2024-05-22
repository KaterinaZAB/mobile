package ru.mirea.zubarevaes.mireaproject.ui.net;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;



public interface PostApi {
    @GET("/todos")
    Call<List<Post>> getPosts();
}
