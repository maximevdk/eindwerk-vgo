package be.khleuven.vgo.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import be.khleuven.vgo.db.DatabaseException;
import be.khleuven.vgo.domain.Page;
import be.khleuven.vgo.domain.Project;
import be.khleuven.vgo.domain.cellRenderers.PagesViewCellRenderer;
import be.khleuven.vgo.domain.observer.Observer;
import be.khleuven.vgo.domain.style.Style;
import be.khleuven.vgo.ui.ElementUtilsUI.ElementInputDialog;
import be.khleuven.vgo.ui.ElementUtilsUI.LinkedElementInputDialog;
import be.khleuven.vgo.ui.pagecontroller.PageController;
import be.khleuven.vgo.ui.popup.WorkflowViewElementPopClickListener;
import be.khleuven.vgo.ui.popup.WorkflowViewPopClickListener;
import be.khleuven.vgo.ui.utilities.NewPageDialogView;

public class WorkflowView extends JFrame implements ActionListener, Observer {

	private Container c;
	private JMenuBar menubar;
	private JPanel pages, pageview;


	// COMPONENTS PAGES
	private JList pagesList;

	private JButton button;

	private JMenuItem editTitle, addLink, addText, addHeader;

	private Project project;

	// COMPONENTS VOOR PAGEVIEW

	private JLabel projectNameLabel, projectTitleLabel, projectNavigationLabel,
			projectHeddingLabel, projectParagraphLabel;
	private JTextField titleTextfield;
	private JList navigationList, titlesList, paragraphsList;

	private Page current;

	private JScrollPane navScroll, headerScroll, paragraphScroll;

	// OVERIGE BENODIGHEDEN
	private PageController pc;

	private Map<Page, PageController> controllerperpage;

	boolean geupdate = false;

