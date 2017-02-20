package mvp.it.dekz.notes.activity.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.it.dekz.notes.R;
import mvp.it.dekz.notes.model.Note;
import mvp.it.dekz.notes.utils.DateToMillis;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.tvDetailNoteDesc)TextView desc;
    @BindView(R.id.tvDetailNoteTime)TextView time;
    @BindView(R.id.tvDetailNoteTitle)TextView title;

    DetailPresenter detailPresenter;
    String id;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail_note);
        ButterKnife.bind(this);

        detailPresenter = new DetailPresenter();

        onAttachView();
    }

    @Override
    public void onBackPressed(){
        onDetachView();
        DetailActivity.this.finish();
    }

    @Override
    public void onDetailShow(Note note) {
        title.setText(note.getTitle());
        desc.setText(note.getNote());
        time.setText(DateToMillis.getRelativeTime(note.getTime()));
    }

    @Override
    public void onAttachView() {
        detailPresenter.onAttach(this);
        getDetail();
    }

    @Override
    public void onDetachView() {
        detailPresenter.onDetach();
    }

    @Override
    protected void onDestroy(){
        onDetachView();
        super.onDestroy();
    }

    private void getDetail(){
        id = getIntent().getExtras().getString("id",null);
        detailPresenter.getDetailNote(id);
    }
}
