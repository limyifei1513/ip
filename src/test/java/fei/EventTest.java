package fei;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventPrint_notDone_formatsCorrectly() {
        Event event = new Event("team meeting", "Mon 2pm", "Mon 4pm");

        assertEquals("[E][ ] team meeting (from: Mon 2pm to: Mon 4pm)", event.print());
    }

    @Test
    public void eventPrint_done_formatsCorrectly() {
        Event event = new Event("team meeting", "Mon 2pm", "Mon 4pm", true);

        assertEquals("[E][X] team meeting (from: Mon 2pm to: Mon 4pm)", event.print());
    }

    @Test
    public void eventToFileString_notDone_formatsCorrectly() {
        Event event = new Event("team meeting", "Mon 2pm", "Mon 4pm");

        String expected = "E // false // team meeting // Mon 2pm // Mon 4pm" + System.lineSeparator();
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void eventToFileString_done_formatsCorrectly() {
        Event event = new Event("team meeting", "Mon 2pm", "Mon 4pm", true);

        String expected = "E // true // team meeting // Mon 2pm // Mon 4pm" + System.lineSeparator();
        assertEquals(expected, event.toFileString());
    }
}
