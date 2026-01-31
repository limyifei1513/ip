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

    @Override
    public String print() {
        return "[T]" + super.print();
    }

    @Override
    public String toFileString() {
        return "T // " + super.toFileString() + System.lineSeparator();
    }  
}
