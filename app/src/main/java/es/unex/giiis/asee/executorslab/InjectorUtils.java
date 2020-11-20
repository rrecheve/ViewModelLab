package es.unex.giiis.asee.executorslab;

import android.content.Context;

import es.unex.giiis.asee.executorslab.data.RepoRepository;
import es.unex.giiis.asee.executorslab.data.network.RepoNetworkDataSource;
import es.unex.giiis.asee.executorslab.data.roomdb.RepoDatabase;
import es.unex.giiis.asee.executorslab.ui.MainViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for the app
 */
public class InjectorUtils {

    public static RepoRepository provideRepository(Context context) {
        RepoDatabase database = RepoDatabase.getInstance(context.getApplicationContext());
        RepoNetworkDataSource networkDataSource = RepoNetworkDataSource.getInstance();
        return RepoRepository.getInstance(database.repoDao(), networkDataSource);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        RepoRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

}