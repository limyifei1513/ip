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

    /**
     * Returns the formatted start date of the event.
     * If the date was parsed successfully, it is returned in {@code MMM dd yyyy}
     * format; otherwise, the original input string is returned.
     *
     * @return Start date of the event.
     */
    public String getFrom() {
        if (fromLocalDate != null) {
            return fromLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return fromString;
    }

    /**
     * Returns the formatted end date of the event.
     * If the date was parsed successfully, it is returned in {@code MMM dd yyyy}
     * format; otherwise, the original input string is returned.
     *
     * @return End date of the event.
     */
    public String getTo() {
        if (toLocalDate != null) {
            return toLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return toString;
    }

    /**
     * Returns a user-facing string representation of the event.
     *
     * @return String representation of the event for display.
     */
    @Override
    public String print() {
        return "[E]" + super.print() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }

    /**
     * Returns a string representation of the event for file storage.
     *
     * @return File storage format of the event.
     */
    @Override
    public String toFileString() {
        return "E // " + super.toFileString() + " // " + getFrom() + " // " + getTo() + System.lineSeparator();
    }      
}