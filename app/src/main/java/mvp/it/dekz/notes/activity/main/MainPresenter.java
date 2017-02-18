package mvp.it.dekz.notes.activity.main;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import mvp.it.dekz.notes.base.Presenter;
import mvp.it.dekz.notes.model.Note;

public class MainPresenter implements Presenter<MainView>{
    private MainView mainView;

    @Override
    public void onAttach(MainView View) {
        mainView = View;
    }

    @Override
    public void onDetach() {
        mainView = null;
        //notes.removeChangeListener(this);
    }

    public void getNotesList(){
        /*Realm realm = Realm.getDefaultInstance();
        notes = realm.where(Note.class).findAll();
        notes.addChangeListener(this);*/
        mainView.onShowFragment();
        //realm.close();
    }
}
