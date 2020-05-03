package be.khleuven.vgo.domain.observer;

import be.khleuven.vgo.domain.Page;

public interface Observer {
    void update(String pagename);

    void update(Page current);

    void update();
}
