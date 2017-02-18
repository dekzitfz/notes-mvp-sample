package mvp.it.dekz.notes.activity.main.fragment.listnotes;

import io.realm.RealmResults;
import mvp.it.dekz.notes.base.View;
import mvp.it.dekz.notes.model.Note;

public interface ListNotesView extends View {
    void onShowResult(RealmResults<Note> notes);
}
