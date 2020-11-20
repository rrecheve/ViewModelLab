package es.unex.giiis.asee.executorslab;

import android.content.Context;

import es.unex.giiis.asee.executorslab.data.RepoRepository;
import es.unex.giiis.asee.executorslab.data.network.RepoNetworkDataSource;
import es.unex.giiis.asee.executorslab.data.roomdb.RepoDatabase;
import es.unex.giiis.asee.executorslab.ui.MainViewModelFactory;

public class AppContainer {

    private RepoDatabase database;
    private RepoNetworkDataSource networkDataSource;
    public RepoRepository repository;
    public MainViewModelFactory factory;

    public AppContainer(Context context){
        database = RepoDatabase.getInstance(context);
        networkDataSource = RepoNetworkDataSource.getInstance();
        repository = RepoRepository.getInstance(database.repoDao(), networkDataSource);
        factory = new MainViewModelFactory(repository);
    }
}
