package be.khleuven.vgo.domain.pageactions;

import java.util.ArrayList;
import java.util.List;

import be.khleuven.vgo.domain.Page;
import be.khleuven.vgo.domain.elements.Element;
import be.khleuven.vgo.domain.elements.ElementFactory;

public class PageElements {



	public PageElements() {
	}

	public static List<Element> getAllPageElements(Page p) {
		List<Element> elementsOnMyPage = new ArrayList<Element>();
		String inhoud;
		Element e;
		String[] split;
		for (String s : p.getInhoud()) {
			if (s.contains("<p")) {
				split = s.split(">");
				inhoud = split[1];
				inhoud = inhoud.substring(0, inhoud.length() - 3);

				e = ElementFactory.createElement("p", inhoud);
				elementsOnMyPage.add(e);

			} else if (s.contains("<h1")) {
				split = s.split(">");
				inhoud = split[1];
				inhoud = inhoud.substring(0, inhoud.length() - 4);
				e = ElementFactory.createElement("h1", inhoud);
				elementsOnMyPage.add(e);
			} else if (s.contains("<div")) {
				inhoud = s.substring(5, s.length() - 6);
				e = ElementFactory.createElement("div", inhoud);
				elementsOnMyPage.add(e);

			} else if (s.contains("<a")) {
				split = s.split(">");
				inhoud = split[1];
				inhoud = inhoud.substring(0, inhoud.length() - 3);

				String[] tmp = split[0].split("href=\\\"");

				String linkedTo = tmp[1].substring(0, tmp[1].length() - 1);

				e = ElementFactory.createLinkElement("a", inhoud, linkedTo);
				elementsOnMyPage.add(e);
			}

		}

		// e = new PElement("Dit is de inhoud");
		// elementsOnMyPage.add(e);
		return elementsOnMyPage;
	}

	public static List<Element> getParagraphElements(Page p) {
		List<Element> elementsOnMyPage = new ArrayList<Element>();
		String inhoud;
		Element e;
		String[] split;
		for (String s : p.getInhoud()) {
			if (s.contains("<p")) {
				// inhoud = s.substring(3, s.length() - 4);
				split = s.split(">");
				inhoud = split[1];
				inhoud = inhoud.substring(0, inhoud.length() - 3);

				e = ElementFactory.createElement("p", inhoud);
				elementsOnMyPage.add(e);

			}
		}

		return elementsOnMyPage;
	}

	public static List<Element> getHeaderElements(Page p) {
		List<Element> elementsOnMyPage = new ArrayList<Element>();
		String inhoud;
		String[] split;

		Element e;

		for (String s : p.getInhoud()) {
			if (s.contains("<h1")) {
				split = s.split(">");
				inhoud = split[1];
				inhoud = inhoud.substring(0, inhoud.length() - 4);
				e = ElementFactory.createElement("h1", inhoud);
				elementsOnMyPage.add(e);

			}
		}
		return elementsOnMyPage;
	}

	public static List<Element> getNavigation(Page p) {
		List<Element> navigationOnMyPage = new ArrayList<Element>();
		String inhoud;
		String[] split;

		Element e;

		for (String string : p.getInhoud()) {
			if (string.contains("<a")) {
				split = string.split(">");
				inhoud = split[1];
				inhoud = inhoud.substring(0, inhoud.length() - 3);

				String[] tmp = split[0].split("href=\\\"");

				String linkedTo = tmp[1].substring(0, tmp[1].length() - 1);

				e = ElementFactory.createLinkElement("a", inhoud, linkedTo);
				navigationOnMyPage.add(e);
			}
		}

		return navigationOnMyPage;
	}
	
	public static Element getElement(String s) {
		String inhoud;
		Element e = null;
		String[] split;
		if (s.contains("<p")) {
			split = s.split(">");
			inhoud = split[1];
			inhoud = inhoud.substring(0, inhoud.length() - 3);

			e = ElementFactory.createElement("p", inhoud);

		} else if (s.contains("<h1")) {
			split = s.split(">");
			inhoud = split[1];
			inhoud = inhoud.substring(0, inhoud.length() - 4);
			e = ElementFactory.createElement("h1", inhoud);
		} else if (s.contains("<div")) {
			inhoud = s.substring(5, s.length() - 6);
			e = ElementFactory.createElement("div", inhoud);

		} else if (s.contains("<a")) {
			split = s.split(">");
			inhoud = split[1];
			inhoud = inhoud.substring(0, inhoud.length() - 3);

			String[] tmp = split[0].split("href=\\\"");

			String linkedTo = tmp[1].substring(0, tmp[1].length() - 1);

			e = ElementFactory.createLinkElement("a", inhoud, linkedTo);
		}

		return e;
	}
}
