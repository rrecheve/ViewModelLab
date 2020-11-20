package es.unex.giiis.asee.executorslab;

import android.app.Application;

public class MyApplication extends Application {

    // Instance of AppContainer that will be used by all the Activities of the app
    public AppContainer appContainer;
    public MyApplication(){
       appContainer = new AppContainer(this);
    }
}