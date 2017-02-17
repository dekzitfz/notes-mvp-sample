package mvp.it.dekz.notes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.it.dekz.notes.R;
import mvp.it.dekz.notes.model.Note;
import mvp.it.dekz.notes.utils.DateToMillis;

public class VHListNotes extends RecyclerView.ViewHolder {

    @BindView(R.id.tvRowNoteTime)TextView time;
    @BindView(R.id.tvRowNoteTitle)TextView title;


    public VHListNotes(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Note note){
        title.setText(note.getTitle());
        time.setText(DateToMillis.getRelativeTime(note.getTime()));
    }
}
