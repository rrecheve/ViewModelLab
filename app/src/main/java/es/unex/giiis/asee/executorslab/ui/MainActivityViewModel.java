package es.unex.giiis.asee.executorslab.ui;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.unex.giiis.asee.executorslab.data.RepoRepository;
import es.unex.giiis.asee.executorslab.data.model.Repo;

/**
 * {@link ViewModel} for {@link MainActivity}
 */
class MainActivityViewModel extends ViewModel {

    private final RepoRepository mRepository;
    private final LiveData<List<Repo>> mRepos;
    private String mUsername = "";

    public MainActivityViewModel(RepoRepository repository) {
        mRepository = repository;
        mRepos = mRepository.getCurrentRepos();
    }

    public void setUsername(String username){
        mUsername = username;
        mRepository.setUsername(username);
    }

    public void onRefresh() {
        mRepository.doFetchRepos(mUsername);
    }

    public LiveData<List<Repo>> getRepos() {
        return mRepos;
    }


}
