package be.khleuven.vgo.ui.utilities;

import be.khleuven.vgo.domain.DomainException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DisplayHTMLPage {
    public DisplayHTMLPage(File page) throws DomainException {
        display(page);
    }

    private void display(File page) throws DomainException {
        try {
            Desktop.getDesktop().browse(page.toURI());
        } catch (IOException e) {
            throw new DomainException("De pagina is niet gevonden", e);
        }

    }
}
