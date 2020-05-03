package be.khleuven.vgo.domain.cellRenderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class ProjectViewCellRenderer extends JLabel implements ListCellRenderer {

	public ProjectViewCellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString());

		if (isSelected) {
			setBackground(Color.LIGHT_GRAY);
			list.ensureIndexIsVisible(index);
			setFont(new Font(list.getFont().getFontName(), list.getFont()
					.getStyle(), 18));

			setForeground(Color.BLACK);

		} else {
			setBackground(list.getBackground());
			setFont(new Font(list.getFont().getFontName(), list.getFont()
					.getStyle(), 18));
			setForeground(list.getForeground());
		}

		/*
		 * Volgende code zorgt ervoor dat de tekst in de lijst mooi op zijn
		 * plaats staat
		 */
		String[] tmp = this.getText().split("-");
		int temp = 68; // dit is het aantal karakters waar de lengte van het
						// eerste deel en het tweede deel van de string zullen
						// worden vanafgetrokken
		temp = temp - tmp[0].length(); //karakter voor de "-"
		temp = temp - tmp[1].length(); //karakter na de "-"
		String nieuw = tmp[0]; // de uitkomst
		for (int i = 0; i < temp; i++) {
			nieuw += " "; // de uitkomst vullen met spaties
		}
		nieuw += tmp[1]; // tweede deel van de uitkomst bijplakken
		this.setText(nieuw); // de uitkomst terug in de lijst plaatsen
		return this;
	}

}