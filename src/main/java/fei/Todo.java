package fei;

/**
 * Represents a todo task with a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    
    public Todo(String description,Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a user-facing string representation of the todo task.
     *
     * @return String representation of the todo task for display.
     */
    @Override
    public String print() {
        return "[T]" + super.print();
    }

    /**
     * Returns a string representation of the todo task for file storage.
     *
     * @return File storage format of the todo task.
     */
    @Override
    public String toFileString() {
        return "T // " + super.toFileString() + System.lineSeparator();
    }
}
