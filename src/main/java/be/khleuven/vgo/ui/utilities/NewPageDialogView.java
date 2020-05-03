package be.khleuven.vgo.ui.utilities;

import be.khleuven.vgo.domain.observer.Observer;
import be.khleuven.vgo.domain.observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPageDialogView extends JDialog implements Subject {
    private final Container c;
    private final JLabel label;
    private final JTextField textField;
    private final JButton button;
    private Observer observer;

    public NewPageDialogView(String title, final JFrame frame, boolean bool) {
        super(frame, title, bool);
        this.setSize(200, 170);
        this.setLocation(200, 190);

        c = this.getContentPane();
        label = new JLabel("Hoe heet de nieuwe pagina?");
        textField = new JTextField();
        button = new JButton("Aanmaken");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                notifyObservers(textField.getText());
                setVisible(false);
            }
        });


        c.setLayout(null);
        label.setBounds(10, 10, 200, 20);
        textField.setBounds(10, 40, 190, 20);
        button.setBounds(40, 70, 100, 50);

        c.add(label);
        c.add(textField);
        c.add(button);
        //this.setVisible(true);

    }

    @Override
    public void registerObserver(Observer o) {
        this.observer = o;

    }

    @Override
    public void removeObserver(Observer o) {
        this.observer = null;

    }

    @Override
    public void notifyObservers(String pagename) {
        if (observer != null) {
            observer.update(pagename);
        } else {
            System.out.println("oeps");
        }
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub

    }

}