	public WorkflowView(Project p) {
		c = this.getContentPane();
		c.setLayout(null);

		this.project = p;

		// DE MAP INITIALISEREN
		ininitializeMap();
		// ///////////////

		menubar = new JMenuBar();
		JMenu gwb = new JMenu("GWB");
		JMenuItem about = new JMenuItem("About");
		JMenuItem close = new JMenuItem("Close");
		close.addActionListener(this);
		// gwb.add(about);
		gwb.add(close);

		JMenu file = new JMenu("File");
		editTitle = new JMenuItem("Edit title");
		file.add(editTitle);


		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(this);

		JMenuItem saveall = new JMenuItem("Save all");
		saveall.addActionListener(this);
		file.add(save);
		file.add(saveall);

		JMenu edit = new JMenu("Edit");
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem delete = new JMenuItem("Delete");
		edit.add(undo);
		edit.add(redo);
		edit.add(delete);

		JMenu add = new JMenu("Add Component");
		addLink = new JMenuItem("Add link");
		addText = new JMenuItem("Add text");
		addHeader = new JMenuItem("Add header");
		add.add(addLink);
		add.add(addText);
		add.add(addHeader);

		addLink.addActionListener(this);
		editTitle.addActionListener(this);
		addText.addActionListener(this);
		addHeader.addActionListener(this);

		menubar.add(gwb);
		menubar.add(file);
		// menubar.add(edit);
		menubar.add(add);

		// ******
		// p.createPage("Pagename","Page title");
		// p.createPage("Pagename_1","Page title_1");
		// p.createPage("Pagename_2","Page title_2");
		// p.createPage("Pagename_3","Page title_2");
		// *****

		pages = new JPanel();
		pages.setLayout(null);
		pagesList = new JList(project.getProjectPages().toArray());
		pagesList.setCellRenderer(new PagesViewCellRenderer());
		pagesList.setBounds(10, 70, 250, (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() - 200);

		// WorkflowViewPopClickListener listner = new
		// WorkflowViewPopClickListener(pagesList,project,this);

		pagesList.addMouseListener(new WorkflowViewPopClickListener(pagesList,
				project, this));

		pages.add(pagesList);

		button = new JButton("+");
		button.addActionListener(this);
		button.setBounds(10, 10, 235, 50);

		pages.add(button);
		pages.setBounds(0, 0, 250, (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight());
		c.add(pages);

		//
		// PAGE VIEW
		//

		// JPANEL
		pageview = new JPanel();
		pageview.setLayout(null);
		pageview.setBounds(250, 0,
				Toolkit.getDefaultToolkit().getScreenSize().width - 250,
				Toolkit.getDefaultToolkit().getScreenSize().height);

		// LABELS
		// projectNameLabel = new JLabel(project.getShortProjectname());

		projectTitleLabel = new JLabel("Selecteer een pagina");
		projectTitleLabel.setFont(new Font("Zapf Dingbats", 1, 40));
		projectTitleLabel.setBounds(
				(pageview.getPreferredSize().width / 2)
						- (projectTitleLabel.getPreferredSize().width / 2),
				(pageview.getPreferredSize().height / 2)
						- (projectTitleLabel.getPreferredSize().height),
				projectTitleLabel.getPreferredSize().width,
				projectTitleLabel.getPreferredSize().height);

		// TEXTFIELD
		titleTextfield = new JTextField();

		pageview.add(projectTitleLabel);

		c.add(pageview);

		String[] projectName = p.getShortProjectname().split("-");
		this.setTitle(projectName[0]);
		this.setJMenuBar(menubar);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);

		this.setVisible(true);

	}

	public Project getProject() {
		return project;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("+")) {
			NewPageDialogView npdv = new NewPageDialogView("Nieuwe pagina",
					this, true);
			npdv.registerObserver(this);
			npdv.setVisible(true);
		} else if (e.getActionCommand().equals("Save")) {
			try {
				this.current.writePage();
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		} else if (e.getActionCommand().equals("Save all")) {
			for (Page p : project.getProjectPages()) {
				try {
					p.writePage();
				} catch (DatabaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		} else if (e.getActionCommand().equals("Close")) {
			this.setVisible(false);
		} else if (e.getSource() == this.addText) {
			if (this.current != null) {
			ElementInputDialog dialog = new ElementInputDialog(this,
					"Voeg een nieuwe paragraaf toe", true);

			if (dialog.getIsErGeklikt()) {

				if (dialog.getStijlSelected()) {
					Style stijl = new Style(dialog.getFontSize(),
							dialog.getTextColor(), dialog.getBackGroundColor(),
							dialog.getFontStyle());
					pc.addElementToPage("p", dialog.getInhoud(), stijl,
							dialog.getPlaats());
				} else {
					pc.addElementToPage("p", dialog.getInhoud(),
							dialog.getPlaats());
				}
				geupdate = true;
				this.update(current);
			}
			} else {
				JOptionPane.showMessageDialog(null,
						"Je moet eerst een pagina selecteren!");
			}

		} else if (e.getSource() == this.addHeader) {
			if (current != null) {
			ElementInputDialog dialog = new ElementInputDialog(this,
					"Voeg een nieuwe header toe", true);
			if (dialog.getIsErGeklikt()) {
				if (dialog.getStijlSelected()) {
					Style stijl = new Style(dialog.getFontSize(),
							dialog.getTextColor(), dialog.getBackGroundColor(),
							dialog.getFontStyle());
					pc.addElementToPage("h1", dialog.getInhoud(), stijl,
							dialog.getPlaats());
				} else {
					pc.addElementToPage("h1", dialog.getInhoud(),
							dialog.getPlaats());
				}
				geupdate = true;
				this.update(current);
			}
			} else {
				JOptionPane.showMessageDialog(null,
						"Je moet eerst een pagina selecteren!");
			}
		} else if (e.getSource() == this.editTitle) {
			if (this.current != null) {
			this.current.updateTitle(JOptionPane
					.showInputDialog("Geeft een nieuwe titel voor de pagina"));
			} else {
				JOptionPane.showMessageDialog(null,
						"Je moet eerst een pagina selecteren!");
			}
		} else if (e.getSource() == this.addLink) {
			if (this.current != null) {

				LinkedElementInputDialog dialog = new LinkedElementInputDialog(
						this, "Voeg een nieuwe header toe", true,
						this.getProject());
				if (dialog.getIsErGeklikt()) {
					if (dialog.getStijlSelected()) {
						Style stijl = new Style(dialog.getFontSize(),
								dialog.getTextColor(),
								dialog.getBackGroundColor(),
								dialog.getFontStyle());
						pc.addLinkedElementToPage("a", dialog.getInhoud(),
								dialog.getSelectedPage(), stijl,
								dialog.getPlaats());
					} else {
						pc.addLinkedElementToPage("a", dialog.getInhoud(),
								dialog.getSelectedPage(), dialog.getPlaats());
					}
					geupdate = true;
					this.update(current);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Je moet eerst een pagina selecteren!");
			}
		}

	}

	@Override
	public void update(String pagename) {
		project.createPage(pagename, "Edit this title");
		// project.getProjectPages();
		ininitializeMap();
		pages.remove(pagesList);
		pagesList = new JList(project.getProjectPages().toArray());
		pagesList.setCellRenderer(new PagesViewCellRenderer());
		pagesList.addMouseListener(new WorkflowViewPopClickListener(pagesList,
				project, this));

		pages.add(pagesList);

		pagesList.setBounds(10, 70, 250, (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() - 200);
	}

	@Override
	public void update() {
		project.getProjectPages();

		pages.remove(pagesList);
		pagesList = new JList(project.getProjectPages().toArray());
		pagesList.setCellRenderer(new PagesViewCellRenderer());
		pagesList.addMouseListener(new WorkflowViewPopClickListener(pagesList,
				project, this));

		pages.add(pagesList);

		pagesList.setBounds(10, 70, 250, (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() - 200);
	}

	public void update(Page current) {
		// controlleren of de pagina waarop geklikt is, niet de pagina is die al
		// geselecteerd staat
		if (this.current == null || !this.current.equals(current) || geupdate) {
			geupdate = false;

			// PageController initialiseren
			pc = controllerperpage.get(current);
			// //////////////////////

			// De de huidige pagina zetten in de PAGECONTROLLER
			// pc.setPage(this.current);
			// ////////////////////////

			pageview.removeAll();
			// indien dit slaagt is het een nieuwe pagina

			// de current page zetten
			this.current = current;
			// /////////////////////////

			// //////////////////////////

			// De titel van de pagina
			for(Component p : pageview.getComponents()){
				if (p.equals(projectTitleLabel)) {
					pageview.remove(projectTitleLabel);
				}
			}
			
			projectTitleLabel.setText(current.toString());
			projectTitleLabel.setBounds((pageview.getWidth() / 2) - 100, 10,
					projectTitleLabel.getPreferredSize().width,
					projectTitleLabel.getPreferredSize().height);
			pageview.add(projectTitleLabel);
			// //////////////////////////

			// drie JPanels voor de drie onderdelen van de site
			JPanel navigationpane = new JPanel();
			JPanel headerpane = new JPanel();
			JPanel paragraphpane = new JPanel();
			// //////////////////////////

			// De content voor in navigationpane

			// pageview.remove(navigationpane);
			navigationpane.removeAll();

			navigationpane.setLayout(null);
			projectNavigationLabel = new JLabel("Project navigation");
			projectNavigationLabel.setBounds(10, 0, 200, 15);

			// navigationlist
			navigationList = new JList(pc.getNviagtionElements().toArray());
			navigationList.setCellRenderer(new PagesViewCellRenderer());
			navigationList.setBounds(20, 20, 600, 180);
			navigationList
					.addMouseListener(new WorkflowViewElementPopClickListener(
							navigationList, pc, this));
			// //////////

			navigationpane.add(projectNavigationLabel);
			navigationpane.add(navigationList);
			navigationpane.setBounds(0, 120, 600, 190);
			// /////////////////////////

			// headerpane
			headerpane.removeAll();

			headerpane.setLayout(null);
			projectHeddingLabel = new JLabel("Pages titles");
			projectHeddingLabel.setBounds(10, 0, 200, 15);

			// titleslist
			titlesList = new JList(pc.getHeaderElements().toArray());
			titlesList.setCellRenderer(new PagesViewCellRenderer());
			titlesList.setBounds(20, 20, 600, 180);
			titlesList
					.addMouseListener(new WorkflowViewElementPopClickListener(
							titlesList, pc, this));

			headerpane.add(projectHeddingLabel);
			headerpane.add(titlesList);
			headerpane.setBounds(0, 320, 600, 190);
			// /////////////

			// ////////////////////

			// paragraphpane
			paragraphpane.removeAll();
			paragraphpane.setLayout(null);
			projectParagraphLabel = new JLabel("Page paragraphs");
			projectParagraphLabel.setBounds(10, 0, 200, 15);
			// ////////////
			// list
			paragraphsList = new JList(pc.getParagraphElements().toArray());
			paragraphsList.setCellRenderer(new PagesViewCellRenderer());
			paragraphsList.setBounds(20, 20, 600, 180);
			paragraphsList
					.addMouseListener(new WorkflowViewElementPopClickListener(
							paragraphsList, pc, this));
			// ///////

			paragraphpane.add(projectParagraphLabel);
			paragraphpane.add(paragraphsList);
			paragraphpane.setBounds(0, 520, 600, 190);
			// ////////////////////

			// alles in de pageview plaatsen
			pageview.add(navigationpane);
			pageview.add(headerpane);
			pageview.add(paragraphpane);
			// ////////////////////////////

			// hertekenen
			this.repaint();
			// //////////////////////////
		}
	}

	public void ininitializeMap() {
		controllerperpage = new HashMap<Page, PageController>();

		for (Page p : project.getProjectPages()) {
			PageController pagecontroller = new PageController();

			pagecontroller.setPage(p);
			controllerperpage.put(p, pagecontroller);
		}
	}

}
