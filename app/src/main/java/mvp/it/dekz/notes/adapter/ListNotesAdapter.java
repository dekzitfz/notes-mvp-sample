package mvp.it.dekz.notes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.realm.RealmResults;
import mvp.it.dekz.notes.R;
import mvp.it.dekz.notes.activity.main.fragment.listnotes.ListNotesPresenter;
import mvp.it.dekz.notes.model.Note;

public class ListNotesAdapter extends RecyclerView.Adapter {

    private RealmResults<Note> notes;
    private ListNotesPresenter presenter;

    public ListNotesAdapter(RealmResults<Note> notes,ListNotesPresenter presenter) {
        this.notes = notes;
        this.presenter=presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHListNotes(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_note, parent, false),presenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VHListNotes)holder).bind(notes.get(position),position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
