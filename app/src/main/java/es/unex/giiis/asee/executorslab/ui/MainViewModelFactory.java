package es.unex.giiis.asee.executorslab.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.unex.giiis.asee.executorslab.data.RepoRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link RepoRepository}
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RepoRepository mRepository;

    public MainViewModelFactory(RepoRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}