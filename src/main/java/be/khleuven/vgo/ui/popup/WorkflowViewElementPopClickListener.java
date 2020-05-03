package be.khleuven.vgo.ui.popup;

import be.khleuven.vgo.domain.observer.Observer;
import be.khleuven.vgo.ui.pagecontroller.PageController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkflowViewElementPopClickListener extends MouseAdapter {
    private final JList list;
    private final PageController pc;
    private final Observer observer;

    public WorkflowViewElementPopClickListener(JList list, PageController pc,
                                               Observer observer) {
        this.list = list;
        this.pc = pc;
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
        WorkviewElementPopUp menu = new WorkviewElementPopUp(list, pc,
                observer);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    // @Override
    // public void mouseClicked(MouseEvent e) {
    // observer.update(pc.getPage());
    // }
}
