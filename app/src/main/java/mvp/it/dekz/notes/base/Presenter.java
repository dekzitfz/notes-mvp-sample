package mvp.it.dekz.notes.base;

public interface Presenter<T extends View> {
    void onAttach(T View);
    void onDetach();
}
