package be.khleuven.vgo.ui.popup;

import be.khleuven.vgo.domain.Project;
import be.khleuven.vgo.domain.observer.Observer;

import javax.swing.*;


public class WorkviewPopUp extends JPopupMenu {
    public WorkviewPopUp(JList list, Project project, Observer observer) {

        JMenuItem menuitem1 = new JMenuItem("Weergeven in browser");
        JMenuItem menuitem2 = new JMenuItem("Verwijder");

        menuitem1.addActionListener(new WorkflowViewActionListener(list, project, observer));
        menuitem2.addActionListener(new WorkflowViewActionListener(list, project, observer));

        add(menuitem1);
        add(menuitem2);

    }
}

