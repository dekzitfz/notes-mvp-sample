package mvp.it.dekz.notes.activity.add;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;
import mvp.it.dekz.notes.base.Presenter;
import mvp.it.dekz.notes.model.Note;

public class AddPresenter implements Presenter<AddView> {

    private AddView addView;

    @Override
    public void onAttach(AddView View) {
        addView = View;
    }

    @Override
    public void onDetach() {
        addView = null;
    }

    public void saveNote(String title,String text){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Note note = realm.createObject(Note.class,generateID());
        note.setNote(text);
        note.setTitle(title);
        note.setTime(getDate());
        realm.commitTransaction();
        realm.close();
        addView.onNoteSaved();
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
