package be.khleuven.vgo.domain.observer;

import be.khleuven.vgo.domain.Page;

public interface Observer {
	public void update(String pagename);
	public void update(Page current);
	public void update();
}
