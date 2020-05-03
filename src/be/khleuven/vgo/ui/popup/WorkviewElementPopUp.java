package be.khleuven.vgo.ui.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import be.khleuven.vgo.domain.elements.Element;
import be.khleuven.vgo.domain.observer.Observer;
import be.khleuven.vgo.ui.pagecontroller.PageController;

public class WorkviewElementPopUp extends JPopupMenu {
	public WorkviewElementPopUp(final JList list, final PageController pc,
			final Observer observer) {

		JMenuItem menuitem1 = new JMenuItem("Verwijder");

		menuitem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				List<Element> elementen = pc.getElements();
				Element element = null;
				for (Element e : elementen) {
					if (e.equals(list.getSelectedValue())) {
						element = e;
					}
				}

				pc.RemoveElementFromPage((element));
				observer.update(pc.getPage());
			}
		});


		add(menuitem1);

	}
}
