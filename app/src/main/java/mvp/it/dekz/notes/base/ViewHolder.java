package mvp.it.dekz.notes.base;

import mvp.it.dekz.notes.model.Note;

public interface ViewHolder {
    void bind(Note note, int pos);
}
