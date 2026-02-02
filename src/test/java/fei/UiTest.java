package fei;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Ui class.
 */
public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void sayHi_printsGreetingMessage() {
        Ui ui = new Ui();

        ui.sayHi();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Hello! I'm Fei"));
        assertTrue(output.contains("What can I do for you?"));
    }

    @Test
    public void sayBye_printsGoodbyeMessage() {
        Ui ui = new Ui();

        ui.sayBye();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    public void showList_printsAllTasksWithIndex() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("write code"));

        ui.showList(tasks);

        String output = outputStreamCaptor.toString();
        String expectedLine1 = "1. " + tasks.getTask(0).print();
        String expectedLine2 = "2. " + tasks.getTask(1).print();

        assertTrue(output.contains(expectedLine1));
        assertTrue(output.contains(expectedLine2));
    }
}
