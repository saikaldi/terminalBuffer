import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TerminalBufferTest {

    @Test
    void testWriteText() {
        TerminalBuffer buffer = new TerminalBuffer(5, 3);
        buffer.writeText("Hi");
        assertEquals('H', buffer.getCell(0,0).getCharacter());
        assertEquals('i', buffer.getCell(0,1).getCharacter());
    }

    @Test
    void testInsertEmptyLineScroll() {
        TerminalBuffer buffer = new TerminalBuffer(5, 2);
        buffer.writeText("Hello");
        buffer.insertEmptyLine();
        // top line should go to scrollback
        assertEquals(1, buffer.getScrollbackSize());
        // cursor should be at start of new line
        assertEquals(1, buffer.getCursorRow());
        assertEquals(0, buffer.getCursorCol());
    }

    @Test
    void testClearScreen() {
        TerminalBuffer buffer = new TerminalBuffer(5, 3);
        buffer.writeText("Bye");
        buffer.clearScreen();
        for (int r=0; r<3; r++) {
            for (int c=0; c<5; c++) {
                assertEquals(' ', buffer.getCell(r,c).getCharacter());
            }
        }
        assertEquals(0, buffer.getCursorRow());
        assertEquals(0, buffer.getCursorCol());
    }
}