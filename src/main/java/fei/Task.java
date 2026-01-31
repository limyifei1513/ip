package fei;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    String name;
    Boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String print() {
        if (isDone) {
            return ("[X] " + name);
        } else {
            return ("[ ] " + name);
        }
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toFileString() {
        return isDone + " // " + name;
    }
}