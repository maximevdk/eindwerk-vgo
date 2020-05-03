package be.khleuven.vgo.domain.observer;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers(String pagename);

    void notifyObservers();
}
