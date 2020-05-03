package be.khleuven.vgo.ui.popup;

import be.khleuven.vgo.db.DatabaseException;
import be.khleuven.vgo.domain.DomainException;
import be.khleuven.vgo.domain.Page;
import be.khleuven.vgo.domain.Project;
import be.khleuven.vgo.domain.observer.Observer;
import be.khleuven.vgo.domain.observer.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkflowViewActionListener implements ActionListener, Subject {

    private final JList list;
    private final Project project;
    private Observer observer;

    public WorkflowViewActionListener(JList list, Project project, Observer observer) {
        this.list = list;
        this.project = project;
        this.registerObserver(observer);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Page p;
        if (event.getActionCommand().equals("Weergeven in browser")) {
            try {
                p = (Page) list.getSelectedValue();
                p.display();
            } catch (DomainException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (event.getActionCommand().equals("Verwijder")) {
            p = (Page) list.getSelectedValue();
            try {
                project.removePage(p);
                notifyObservers();
            } catch (DomainException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    @Override
    public void registerObserver(Observer o) {
        this.observer = o;

    }

    @Override
    public void removeObserver(Observer o) {
        this.observer = null;

    }

    @Override
    public void notifyObservers(String pagename) {

    }

    @Override
    public void notifyObservers() {
        observer.update();

    }

}
