package be.khleuven.vgo.ui.pagecontroller;

import be.khleuven.vgo.domain.Page;
import be.khleuven.vgo.domain.elements.Element;
import be.khleuven.vgo.domain.elements.ElementFactory;
import be.khleuven.vgo.domain.pageactions.PageElements;
import be.khleuven.vgo.domain.style.Style;

import java.util.List;

public class PageController {

    private Page page = null;

    public PageController() {
    }

    public void addElementToPage(String element, String inhoud, int plaats) {
        Element e = ElementFactory.createElement(element, inhoud);
        page.addElement(plaats, e);
    }

    public void addElementToPage(String element, String inhoud, Style stijl,
                                 int plaats) {
        Element e = ElementFactory.createElement(element, inhoud);
        e.setStyle(stijl);
        page.addElement(plaats, e);
    }

    public void addLinkedElementToPage(String element, String inhoud,
                                       String linkedTo, int plaats) {
        Element e = ElementFactory.createLinkElement(element, inhoud, linkedTo);
        page.addElement(plaats, e);
    }

    public void addLinkedElementToPage(String element, String inhoud,
                                       String linkedTo, Style stijl, int plaats) {
        Element e = ElementFactory.createLinkElement(element, inhoud, linkedTo);
        e.setStyle(stijl);
        page.addElement(plaats, e);

    }

    public void RemoveElementFromPage(Element e) {
        int todelete = 0;
        int teller = 0;
        for (String s : page.getInhoud()) {
            Element element = PageElements.getElement(s);
            if (element != null) {
                if (element.equals(e)) {
                    todelete = teller;

                }
            }
            teller++;
        }
        page.removeElement(todelete);

    }

    public List<Element> getElements() {
        return PageElements.getAllPageElements(page);
    }

    public List<Element> getParagraphElements() {
        return PageElements.getParagraphElements(page);
    }

    public List<Element> getHeaderElements() {
        return PageElements.getHeaderElements(page);
    }

    public List<Element> getNviagtionElements() {
        return PageElements.getNavigation(page);
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
