package es.unex.giiis.asee.executorslab.data.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.executorslab.AppExecutors;
import es.unex.giiis.asee.executorslab.data.model.Repo;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposNetworkLoaderRunnable implements Runnable{

    private final OnReposLoadedListener mOnReposLoadedListener;
    private final String mUsername;

    public ReposNetworkLoaderRunnable(String username, OnReposLoadedListener onReposLoadedListener){
        mOnReposLoadedListener = onReposLoadedListener;
        mUsername = username;
    }

    @Override
    public void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.listRepos(mUsername);
        try {
            Response<List<Repo>> response = call.execute();
            List<Repo> repos = response.body() == null ? new ArrayList<>() : response.body();
            AppExecutors.getInstance().mainThread().execute(() -> mOnReposLoadedListener.onReposLoaded(repos));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
