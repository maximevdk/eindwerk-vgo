package be.khleuven.vgo.ui.popup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import be.khleuven.vgo.domain.observer.Observer;
import be.khleuven.vgo.ui.pagecontroller.PageController;

public class WorkflowViewElementPopClickListener extends MouseAdapter {
	private JList list;
	private PageController pc;
	private Observer observer;

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
