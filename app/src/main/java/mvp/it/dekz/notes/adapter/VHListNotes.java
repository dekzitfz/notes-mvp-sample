package mvp.it.dekz.notes.adapter;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.it.dekz.notes.R;
import mvp.it.dekz.notes.model.Note;
import mvp.it.dekz.notes.utils.DateToMillis;

public class VHListNotes extends RecyclerView.ViewHolder {

    @BindView(R.id.tvRowNoteTime)TextView time;
    @BindView(R.id.tvRowNoteTitle)TextView title;
    @BindView(R.id.tvOption)TextView option;

    public VHListNotes(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Note note){
        title.setText(note.getTitle());
        time.setText(DateToMillis.getRelativeTime(note.getTime()));

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(option.getContext(),option);
                popupMenu.inflate(R.menu.list_option);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.listOptDelete:
                                Toast.makeText(option.getContext(), "delete", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.listOptEdit:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
