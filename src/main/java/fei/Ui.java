package fei;

public class Ui {

    /**
     * Prints the greeting message to the user.
     */
    public String sayHi() {
        return ("Hello! I'm Fei\nBelow is your previously saved list.\nWhat can I do for you?");
    }

    /**
     * Prints all tasks in the given task list.
     *
     * @param tasks Task list to be printed.
     */
    public String showList(TaskList tasks) {
        String ans = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            ans += i + ". " + tasks.getTask(i - 1).print() + "\n";
        }
        return ans;
    }

    /**
     * Prints a confirmation message after adding a new task.
     *
     * @param tasks The list of tasks.
     */
    public String printConfirmation(TaskList tasks) {
        int size = tasks.getSize();
        return ("Got it. I've added this task:\n" + "    " + tasks.getTask(size - 1).print()
                + "\n" + "Now you have " + size + " tasks in the list.");
    }

    public String showLoadingError() {
        return "File not found";
    }
}
