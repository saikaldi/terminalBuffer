public class TerminalBuffer {
    private int width;
    private int height;
    private Cell[][] screen;  // screen: rows x cols
    private int cursorRow;
    private int cursorCol;

    public TerminalBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.cursorRow = 0;
        this.cursorCol = 0;

        // create an empty screen
        screen = new Cell[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                screen[r][c] = new Cell();
            }
        }
    }

    // Get current cursor row
    public int getCursorRow() {
        return cursorRow;
    }

    // Get current cursor column
    public int getCursorCol() {
        return cursorCol;
    }

    // Set cursor position
    public void setCursor(int row, int col) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            cursorRow = row;
            cursorCol = col;
        }
    }

    // Write a character at the current cursor position
    public void writeChar(char c) {
        screen[cursorRow][cursorCol].setCharacter(c);
        cursorCol++;
        if (cursorCol >= width) {
            cursorCol = 0;
            cursorRow++;
            if (cursorRow >= height) {
                cursorRow = height - 1; // scrollback not implemented yet
            }
        }
    }
}