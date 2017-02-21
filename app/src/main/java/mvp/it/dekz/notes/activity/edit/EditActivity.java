package mvp.it.dekz.notes.activity.edit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.it.dekz.notes.R;

public class EditActivity extends AppCompatActivity implements EditView {

    @BindView(R.id.btnSave)Button btnSave;
    @BindView(R.id.etTitle)EditText title;
    @BindView(R.id.etNote)EditText text;

    EditPresenter presenter;
    private String id,titleNote,textNote;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        //use same layout like add activity
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        id = getIntent().getExtras().getString("id");
        Log.d("id",id);
        titleNote = getIntent().getExtras().getString("title");
        title.setText(titleNote);
        textNote = getIntent().getExtras().getString("text");
        text.setText(textNote);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().length()!=0 &&
                        text.getText().length()!=0){
                    presenter.saveChanges(id,title.getText().toString(),
                            text.getText().toString());
                }
            }
        });

        presenter = new EditPresenter();

        onAttachView();
    }

    @Override
    public void onChangeSaved() {
        Toast.makeText(getApplicationContext(), "notes updated", Toast.LENGTH_SHORT).show();
        EditActivity.this.finish();
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    protected void onDestroy(){
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        onDetachView();
        EditActivity.this.finish();
    }
}
