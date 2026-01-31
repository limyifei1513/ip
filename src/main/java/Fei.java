import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Fei {

    public static void sayHi(){
        System.out.println("\nHello! I'm Fei\nAbove is your previously saved list.\nWhat can I do for you?");
    }

    public static void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showList(ArrayList<Task> tasks){
        for (int i = 1; i <= tasks.size();i++){
            System.out.println(i +". " + tasks.get(i-1).print());
        }
    }

    private static void validateMarkNumber(int num, ArrayList<Task> tasks) throws FeiException{
        if (num > tasks.size() || num < 1){
            throw new FeiException("That task number is out of range.");
        }
    }
    private static void validateIfEmpty(String description, String task) throws FeiException {
        if (description.isEmpty()) {
            throw new FeiException("A/An "+ task + " must have a description.");
        }
    }

    private static void validateDeadline(String[] parts) throws FeiException {
        if (parts.length < 2) {
            throw new FeiException("Must follow this format: \ndeadline <description> /by <date>");
        }
    }
    private static void validateEvent(String[] parts) throws FeiException {
        if (parts.length < 2) {
            throw new FeiException("Must follow this format: \nevent <description> /from <date> /to <date>");
        }
    }
    private static String importFile(){
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

    private static void loadContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String data = s.nextLine();
            String[] dataParts = data.split(" // ");
            Boolean isDone = Boolean.parseBoolean(dataParts[1]);
            if (dataParts[0].equals("T")){
                tasks.add(new Todo(dataParts[2],isDone));
            }

            else if (dataParts[0].equals("D")){
                tasks.add(new Deadline(dataParts[2],dataParts[3],isDone));
            }

            else if (dataParts[0].equals("E")){
                tasks.add(new Event(dataParts[2],dataParts[3],dataParts[4],isDone));
            }

            else{
                System.out.println("File is corrupted, can't read the file");
            }
        }
    }

    private static void printConfirmation(ArrayList<Task> tasks){
        System.out.println("Got it. I've added this task:");
        int size = tasks.size();
        System.out.println("    "+tasks.get(size-1).print());
        System.out.println("Now you have "+ size +" tasks in the list.");
    }
    
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String fileAddress;
        fileAddress = importFile();
        try {
            loadContents(fileAddress,tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        showList(tasks);
        sayHi();

        while (true){
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")){
                sayBye();
                break;
            }
            else if (userInput.equals("list")){
                showList(tasks); 
            }

            else if (userInput.startsWith("mark ")){
                try{
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new FeiException("Must follow this format: \nmark <valid number>");
                    }
                    int taskNumber = Integer.parseInt(parts[1]); 
                    validateMarkNumber(taskNumber, tasks);
                    tasks.get(taskNumber -1).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    "+tasks.get(taskNumber -1).print());

                } catch (NumberFormatException e){
                    System.out.println("Task number must be a number");

                } catch (FeiException e){
                    System.out.println(e.getMessage());
                }

            }

            else if (userInput.startsWith("unmark ")){
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new FeiException("Must follow this format: \nunmark <valid number>");
                    }
                    int taskNumber = Integer.parseInt(parts[1]);
                    validateMarkNumber(taskNumber, tasks); 
                    tasks.get(taskNumber -1).unmark();
                    System.out.println("Nice! I've marked this task as not done yet:");
                    System.out.println("    "+tasks.get(taskNumber -1).print());
                } catch (NumberFormatException e){
                    System.out.println("Task number must be a number");

                } catch (FeiException e){
                    System.out.println(e.getMessage());
                }
            }
            else if (userInput.startsWith("delete ")){
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new FeiException("Must follow this format: \ndelete <valid number>");
                    }
                    int taskNumber = Integer.parseInt(parts[1]);
                    validateMarkNumber(taskNumber, tasks); 
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    "+tasks.get(taskNumber-1).print());
                    tasks.remove(taskNumber-1);
                    System.out.println("Now you have "+ tasks.size() +" tasks in the list.");


                } catch (NumberFormatException e){
                    System.out.println("Task number must be a number");

                } catch (FeiException e){
                    System.out.println(e.getMessage());
                }
            }

            else if (userInput.startsWith("todo ")){
                try{
                    String task = userInput.substring(5);
                    validateIfEmpty(task,"todo");
                    tasks.add(new Todo(task));
                    appendToFile(fileAddress, "T | 0 | read book" + System.lineSeparator());
                    printConfirmation(tasks);
                } catch (FeiException e){
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                    }
            }

            else if (userInput.startsWith("deadline ")){
                try{
                    String task = userInput.substring(9);
                    validateIfEmpty(task,"deadline");
                    String[] parts = task.split(" /by ");
                    validateDeadline(parts);
                    tasks.add(new Deadline(parts[0],parts[1]));
                    printConfirmation(tasks);
                } catch (FeiException e){
                    System.out.println(e.getMessage());
                }
            }

            else if (userInput.startsWith("event ")){
                try{
                    String task = userInput.substring(6);
                    validateIfEmpty(task,"event");
                    String[] parts = task.split(" /from ");
                    validateEvent(parts);
                    String[] from_to = parts[1].split(" /to ");
                    validateEvent(from_to);
                    tasks.add(new Event(parts[0],from_to[0],from_to[1]));
                    printConfirmation(tasks);   
                } catch (FeiException e){
                    System.out.println(e.getMessage());
                }
            }

            else{
                System.out.println("please enter a valid command"); 
            }

            System.out.println("");
        }
    }
}

