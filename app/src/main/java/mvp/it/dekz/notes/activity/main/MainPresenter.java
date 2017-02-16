package mvp.it.dekz.notes.activity.main;

import io.realm.Realm;
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
    }

    public void getNotesList(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Note> notes = realm.where(Note.class).findAll();
        mainView.onShowFragment(notes);
        realm.close();
    }
}
