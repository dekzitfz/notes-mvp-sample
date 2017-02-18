package mvp.it.dekz.notes.activity.main.fragment.listnotes;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import mvp.it.dekz.notes.base.Presenter;
import mvp.it.dekz.notes.base.ViewHolder;
import mvp.it.dekz.notes.base.ViewHolderPresenter;
import mvp.it.dekz.notes.model.Note;

public class ListNotesPresenter implements Presenter<ListNotesView>,ViewHolderPresenter<ViewHolder> {

    private ListNotesView listNotesView;
    private ViewHolder viewHolder;
    private RealmResults<Note> notes;
    private Realm realm;

    @Override
    public void onAttach(ListNotesView View) {
        listNotesView = View;
    }

    @Override
    public void onAttach(ViewHolder ViewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    public void onDetach() {
        listNotesView = null;
        this.viewHolder = null;
    }

    public void showResult(){
        realm = Realm.getDefaultInstance();
        notes = realm.where(Note.class).findAll();
        listNotesView.onShowResult(notes);
        //realm.close();
    }

    public void deleteData(final int pos){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Note> notes = realm.where(Note.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realmDel) {
                notes.get(pos).deleteFromRealm();
            }
        });

        showResult();
    }
}
