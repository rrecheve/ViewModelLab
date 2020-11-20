package es.unex.giiis.asee.executorslab.data.network;

import java.util.List;

import es.unex.giiis.asee.executorslab.data.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
