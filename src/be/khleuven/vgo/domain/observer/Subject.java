package be.khleuven.vgo.domain.observer;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(String pagename);
	public void notifyObservers();
}
