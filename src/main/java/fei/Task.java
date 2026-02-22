package fei;

/**
 * Represents a task with a description and a completion status.
 * <p>
 * {@code Task} is the base abstraction used by Fei to model items in the task list.
 * Subclasses (e.g., {@code Todo}, {@code Deadline}, {@code Event}) may extend this class
 * to add additional fields such as dates.
 */
public class Task {
    /**
     * The user-facing description of this task.
     */
    private String name;

    /**
     * Whether this task has been completed.
     */
    private Boolean isDone;

    /**
     * Creates a new incomplete task with the given description.
     *
     * @param name the task description
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a task with the given description and completion status.
     * This constructor is typically used when loading tasks from storage.
     *
     * @param name the task description
     * @param isDone whether the task is completed
     */
    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns a user-facing representation of this task, including the completion marker.
     *
     * @return a formatted string such as {@code "[ ] buy milk"} or {@code "[X] buy milk"}
     */
    public String print() {
        return isDone ? "[X] " + name : "[ ] " + name;
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Serializes this task into a storage-friendly single-line format.
     * <p>
     * Current format:
     * <pre>
     * isDone // name
     * </pre>
     *
     * @return a single-line string suitable for writing to the save file
     */
    public String toFileString() {
        return isDone + " // " + name;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getName() {
        return name;
    }
}