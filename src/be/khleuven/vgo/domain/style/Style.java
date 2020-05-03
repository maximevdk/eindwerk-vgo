package be.khleuven.vgo.domain.style;


public class Style {
	private String fontSize;
	private Color textColor;
	private Color backgroundColor;
	private FontStyle fontStyle;

	public Style() {
		this.setFontSize("1em");
		this.setTextColor(Color.BLACK);
		this.setBackgroundColor(Color.WHITE);
		this.setFontStyle(FontStyle.NORMAL);
	}

	public Style(String fontSize, Color textColor, Color backColor,
			FontStyle fontStyle) {
		this.setFontSize(fontSize);
		this.setTextColor(textColor);
		this.setBackgroundColor(backColor);
		this.setFontStyle(fontStyle);
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public FontStyle getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String generateHTML() {
		String output = "style=\"";

		output += "color:" + this.getTextColor() + ";";
		output += "background-color:" + this.getBackgroundColor().toString()
				+ ";";
		output += "font-size:" + this.fontSize + ";";
		output += "font-style:" + this.getFontStyle() + ";\"";
		return output;
	}

//	public static void main(String[] args) {
//		Style s = new Style(20, Color.GREY, Color.YELLOW, FontStyle.ITALIC);
//		System.out.println(s.generateHTML());
//	}

}
