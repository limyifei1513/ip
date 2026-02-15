package fei;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a deadline task with a description and a due date/time.
 */
public class Deadline extends Task {

    private String byString;
    private LocalDate byLocalDate;

    /**
     * Creates a deadline task with the given description and due date/time.
     *
     * @param description Description of the task.
     * @param by          Due date/time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            byLocalDate = LocalDate.parse(by);
            byString = null;
        } catch (DateTimeParseException e) {
            byString = by;
            byLocalDate = null;
        }
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description,isDone);
        try {
            byLocalDate = LocalDate.parse(by);
            byString = null;
        } catch (DateTimeParseException e) {
            byString = by;
            byLocalDate = null;
        }
    }

    public String getBy() {
        if (byLocalDate != null) {
            return byLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return byString;
    }

    @Override
    public String print() {
        return "[D]" + super.print() + " (by: " + getBy() + ")";
    }

    @Override
    public String toFileString() {
        return "D // " + super.toFileString() + " // " + getBy() + System.lineSeparator();
    }
}
