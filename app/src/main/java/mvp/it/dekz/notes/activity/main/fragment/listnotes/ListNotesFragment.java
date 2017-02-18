package mvp.it.dekz.notes.activity.main.fragment.listnotes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.RealmResults;
import mvp.it.dekz.notes.R;
import mvp.it.dekz.notes.adapter.ListNotesAdapter;
import mvp.it.dekz.notes.model.Note;

public class ListNotesFragment extends Fragment implements ListNotesView {

    @BindView(R.id.rvNotes)RecyclerView rvNotes;
    private View rootView;
    private Unbinder unbinder;

    ListNotesPresenter listNotesPresenter;

    public ListNotesFragment() {
        // Required empty public constructor
    }

    public static ListNotesFragment newInstance() {
        return new ListNotesFragment();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listNotesPresenter = new ListNotesPresenter();
        onAttachView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_notes, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDetachView();
        unbinder.unbind();
    }

    @Override
    public void onShowResult(RealmResults<Note> notes) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        ListNotesAdapter listNotesAdapter = new ListNotesAdapter(notes);
        rvNotes.setLayoutManager(linearLayoutManager);
        rvNotes.setAdapter(listNotesAdapter);
    }

    @Override
    public void onAttachView() {
        listNotesPresenter.onAttach(this);
        showList();
    }

    @Override
    public void onDetachView() {
        listNotesPresenter.onDetach();
    }

    private void showList(){
        listNotesPresenter.showResult();
    }
}
