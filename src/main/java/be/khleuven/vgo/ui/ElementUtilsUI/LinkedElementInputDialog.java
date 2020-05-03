package be.khleuven.vgo.ui.ElementUtilsUI;

import be.khleuven.vgo.domain.Project;
import be.khleuven.vgo.domain.style.Color;
import be.khleuven.vgo.domain.style.FontStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LinkedElementInputDialog extends JDialog implements ActionListener {

    private final JCheckBox stijlCheckbox;
    private final JPanel stijlpanel;
	private final JPanel inhoudpanel;
    private final JComboBox plaatsbox;
	private final JComboBox pagesbox;
	private final JComboBox fontsizebox;
	private final JComboBox textcolorbox;
	private final JComboBox backcolorbox;
	private final JComboBox fontstylebox;
    private final JTextArea inhoudarea;
    private final JButton okbutton;
    private boolean opokgeklickt = false;

    private final Project p;

    public LinkedElementInputDialog(JFrame frame, String titel, boolean bool,
                                    Project p) {
        super(frame, titel, bool);
        this.p = p;

        Container c = this.getContentPane();
        c.setLayout(new GridLayout(1, 2));
        // pannels
        inhoudpanel = new JPanel(); // hier moet STIJL mee in!
        stijlpanel = new JPanel();

        // labels
        JLabel inhoudlabel = new JLabel("Inhoud:");
        JLabel plaatslabel = new JLabel("Plaats (binnen de body):");
        JLabel linklabel = new JLabel("Deze pagina verwijst naar:");

        JLabel fontsize = new JLabel("Fontsize:");
        JLabel textcolor = new JLabel("TextColor:");
        JLabel backgroundcolor = new JLabel("BackGround Color:");
        JLabel fontstyle = new JLabel("FontStyle:");

        // checkbox
        stijlCheckbox = new JCheckBox("Style");
        stijlCheckbox.addActionListener(this);

        // JComboBoxen
        ArrayList<Integer> tmp1 = new ArrayList<Integer>();
        for (int i = 6; i < 20; i++) {
            tmp1.add(i);
        }
        plaatsbox = new JComboBox(tmp1.toArray());
        plaatsbox.setEditable(true);
        plaatsbox.setSelectedItem(6);

        pagesbox = new JComboBox(p.getProjectPages().toArray());
        // pagesbox.addItem("url");
        pagesbox.setEditable(true);
        // pagesbox.sete

        ArrayList<String> tmp = new ArrayList<String>();
        tmp.add("12em");
        tmp.add("14em");
        tmp.add("16em");
        tmp.add("18em");
        tmp.add("20em");
        tmp.add("24em");

        fontsizebox = new JComboBox(tmp.toArray());
        fontsizebox.setEditable(true);
        fontsizebox.setSelectedItem("12em");

        textcolorbox = new JComboBox(Color.values());
        textcolorbox.setSelectedItem(Color.BLACK);

        backcolorbox = new JComboBox(Color.values());
        backcolorbox.setSelectedItem(Color.WHITE);

        fontstylebox = new JComboBox(FontStyle.values());
        fontstylebox.setSelectedItem(FontStyle.NORMAL);

        // textbox
        inhoudarea = new JTextArea("Hier komt de inhoud...");
        Dimension d = new Dimension(150, 50);
        inhoudarea.setPreferredSize(d);

        // toevoegen aan inhoud panel
        inhoudpanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inhoudpanel.add(inhoudlabel);
        inhoudpanel.add(inhoudarea);
        //
        inhoudpanel.add(linklabel);
        inhoudpanel.add(pagesbox);
        //
        inhoudpanel.add(plaatslabel);
        inhoudpanel.add(plaatsbox);
        //
        inhoudpanel.add(stijlCheckbox);

        // toevoegen aan stijlpanel;
        stijlpanel.add(fontsize);
        stijlpanel.add(fontsizebox);

        stijlpanel.add(textcolor);
        stijlpanel.add(textcolorbox);

        stijlpanel.add(backgroundcolor);
        stijlpanel.add(backcolorbox);

        stijlpanel.add(fontstyle);
        stijlpanel.add(fontstylebox);

        for (Component component : stijlpanel.getComponents()) {
            component.setEnabled(false);
        }
        okbutton = new JButton("Add");
        okbutton.addActionListener(this);

        inhoudpanel.add(okbutton);

        c.add(inhoudpanel);
        c.add(stijlpanel);

        setLocationRelativeTo(frame);
        this.setResizable(false);
        this.setSize(320, 250);

        this.setVisible(true);

    }

    public String getInhoud() {
        return inhoudarea.getText();
    }

    public String getSelectedPage() {

        if (this.p.getProjectPages().contains(pagesbox.getSelectedItem())) {
            return pagesbox.getSelectedItem() + ".html";
        }
        return (String) pagesbox.getSelectedItem();
    }

    public boolean getStijlSelected() {
        return this.stijlCheckbox.isSelected();
    }

    public String getFontSize() {
        return (String) fontsizebox.getSelectedItem();
    }

    public Color getTextColor() {
        return (Color) textcolorbox.getSelectedItem();
    }

    public Color getBackGroundColor() {
        return (Color) backcolorbox.getSelectedItem();
    }

    public FontStyle getFontStyle() {
        return (FontStyle) fontstylebox.getSelectedItem();
    }

    public int getPlaats() {
        return (Integer) this.plaatsbox.getSelectedItem();
    }

    public boolean getIsErGeklikt() {
        return opokgeklickt;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.stijlCheckbox) {
            if (this.stijlCheckbox.isSelected()) {
                for (Component component : stijlpanel.getComponents()) {
                    component.setEnabled(true);
                }
            } else {
                for (Component component : stijlpanel.getComponents()) {
                    component.setEnabled(false);
                }
            }
        } else if (e.getSource() == okbutton) {
            opokgeklickt = true;
            setVisible(false);
            dispose();
        }

    }

}
