package mvp.it.dekz.notes.activity.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import mvp.it.dekz.notes.R;
import mvp.it.dekz.notes.activity.add.AddActivity;
import mvp.it.dekz.notes.activity.main.fragment.listnotes.ListNotesFragment;
import mvp.it.dekz.notes.model.Note;

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter mainPresenter;
    @BindView(R.id.fabAdd)FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO add data
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });

        mainPresenter = new MainPresenter();
        onAttachView();
    }

    @Override
    public void onAttachView() {
        mainPresenter.onAttach(this);
        //get data from realm
        mainPresenter.getNotesList();
    }

    @Override
    public void onDetachView() {
        mainPresenter.onDetach();
    }

    @Override
    protected void onDestroy(){
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void onShowFragment() {
        Fragment fragment = ListNotesFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commit();
    }
}
