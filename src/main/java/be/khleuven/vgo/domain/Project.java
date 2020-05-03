package be.khleuven.vgo.domain;

import be.khleuven.vgo.db.DatabaseException;
import be.khleuven.vgo.db.HTMLReadAndWriter;
import be.khleuven.vgo.db.PagesLoader;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
    private String projectname;
    private String date;
    private final List<Page> pages;

    public Project(String projectname) throws DomainException,
            DatabaseException {
        this.setDate(Calendar.getInstance());
        this.setProjectname(projectname);
        pages = new ArrayList<Page>();
        boolean success = (new File("Projects", projectname + "-" + date
                + "/pages")).mkdirs();
        if (success) {
            System.out.println("Project aangemaakt");
            try {
                getPages();
            } catch (DatabaseException e) {
                throw new DomainException(
                        "Het laden van de pagina's is mislukt");
            }
        }
    }

    public Project(String projectname, String date) throws DomainException,
            DatabaseException {
        this.setDate(date);
        this.setProjectname(projectname);
        pages = new ArrayList<Page>();

        boolean success = (new File("Projects", projectname + "-" + date
                + "/pages")).mkdirs();
        if (success) {
            System.out.println("Project aangemaakt");
            try {
                getPages();
            } catch (DatabaseException e) {
                throw new DomainException(
                        "Het laden van de pagina's is mislukt");
            }
        }
    }

    public String getShortProjectname() {
        return projectname;
    }

    public String getFullProjectname() {
        return projectname + "-" + this.getDate();
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date.getTime().toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void createPage(String pagename, String title) {
        Page p = new Page(this.getFullProjectname(), pagename, title);
        pages.add(p);
    }

    public void removePage(String pagename) {
        // not implemented
    }

    public void removePage(int index, String pagename) {
        // not implemented
    }

    public void removePage(Page p) throws DomainException {
        pages.remove(p);
        File file = new File("Projects/" + this.getShortProjectname() + "-"
                + this.getDate() + "/pages/" + p.getName() + ".html");
        if (!file.delete()) {
            throw new DomainException("De file kon niet worden verwijdert");
        }
    }


    private void getPages() throws DatabaseException {
        File dir = new File("Projects/" + this.getShortProjectname() + "-"
                + this.getDate() + "/pages");

        boolean exists = dir.exists();
        if (!exists) {
            dir.mkdir();
        } else {
            HTMLReadAndWriter rw = new HTMLReadAndWriter();
            PagesLoader pl = new PagesLoader();
            List<String> pagesString = pl
                    .getAllPages(this.getFullProjectname());
            System.out.println(pagesString.size());
            for (String string : pagesString) {
                List<String> paginaInhoud;
                File file = new File("Projects/" + this.getFullProjectname()
                        + "/pages/" + string);
                if (file.exists()) {
                    System.out.println("Projects/" + this.getFullProjectname()
                            + "/pages/");
                    paginaInhoud = rw.readFromFile(file);
                    Page p = new Page(this.getFullProjectname());
                    p.setName(string.substring(0, string.length() - 5));
                    p.setInhoud(paginaInhoud);
                    pages.add(p);
                }
            }

        }
    }

    public Page getPage(String pagename) {
        Page page = null;

        for (Page p : this.getProjectPages()) {
            if (p.getName().equals(pagename)) {
                page = p;
                break;
            }
        }
        return page;
    }


    // public Page getPage(Page page){
    // for (Page p : this.getProjectPages()) {
    // if(p.getName().equals(pagename)){
    // page = p;
    // break;
    // }
    // }
    // return page;
    // }

    public List<Page> getProjectPages() {
        if (pages.size() == 0) {
            try {
                getPages();
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return this.pages;
    }

    public String toString() {
        return this.getShortProjectname() + "-" + this.getDate();
    }

}
