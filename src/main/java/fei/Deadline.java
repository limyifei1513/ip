package fei;

/**
 * Represents a deadline task with a description and a due date/time.
 */
public class Deadline extends Task {

    private final String by;

    /**
     * Creates a deadline task with the given description and due date/time.
     *
     * @param description Description of the task.
     * @param by          Due date/time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description,isDone);
        this.by = by;
    }

    @Override
    public String print() {
        return "[D]" + super.print() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D // " + super.toFileString() + " // " + by + System.lineSeparator();
    }
}
