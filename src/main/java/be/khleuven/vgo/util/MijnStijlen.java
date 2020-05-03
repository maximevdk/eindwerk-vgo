package be.khleuven.vgo.util;

import java.awt.*;

public class MijnStijlen {
    String[] fonts;

    public MijnStijlen() {
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }

    public static void main(String[] args) {
        MijnStijlen m = new MijnStijlen();
        for (String string : m.getFonts()) {
            System.out.println(string);
        }
    }

    public String[] getFonts() {
        return this.fonts;
    }
}
