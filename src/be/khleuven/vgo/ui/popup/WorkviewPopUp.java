package be.khleuven.vgo.ui.popup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import be.khleuven.vgo.domain.Project;
import be.khleuven.vgo.domain.observer.Observer;


public class WorkviewPopUp extends JPopupMenu{
	    public WorkviewPopUp(JList list, Project project, Observer observer){
	    	
			JMenuItem menuitem1 = new JMenuItem("Weergeven in browser");
			JMenuItem menuitem2 = new JMenuItem("Verwijder");

	    	menuitem1.addActionListener(new WorkflowViewActionListener(list,project,observer));
			menuitem2.addActionListener(new WorkflowViewActionListener(list,project,observer));
			
			add(menuitem1);
			add(menuitem2);
			
	    }
}

