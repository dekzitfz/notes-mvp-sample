package mvp.it.dekz.notes.activity.add;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.it.dekz.notes.R;

public class AddActivity extends AppCompatActivity implements AddView {

    AddPresenter addPresenter;
    @BindView(R.id.btnSave)Button btnSave;
    @BindView(R.id.etTitle)EditText title;
    @BindView(R.id.etNote)EditText text;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        addPresenter = new AddPresenter();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().length()!=0 && text.getText().length()!=0){
                    addPresenter.saveNote(title.getText().toString(),
                            text.getText().toString());
                }
            }
        });

        onAttachView();
    }

    @Override
    public void onBackPressed(){
        onDetachView();
        AddActivity.this.finish();
    }

    @Override
    protected void onDestroy(){
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void onNoteSaved() {
        Toast.makeText(getApplicationContext(), "notes saved", Toast.LENGTH_SHORT).show();
        AddActivity.this.finish();
    }

    @Override
    public void onAttachView() {
        addPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        addPresenter.onDetach();
    }
}
