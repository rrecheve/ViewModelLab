package es.unex.giiis.asee.executorslab.data.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import es.unex.giiis.asee.executorslab.AppExecutors;
import es.unex.giiis.asee.executorslab.data.model.Repo;

public class RepoNetworkDataSource {
    private static final String LOG_TAG = RepoNetworkDataSource.class.getSimpleName();
    private static RepoNetworkDataSource sInstance;

    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<Repo[]> mDownloadedRepos;

    private RepoNetworkDataSource() {
        mDownloadedRepos = new MutableLiveData<>();
    }

    public synchronized static RepoNetworkDataSource getInstance() {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            sInstance = new RepoNetworkDataSource();
            Log.d(LOG_TAG, "Made new network data source");
        }
        return sInstance;
    }

    public LiveData<Repo[]> getCurrentRepos() {
        return mDownloadedRepos;
    }

    /**
     * Gets the newest repos
     */
    public void fetchRepos(String username) {
        Log.d(LOG_TAG, "Fetch repos started");
        // Get gata from network and pass it to LiveData
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(username, repos -> mDownloadedRepos.postValue(repos.toArray(new Repo[0]))));
    }

}
