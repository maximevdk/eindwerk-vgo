package be.khleuven.vgo.ui;

import be.khleuven.vgo.db.DatabaseException;
import be.khleuven.vgo.db.ProjectLoader;
import be.khleuven.vgo.domain.DomainException;
import be.khleuven.vgo.domain.Project;
import be.khleuven.vgo.domain.cellRenderers.ProjectViewCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ProjectView extends JFrame {
    private final Container c;

    private final JLabel uitlegLabel;
	private final JLabel siteNameLabel;
	private final JLabel creationDateLabel;
    private final JList projectsList;

    private final JScrollPane scrollpane;

    private final JButton button;

    public ProjectView(final JFrame frame) throws DomainException,
            DatabaseException {
        c = this.getContentPane();
        c.setLayout(null);

        uitlegLabel = new JLabel(
                "Kies hier uit een van u voorgaande projecten.");
        siteNameLabel = new JLabel("Naam website");
        creationDateLabel = new JLabel("Aanmaak datum en tijd");

        ProjectLoader pl = new ProjectLoader();
        final List<Project> data;

        data = pl.getAllProjects();

        projectsList = new JList(data.toArray());
        projectsList.setCellRenderer(new ProjectViewCellRenderer());

        scrollpane = new JScrollPane(projectsList);

        button = new JButton("Starten");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (projectsList.getSelectedIndex() >= 0) {
                    WorkflowView wfv = new WorkflowView((Project) projectsList
                            .getSelectedValue());
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Selecteer een project.");
                }
            }
        });

        c.add(uitlegLabel);
        uitlegLabel.setBounds(100, 50, (int) uitlegLabel.getPreferredSize()
                .getWidth(), (int) uitlegLabel.getPreferredSize().getHeight());

        c.add(siteNameLabel);
        siteNameLabel
                .setBounds(10, 150, (int) siteNameLabel.getPreferredSize()
                        .getWidth(), (int) siteNameLabel.getPreferredSize()
                        .getHeight());
        c.add(creationDateLabel);
        creationDateLabel.setBounds((int) (590 - creationDateLabel
                .getPreferredSize().getWidth()), 150, (int) creationDateLabel
                .getPreferredSize().getWidth(), (int) creationDateLabel
                .getPreferredSize().getHeight());

        c.add(scrollpane);
        scrollpane.setBounds(10, 170, 580, 300);

        c.add(button);
        button.setBounds(300 - button.getPreferredSize().width,
                500 - button.getPreferredSize().height, 100, 50);
        this.setTitle("My projects");
        this.setSize(600, 600);

        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().getWidth()) / 2;
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().getHeight()) / 2;
        this.setLocation(width, height);


        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
            }
        });

        this.setVisible(true);

    }

}
