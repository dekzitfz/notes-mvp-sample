package mvp.it.dekz.notes.activity.main;

import io.realm.RealmResults;
import mvp.it.dekz.notes.base.View;
import mvp.it.dekz.notes.model.Note;

public interface MainView extends View {
    void onShowFragment(RealmResults<Note> notes);
}
