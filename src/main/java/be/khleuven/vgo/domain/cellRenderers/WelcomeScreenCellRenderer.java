package be.khleuven.vgo.domain.cellRenderers;

import javax.swing.*;
import java.awt.*;

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
            setFont(new Font(list.getFont().getFontName(), list.getFont().getStyle(), 25));

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
