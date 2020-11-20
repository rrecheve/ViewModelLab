package es.unex.giiis.asee.executorslab.data.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import es.unex.giiis.asee.executorslab.data.model.Repo;

@Database(entities = {Repo.class}, version = 1, exportSchema = false)
public abstract class RepoDatabase extends RoomDatabase {
    private static RepoDatabase INSTANCE;

    public synchronized static RepoDatabase getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, RepoDatabase.class, "repos.db").build();
        }
        return INSTANCE;
    }

    public abstract RepoDao repoDao();
}
