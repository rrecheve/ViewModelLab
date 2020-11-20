package es.unex.giiis.asee.executorslab.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import es.unex.giiis.asee.executorslab.AppContainer;
import es.unex.giiis.asee.executorslab.MyApplication;
import es.unex.giiis.asee.executorslab.R;
import androidx.lifecycle.ViewModelProvider;


public class MainActivity extends AppCompatActivity implements MyAdapter.OnListInteractionListener {

    private MyAdapter mAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.repoList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(new ArrayList<>(), this);
        mRecyclerView.setAdapter(mAdapter);
        EditText searchBox = findViewById(R.id.searchBox);
        Button searchButton = findViewById(R.id.searchButton);
        mProgressBar = findViewById(R.id.progressBar);

        //MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        MainActivityViewModel mViewModel = new ViewModelProvider(this, appContainer.factory).get(MainActivityViewModel.class);
        mViewModel.getRepos().observe(this, repos -> {
            mAdapter.swap(repos);
            // Show the repo list or the loading screen based on whether the repos data exists and is loaded
            if (repos != null && repos.size() != 0) showReposDataView();
            else showLoading();
        });

        searchButton.setOnClickListener(view -> mViewModel.setUsername(searchBox.getText().toString()));
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(mViewModel::onRefresh);
    }

    private void showLoading(){
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void showReposDataView(){
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListInteraction(String url) {
        Uri webPage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }
}
