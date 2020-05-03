package be.khleuven.vgo.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import be.khleuven.vgo.db.DatabaseException;
import be.khleuven.vgo.domain.DomainException;
import be.khleuven.vgo.domain.Page;
import be.khleuven.vgo.domain.cellRenderers.WelcomeScreenCellRenderer;

public class WelcomeScreen extends JFrame implements ActionListener {
	private Container c;
	private JLabel label;
	private JList list;
	private JButton button;

	private String[] data;

	public WelcomeScreen() {
		c = this.getContentPane();
		c.setLayout(null);

		label = new JLabel("Kies uit een van volgende opties om te beginnen:");
		label.setBounds(10, 10, (int) label.getPreferredSize().getWidth(),
				(int) label.getPreferredSize().getHeight());

		data = new String[] { "Maak een nieuwe website",
				"Open een bestaande website" };

		list = new JList(data);
		list.setCellRenderer(new WelcomeScreenCellRenderer());
		list.setBounds(10, 40, 380, 62);
		list.addMouseListener(new MouseListener() {
		int var1 = 0;
		int var2 = 0;
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(list.getSelectedIndex() == 1){
				var1 ++;
				var2 = 0;
				if(var1 == 2){
					bootsActionNa();
					var1 = 0;
				}
				}else if(list.getSelectedIndex() == 0){
					var2 ++;
					var1 = 0;
					if(var2 == 2){
						bootsActionNa();
						var2 = 0;
					}
				}else{
					var1 = 0;
					var2= 0;
				}
			}
		});
		
		button = new JButton("Ga verder");
		button.setBounds(150, 120, 100, 50);
		button.addActionListener(this);

		c.add(label);
		c.add(list);
		c.add(button);


		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setTitle("Graphical Website Builder by Maxime Van den Kerkhof");
		this.setSize(400, 200);
		
		int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().width-this.getSize().getWidth())/2;
		int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().height-this.getSize().getHeight())/2 ;
		this.setLocation(width,  height);
		
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (list.getSelectedIndex() == 1) {
			try {
				ProjectView pv = new ProjectView(this);
			} catch (DomainException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			} catch (DatabaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			this.setVisible(false);
//			Page p = new Page("Project 2","naam","Dit is de titel");
//			try {
//				try {
//					p.display();
//				} catch (DatabaseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} catch (DomainException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}else if(list.getSelectedIndex() == 0){
			NewProjectView npv = new NewProjectView(this);
			this.setVisible(false);
		}
	}
	
	private void bootsActionNa(){
		ActionEvent event = new ActionEvent(button, 1, "Ga verder");
		actionPerformed(event);
	}
}