package fei;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**  
 * Runs the Fei task manager application.
 */
public class Fei {

    /**
     * Prints the greeting message to the user.
     */
    public static void sayHi() {
        System.out.println("\nHello! I'm Fei\nAbove is your previously saved list.\nWhat can I do for you?");
    }

    /**
     * Prints the goodbye message to the user.
     */
    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all tasks in the given task list.
     *
     * @param tasks Task list to be printed.
     */
    public static void showList(ArrayList<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).print());
        }
    }

    /**
     * Validates that the task number is within the valid range of the task list.
     *
     * @param num   The task number to validate.
     * @param tasks The list of tasks.
     * @throws FeiException if the task number is out of range.
     */
    private static void validateMarkNumber(int num, ArrayList<Task> tasks) throws FeiException {
        if (num > tasks.size() || num < 1) {
            throw new FeiException("That task number is out of range.");
        }
    }

    /**
     * Validates that the task description is not empty.
     *
     * @param description The description to validate.
     * @param task        The type of task (e.g., "todo", "deadline", "event").
     * @throws FeiException if the description is empty.
     */
    private static void validateIfEmpty(String description, String task) throws FeiException {
        if (description.isEmpty()) {
            throw new FeiException("A/An " + task + " must have a description.");
        }
    }

    /**
     * Validates the format of the deadline task input.
     *
     * @param parts The parts of the deadline input split by " /by ".
     * @throws FeiException if the format is incorrect.
     */
    private static void validateDeadline(String[] parts) throws FeiException {
        if (parts.length < 2) {
            throw new FeiException("Must follow this format: \ndeadline <description> /by <date>");
        }
    }

    /**
     * Validates the format of the event task input.
     *
     * @param parts The parts of the event input split by " /from ".
     * @throws FeiException if the format is incorrect.
     */
    private static void validateEvent(String[] parts) throws FeiException {
        if (parts.length < 2) {
            throw new FeiException("Must follow this format: \nevent <description> /from <date> /to <date>");
        }
    }

    /**
     * Initializes the data file if it does not exist and returns its path.
     *
     * @return The file path of the data file.
     */
    private static String importFile() {
        String fileAddress = System.getProperty("user.dir") + "/data.txt";
        try {
            if (!Files.exists(Paths.get(fileAddress))) {
                Files.createFile(Paths.get(fileAddress));
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + e.getMessage());
        }
        return fileAddress;
    }

    /**
     * Loads tasks from the specified file into the task list.
     *
     * @param filePath The path to the data file.
     * @param tasks    The task list to load tasks into.
     * @throws FileNotFoundException if the file cannot be found.
     */
    private static void loadContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String data = s.nextLine();
            String[] dataParts = data.split(" // ");
            Boolean isDone = Boolean.parseBoolean(dataParts[1]);

            if (dataParts[0].equals("T")) {
                tasks.add(new Todo(dataParts[2], isDone));
            } else if (dataParts[0].equals("D")) {
                tasks.add(new Deadline(dataParts[2], dataParts[3], isDone));
            } else if (dataParts[0].equals("E")) {
                tasks.add(new Event(dataParts[2], dataParts[3], dataParts[4], isDone));
            } else {
                System.out.println("File is corrupted, can't read the file");
            }
        }
    }

    /**
     * Prints a confirmation message after adding a new task.
     *
     * @param tasks The list of tasks.
     */
    private static void printConfirmation(ArrayList<Task> tasks) {
        int size = tasks.size();

        System.out.println("Got it. I've added this task:");
        System.out.println("    " + tasks.get(size-1).print());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Appends the given text to the specified file.
     *
     * @param filePath     The path to the file.
     * @param textToAppend The text to append.
     * @throws IOException if an I/O error occurs.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
    
    /**
     * Rewrites the entire file with the current list of tasks.
     *
     * @param filePath The path to the file.
     * @param tasks    The list of tasks to write.
     * @throws IOException if an I/O error occurs.
     */
    private static void rewriteFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (Task t : tasks) {
            fw.write(t.toFileString());
        }
        fw.close();
    }

    /**
     * Starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String fileAddress = importFile();
        try {
            loadContents(fileAddress, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        showList(tasks);
        sayHi();

        while (true) {
            System.out.println("");
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                sayBye();
                break;
            } else if (userInput.equals("list")) {
                showList(tasks); 
            } else if (userInput.startsWith("mark ")) {
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new FeiException("Must follow this format: \nmark <valid number>");
                    }

                    int taskNumber = Integer.parseInt(parts[1]); 
                    validateMarkNumber(taskNumber, tasks);
                    tasks.get(taskNumber - 1).mark();
                    rewriteFile(fileAddress, tasks);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    "+tasks.get(taskNumber -1).print());
                } catch (NumberFormatException e) {
                    System.out.println("Task number must be a number");
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new FeiException("Must follow this format: \nunmark <valid number>");
                    }

                    int taskNumber = Integer.parseInt(parts[1]);
                    validateMarkNumber(taskNumber, tasks); 
                    tasks.get(taskNumber - 1).unmark();
                    rewriteFile(fileAddress, tasks);
                    System.out.println("Nice! I've marked this task as not done yet:");
                    System.out.println("    " + tasks.get(taskNumber - 1).print());
                } catch (NumberFormatException e) {
                    System.out.println("Task number must be a number");
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (userInput.startsWith("delete ")) {
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new FeiException("Must follow this format: \ndelete <valid number>");
                    }

                    int taskNumber = Integer.parseInt(parts[1]);
                    validateMarkNumber(taskNumber, tasks); 

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    "+tasks.get(taskNumber - 1).print());
                    tasks.remove(taskNumber - 1);
                    rewriteFile(fileAddress, tasks);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (NumberFormatException e) {
                    System.out.println("Task number must be a number");
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (userInput.startsWith("todo ")){
                try{
                    String task = userInput.substring(5);
                    validateIfEmpty(task, "todo");
                    tasks.add(new Todo(task));
                    appendToFile(fileAddress, tasks.get(tasks.size() - 1).toFileString());
                    printConfirmation(tasks);
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                    }
            } else if (userInput.startsWith("deadline ")) {
                try{
                    String task = userInput.substring(9);
                    validateIfEmpty(task, "deadline");
                    String[] parts = task.split(" /by ");
                    validateDeadline(parts);
                    tasks.add(new Deadline(parts[0], parts[1]));
                    appendToFile(fileAddress, tasks.get(tasks.size() - 1).toFileString());
                    printConfirmation(tasks);
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (userInput.startsWith("event ")) {
                try{
                    String task = userInput.substring(6);
                    validateIfEmpty(task, "event");
                    String[] parts = task.split(" /from ");
                    validateEvent(parts);
                    String[] from_to = parts[1].split(" /to ");
                    validateEvent(from_to);
                    tasks.add(new Event(parts[0], from_to[0], from_to[1]));
                    appendToFile(fileAddress, tasks.get(tasks.size() - 1).toFileString());
                    printConfirmation(tasks);   
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else {
                System.out.println("please enter a valid command"); 
            }
        }
    }
}