package mvp.it.dekz.notes.base;

public interface ViewHolderPresenter<T extends ViewHolder> {
    void onAttach(T ViewHolder);
    void onDetach();
}
