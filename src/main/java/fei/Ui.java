package fei;

public class Ui {

    /**
     * Prints the greeting message to the user.
     */
    void sayHi() {
        System.out.println("\nHello! I'm Fei\nAbove is your previously saved list.\nWhat can I do for you?");
    }

    /**
     * Prints the goodbye message to the user.
     */
    void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all tasks in the given task list.
     *
     * @param tasks Task list to be printed.
     */
    void showList(TaskList tasks) {
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(i + ". " + tasks.getTask(i - 1).print());
        }
    }

    /**
     * Prints a confirmation message after adding a new task.
     *
     * @param tasks The list of tasks.
     */
    void printConfirmation(TaskList tasks) {
        int size = tasks.getSize();

        System.out.println("Got it. I've added this task:");
        System.out.println("    " + tasks.getTask(size - 1).print());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    void showLoadingError() {
        System.out.println("File not found");
    }
}
