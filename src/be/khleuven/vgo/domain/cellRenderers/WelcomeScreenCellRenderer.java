package be.khleuven.vgo.domain.cellRenderers;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class WelcomeScreenCellRenderer extends JLabel implements ListCellRenderer {

	public WelcomeScreenCellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString());

		if (isSelected) {
			setBackground(Color.LIGHT_GRAY);
			list.ensureIndexIsVisible(index);
			setFont(new Font(list.getFont().getFontName(),list.getFont().getStyle(), 25));

			setForeground(Color.BLACK);

		} else {
			setBackground(list.getBackground());
			setFont(new Font(list.getFont().getFontName(), list.getFont()
					.getStyle(), 25));
			setForeground(list.getForeground());
		}
		return this;
	}

}