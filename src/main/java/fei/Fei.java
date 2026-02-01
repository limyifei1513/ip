package fei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Runs the Fei task manager application.
 */
public class Fei {
    /**
     * Initializes the data file if it does not exist and returns its path.
     *
     * @return The file path of the data file.
     */
    static String getFilePath() {
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

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Fei(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.loadContents();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        ui.showList(tasks);
        ui.sayHi();

        while (true) {
            System.out.println("");
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                ui.sayBye();
                break;
            } else if (userInput.equals("list")) {
                ui.showList(tasks); 
            } else if (userInput.startsWith("mark ")) {
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new FeiException("Must follow this format: \nmark <valid number>");
                    }

                    int taskNumber = Integer.parseInt(parts[1]); 
                    parser.validateMarkNumber(taskNumber, tasks);
                    tasks.getTask(taskNumber - 1).mark();
                    storage.rewriteFile(tasks);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + tasks.getTask(taskNumber -1).print());
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
                    parser.validateMarkNumber(taskNumber, tasks); 
                    tasks.getTask(taskNumber - 1).unmark();
                    storage.rewriteFile(tasks);
                    System.out.println("Nice! I've marked this task as not done yet:");
                    System.out.println("    " + tasks.getTask(taskNumber - 1).print());
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
                    parser.validateMarkNumber(taskNumber, tasks); 

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    "+tasks.getTask(taskNumber - 1).print());
                    tasks.remove(taskNumber - 1);
                    storage.rewriteFile(tasks);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
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
                    parser.validateNotEmpty(task, "todo");
                    tasks.add(new Todo(task));
                    storage.appendToFile(tasks.getTask(tasks.getSize() - 1).toFileString());
                    ui.printConfirmation(tasks);
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                    }
            } else if (userInput.startsWith("deadline ")) {
                try{
                    String task = userInput.substring(9);
                    parser.validateNotEmpty(task, "deadline");
                    String[] parts = task.split(" /by ");
                    parser.validateDeadlineFormat(parts);
                    tasks.add(new Deadline(parts[0], parts[1]));
                    storage.appendToFile(tasks.getTask(tasks.getSize() - 1).toFileString());
                    ui.printConfirmation(tasks);
                } catch (FeiException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (userInput.startsWith("event ")) {
                try{
                    String task = userInput.substring(6);
                    parser.validateNotEmpty(task, "event");
                    String[] parts = task.split(" /from ");
                    parser.validateEventFormat(parts);
                    String[] from_to = parts[1].split(" /to ");
                    parser.validateEventFormat(from_to);
                    tasks.add(new Event(parts[0], from_to[0], from_to[1]));
                    storage.appendToFile(tasks.getTask(tasks.getSize() - 1).toFileString());
                    ui.printConfirmation(tasks);   
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

    /**
     * Starts the application.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String filePath = getFilePath();
        new Fei(filePath).run();
    }
}