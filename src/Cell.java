public class Cell {
    private char character;
    private String foreground;
    private String background;
    private boolean bold;
    private boolean italic;
    private boolean underline;

    // Constructor
    public Cell() {
        this.character = ' ';
        this.foreground = "default";
        this.background = "default";
        this.bold = false;
        this.italic = false;
        this.underline = false;
    }


    public Cell(char character) {
        this();
        this.character = character;
    }

    // Getters and setters
    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public String getForeground() {
        return foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }
}