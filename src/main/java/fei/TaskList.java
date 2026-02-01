package fei;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList  (){
        this.tasks = new ArrayList<Task>();
    }

    public TaskList (ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    int getSize() {
        return tasks.size();
    }

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

    void remove (int index){
        tasks.remove(index);
    }

    void add (Task task){
        tasks.add(task);
    }
}
