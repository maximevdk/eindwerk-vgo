package be.khleuven.vgo.domain.cellRenderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class PagesViewCellRenderer extends JLabel implements ListCellRenderer{
	public PagesViewCellRenderer() {
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
		
		this.setBounds(10, 10, 400, 400);
		return this;

		
	}
}
