package mvp.it.dekz.notes.activity.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.it.dekz.notes.R;
import mvp.it.dekz.notes.activity.add.AddActivity;
import mvp.it.dekz.notes.activity.edit.EditActivity;
import mvp.it.dekz.notes.model.Note;
import mvp.it.dekz.notes.utils.DateToMillis;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.tvDetailNoteDesc)TextView desc;
    @BindView(R.id.tvDetailNoteTime)TextView time;
    @BindView(R.id.tvDetailNoteTitle)TextView title;

    DetailPresenter detailPresenter;
    private String idNote;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail_note);
        ButterKnife.bind(this);

        detailPresenter = new DetailPresenter();

        onAttachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_edit){
            //open edit note form
            //we will re-use add form
            Intent edit = new Intent(getApplicationContext(), EditActivity.class);
            edit.putExtra("id",idNote);
            edit.putExtra("title",title.getText().toString());
            edit.putExtra("text",desc.getText().toString());
            startActivity(edit);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        onDetachView();
        DetailActivity.this.finish();
    }

    @Override
    public void onDetailShow(Note note) {
        idNote = note.getId();
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
        idNote = getIntent().getExtras().getString("id",null);
        detailPresenter.getDetailNote(idNote);
    }
}
