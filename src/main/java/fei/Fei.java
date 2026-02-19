package fei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    // public String getGreeting() {
    //     return ui.sayHi();
    // }
    public String getGreeting() {
        return ui.sayHi(); 
    }

    public String showSavedList() {
        return ui.showList(tasks);
    }
    public String getResponse(String userInput) {
        String printMsg = "";
        if (userInput.equals("bye")) {
            javafx.application.Platform.exit();
            return "";
        } else if (userInput.equals("list")) {
            return ui.showList(tasks); 
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
                return ("Nice! I've marked this task as done:\n"
                        + "    " + tasks.getTask(taskNumber -1).print());
            } catch (NumberFormatException e) {
                return "Task number must be a number";
            } catch (FeiException e) {
                return e.getMessage();
            } catch (IOException e) {
                return "Something went wrong: " + e.getMessage();
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
                return "Nice! I've marked this task as not done yet:\n"
                        + "    " + tasks.getTask(taskNumber - 1).print();
            } catch (NumberFormatException e) {
                return "Task number must be a number";
            } catch (FeiException e) {
                return e.getMessage();
            } catch (IOException e) {
                return "Something went wrong: " + e.getMessage();
            }
        } else if (userInput.startsWith("delete ")) {
            try {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    throw new FeiException("Must follow this format: \ndelete <valid number>");
                }

                int taskNumber = Integer.parseInt(parts[1]);
                parser.validateMarkNumber(taskNumber, tasks); 
                printMsg += "Noted. I've removed this task:\n" 
                        + "    " + tasks.getTask(taskNumber - 1).print();
                tasks.remove(taskNumber - 1);
                storage.rewriteFile(tasks);
                printMsg += "Now you have " + tasks.getSize() + " tasks in the list.";
                return printMsg;
            } catch (NumberFormatException e) {
                return "Task number must be a number";
            } catch (FeiException e) {
                return e.getMessage();
            } catch (IOException e) {
                return ("Something went wrong: " + e.getMessage());
            }
        } else if (userInput.startsWith("todo ")){
            try{
                String task = userInput.substring(5);
                parser.validateNotEmpty(task, "todo");
                tasks.add(new Todo(task));
                storage.appendToFile(tasks.getTask(tasks.getSize() - 1).toFileString());
                return ui.printConfirmation(tasks);
            } catch (FeiException e) {
                return (e.getMessage());
            } catch (IOException e) {
                return ("Something went wrong: " + e.getMessage());
                }
        } else if (userInput.startsWith("deadline ")) {
            try{
                String task = userInput.substring(9);
                parser.validateNotEmpty(task, "deadline");
                String[] parts = task.split(" /by ");
                parser.validateDeadlineFormat(parts);
                tasks.add(new Deadline(parts[0], parts[1]));
                storage.appendToFile(tasks.getTask(tasks.getSize() - 1).toFileString());
                return ui.printConfirmation(tasks);
            } catch (FeiException e) {
                return (e.getMessage());
            } catch (IOException e) {
                return ("Something went wrong: " + e.getMessage());
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
                return ui.printConfirmation(tasks);   
            } catch (FeiException e) {
                return (e.getMessage());
            } catch (IOException e) {
                return ("Something went wrong: " + e.getMessage());
            }
        } else if (userInput.startsWith("find ")) { 
            String keyword = userInput.substring(6);
            TaskList foundTasks = tasks.find(keyword);
            if (foundTasks.getSize() == 0) {
                return ("No matching tasks found.");  
            } else {
                return ("     Here are the matching tasks in your list:\n"
                        + ui.showList(foundTasks));
            }
        } else {
            return ("please enter a valid command");
        }
    }

    /**
     * Starts the application.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("start");
    }
}