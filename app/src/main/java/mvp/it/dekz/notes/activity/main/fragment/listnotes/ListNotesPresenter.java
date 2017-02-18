package mvp.it.dekz.notes.activity.main.fragment.listnotes;

import io.realm.Realm;
import io.realm.RealmResults;
import mvp.it.dekz.notes.base.Presenter;
import mvp.it.dekz.notes.model.Note;

public class ListNotesPresenter implements Presenter<ListNotesView> {

    private ListNotesView listNotesView;
    private RealmResults<Note> notes;

    @Override
    public void onAttach(ListNotesView View) {
        listNotesView = View;
    }

    @Override
    public void onDetach() {
        listNotesView = null;
    }

    public void showResult(){
        Realm realm = Realm.getDefaultInstance();
        notes = realm.where(Note.class).findAll();
        listNotesView.onShowResult(notes);
        //realm.close();
    }
}
