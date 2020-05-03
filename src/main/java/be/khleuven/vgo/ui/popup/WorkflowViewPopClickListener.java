package be.khleuven.vgo.ui.popup;

import be.khleuven.vgo.domain.Page;
import be.khleuven.vgo.domain.Project;
import be.khleuven.vgo.domain.observer.Observer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkflowViewPopClickListener extends MouseAdapter {
    private final JList list;
    private final Project project;
    private final Observer observer;

    public WorkflowViewPopClickListener(JList list, Project project, Observer observer) {
        this.list = list;
        this.project = project;
        this.observer = observer;
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        WorkviewPopUp menu = new WorkviewPopUp(list, project, observer);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        observer.update((Page) list.getSelectedValue());
    }
}
