package be.khleuven.vgo.ui;

import be.khleuven.vgo.db.DatabaseException;
import be.khleuven.vgo.domain.DomainException;
import be.khleuven.vgo.domain.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewProjectView extends JFrame {

    private final Container c;

    private final JLabel label;
    private final JTextField inputveld;
    private final JButton button;

    public NewProjectView(final JFrame frame) {
        c = this.getContentPane();
        c.setLayout(null);

        label = new JLabel("Geef hier de projectnaam in");
        inputveld = new JTextField();
        button = new JButton("Aanmaken");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!inputveld.getText().equals("")) {
                    try {
                        Project p = new Project(inputveld.getText());
                        WorkflowView wfv = new WorkflowView(p);
                        close(frame);
                    } catch (DomainException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (DatabaseException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vul een project-naam in");
                }

            }
        });

        c.add(label);
        c.add(inputveld);
        c.add(button);

        label.setBounds(60, 10, label.getPreferredSize().width, label.getPreferredSize().height);
        inputveld.setBounds(60, label.getPreferredSize().height + 20, label.getPreferredSize().width, inputveld.getPreferredSize().height);
        button.setBounds(100, 80, 100, 50);

        this.setTitle("Nieuw project aanmaken");
        this.setSize(300, 160);

        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().getWidth()) / 2;
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().getHeight()) / 2;
        this.setLocation(width, height);


        this.setVisible(true);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
            }
        });


    }

    public void close(JFrame frame) {
        this.setVisible(false);
    }
}
