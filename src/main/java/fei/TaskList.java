package fei;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList (ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task to retrieve.
     * @return Task at the given index.
    */
    Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns all tasks in this list.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to remove.
     */
    void remove (int index) {
        tasks.remove(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    void add (Task task) {
        tasks.add(task);
    }
}
