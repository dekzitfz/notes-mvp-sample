package mvp.it.dekz.notes.activity.detail;

import mvp.it.dekz.notes.base.View;
import mvp.it.dekz.notes.model.Note;

public interface DetailView extends View {
    void onDetailShow(Note note);
}
