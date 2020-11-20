package es.unex.giiis.asee.executorslab.data.network;

import java.util.List;

import es.unex.giiis.asee.executorslab.data.model.Repo;

public interface OnReposLoadedListener {
    public void onReposLoaded(List<Repo> repos);
}
