package mvp.it.dekz.notes.activity.edit;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;
import mvp.it.dekz.notes.base.Presenter;
import mvp.it.dekz.notes.model.Note;

public class EditPresenter implements Presenter<EditView> {

    private EditView View;

    @Override
    public void onAttach(EditView View) {
        this.View = View;
    }

    @Override
    public void onDetach() {
        this.View = null;
    }

    public void saveChanges(final String id, final String title, final String text){
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm trrealm) {
                Note note = trrealm.where(Note.class).equalTo("id", id).findFirst();
                note.setNote(text);
                note.setTitle(title);
                note.setTime(getDate());
                View.onChangeSaved();
            }
        });

        /*realm.beginTransaction();
        Note note = realm.where(Note.class).equalTo("id",id).findFirst();
        note.setNote(text);
        note.setTitle(title);
        note.setTime(getDate());
        realm.commitTransaction();
        realm.close();*/
        //View.onChangeSaved();
    }

    private String getDate(){
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return desiredFormat.format(c.getTime());
    }

    private String generateID(){
        return UUID.randomUUID().toString();
    }
}
