package mvp.it.dekz.notes;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        //init realm
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("notes.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
