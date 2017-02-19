package mvp.it.dekz.notes.activity.detail;

import io.realm.Realm;
import mvp.it.dekz.notes.base.Presenter;
import mvp.it.dekz.notes.model.Note;

public class DetailPresenter implements Presenter<DetailView>{

    private DetailView detailView;

    @Override
    public void onAttach(DetailView View) {
        detailView = View;
    }

    @Override
    public void onDetach() {
        detailView = null;
    }

    public void getDetailNote(String id){
        Realm realm = Realm.getDefaultInstance();
        Note note = realm.where(Note.class).equalTo("id",id).findFirst();
        detailView.onDetailShow(note);
    }
}
