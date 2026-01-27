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
                String[] parts = userInput.split(" ");
                int taskNumber = Integer.parseInt(parts[1]); 
                tasks.get(taskNumber -1).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    "+tasks.get(taskNumber -1).print());
            }

            else if (userInput.startsWith("unmark ")){
                String[] parts = userInput.split(" ");
                int taskNumber = Integer.parseInt(parts[1]); 
                tasks.get(taskNumber -1).unmark();
                System.out.println("Nice! I've marked this task as not done yet:");
                System.out.println("    "+tasks.get(taskNumber -1).print());
            }

            else if (userInput.startsWith("todo ")){
                String task = userInput.substring(5);
                tasks.add(new Todo(task));
                System.out.println("Got it. I've added this task:");
                int size = tasks.size();
                System.out.println("    "+tasks.get(size-1).print());
                System.out.println("Now you have "+ size +" tasks in the list.");
            }

            else if (userInput.startsWith("deadline ")){
                String task = userInput.substring(9);
                String[] parts = task.split(" /by ");
                tasks.add(new Deadline(parts[0],parts[1]));
                System.out.println("Got it. I've added this task:");
                int size = tasks.size();
                System.out.println("    "+tasks.get(size-1).print());
                System.out.println("Now you have "+ size +" tasks in the list.");
            }

            else if (userInput.startsWith("event ")){
                String task = userInput.substring(6);
                String[] parts = task.split(" /from ");
                String[] from_to = parts[1].split(" /to ");
                tasks.add(new Event(parts[0],from_to[0],from_to[1]));
                System.out.println("Got it. I've added this task:");
                int size = tasks.size();
                System.out.println("    "+tasks.get(size-1).print());
                System.out.println("Now you have "+ size +" tasks in the list.");
            }

            else{
                System.out.println("error, please reinput the command");
            }

            System.out.println("");
        }
    }
}

