package fei;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a description, the start time and end time
 */
public class Event extends Task {

    private String fromString;
    private String toString;
    private LocalDate fromLocalDate;
    private LocalDate toLocalDate;

    /**
     * Creates an event with the given description, start date, and end date.
     * Attempts to parse the start and end dates in {@code yyyy-MM-dd} format.
     * If parsing fails, the original date strings are stored instead.
     *
     * @param description Description of the event.
     * @param from        Start date of the event.
     * @param to          End date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            fromLocalDate = LocalDate.parse(from);
            fromString = null;
            toLocalDate = LocalDate.parse(to);
            toString = null;
        } catch (DateTimeParseException e) {
            fromString = from;
            fromLocalDate = null;
            toString = to;
            toLocalDate = null;
            System.out.println(
                "Current date is not stored in Date format, you may enter your date in this format: "
                    + "yyyy-mm-dd (e.g., 2019-10-15) if you wish");
        }
    }

    /**
     * Creates an event with the given description, start date, end date,
     * and completion status.
     * Attempts to parse the start and end dates in {@code yyyy-MM-dd} format.
     * If parsing fails, the original date strings are stored instead.
     *
     * @param description Description of the event.
     * @param from        Start date of the event.
     * @param to          End date of the event.
     * @param isDone      Whether the event is marked as completed.
     */
    public Event(String description, String from, String to, Boolean isDone) {
        super(description, isDone);
        try {
            fromLocalDate = LocalDate.parse(from);
            fromString = null;
            toLocalDate = LocalDate.parse(to);
            toString = null;
        } catch (DateTimeParseException e) {
            fromString = from;
            fromLocalDate = null;
            toString = to;
            toLocalDate = null;
        }
    }

    public String getFrom() {
        if (fromLocalDate != null) {
            return fromLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return fromString;
    }

    public String getTo() {
        if (toLocalDate != null) {
            return toLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return toString;
    }

    @Override
    public String print() {
        return "[E]" + super.print() + " (from: " + getFrom() + " to: " + getTo() +")";
    }

    @Override
    public String toFileString() {
        return "E // " + super.toFileString() + " // " + getFrom() + " // " + getTo() + System.lineSeparator();
    }      
}