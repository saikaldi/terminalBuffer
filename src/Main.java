
public class Main {
    public static void main(String[] args) {
        TerminalBuffer buffer = new TerminalBuffer(5, 3);

        // Write text and move cursor
        buffer.writeText("Hi");
        buffer.moveCursorDown(1);
        buffer.insertText("Bye");

        // Fill last line
        buffer.moveCursorDown(1);
        buffer.fillLine('-');

        // Print screen before clearing
        System.out.println("=== Screen before clear ===");
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 5; c++) {
                System.out.print(buffer.getCell(r, c).getCharacter());
            }
            System.out.println();
        }

        // Print full content (scrollback + screen)
        System.out.println("=== Full Content before clear ===");
        for (Cell[] line : buffer.getFullContentLines()) {
            for (Cell cell : line) System.out.print(cell.getCharacter());
            System.out.println();
        }

        // Clear everything
        buffer.clearScreenAndScrollback();

        System.out.println("=== Screen after clear ===");
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 5; c++) {
                System.out.print(buffer.getCell(r, c).getCharacter());
            }
            System.out.println();
        }
    }
}