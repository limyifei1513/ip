import java.util.Scanner;
import java.util.ArrayList;

public class Fei {

    public void sayHi(){
        System.out.println("Hello! I'm Fei\nWhat can I do for you?");
    }

    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(ArrayList<Task> tasks){
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fei fei = new Fei();
        ArrayList<Task> tasks = new ArrayList<>();
        fei.sayHi();

        while (true){
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")){
                fei.sayBye();
                break;
            }
            else if (userInput.equals("list")){
                fei.showList(tasks); 
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
                    System.out.println("Got it. I've added this task:");
                    int size = tasks.size();
                    System.out.println("    "+tasks.get(size-1).print());
                    System.out.println("Now you have "+ size +" tasks in the list.");
                } catch (FeiException e){
                    System.out.println(e.getMessage());
                }
            }

            else if (userInput.startsWith("deadline ")){
                try{
                    String task = userInput.substring(9);
                    validateIfEmpty(task,"deadline");
                    String[] parts = task.split(" /by ");
                    validateDeadline(parts);
                    tasks.add(new Deadline(parts[0],parts[1]));
                    System.out.println("Got it. I've added this task:");
                    int size = tasks.size();
                    System.out.println("    "+tasks.get(size-1).print());
                    System.out.println("Now you have "+ size +" tasks in the list.");
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
                    System.out.println("Got it. I've added this task:");
                    int size = tasks.size();
                    System.out.println("    "+tasks.get(size-1).print());
                    System.out.println("Now you have "+ size +" tasks in the list.");
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

