
````markdown
# TerminalBuffer Project

## Overview
This project implements a simple **terminal buffer** in Java.  
A terminal buffer simulates a terminal screen, storing characters in a grid, handling cursor movement, scrollback history, and basic text editing. This is similar to how terminal emulators like `bash` or `zsh` manage screen content.

Features implemented:  
- Screen represented as a grid of `Cell`s  
- Cursor position tracking  
- Writing characters and text  
- Insert empty lines with scrollback  
- Clear screen and clear scrollback  
- Access screen content  

---

## Getting Started

### Prerequisites
- Java 21 or later  
- Gradle 9.x  

### Build and Run
Clone the repository:  
```bash
git clone https://github.com/saikaldi/terminal.git
cd terminal
````

Build the project:

```bash
gradle build
```

Run the main program:

```bash
gradle run
```

Run tests:

```bash
gradle test
```

---

## Example Usage

```java
TerminalBuffer buffer = new TerminalBuffer(5, 3);
buffer.writeText("Hi");
buffer.insertEmptyLine();
buffer.writeText("Bye");

System.out.println("=== Screen ===");
for (int r = 0; r < 3; r++) {
    for (int c = 0; c < 5; c++) {
        System.out.print(buffer.getCell(r, c).getCharacter());
    }
    System.out.println();
}

buffer.clearScreen();
```

---

## Project Structure

```
terminal/
├─ src/
│  ├─ Cell.java
│  ├─ TerminalBuffer.java
│  └─ Main.java
├─ build.gradle
└─ README.md
```

---

## Tests

Unit tests are implemented using **JUnit 5** in `src/test/java`.
The tests cover:

* Writing text and characters
* Cursor movement
* Screen clearing
* Scrollback insertion and clearing

---

## Design Decisions

* Screen stored as a 2D array of `Cell`s for simplicity
* Scrollback stored as a `LinkedList<Cell[]>`
* Cursor does not move outside screen bounds
* Text wraps automatically at the end of the line
* For simplicity, wide characters and resizing are not yet implemented

---

## Git History

* Incremental commits show development progress
* Commit messages are descriptive and separated by feature or refactoring


