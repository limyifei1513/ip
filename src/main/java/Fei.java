import java.util.Scanner;
import java.util.ArrayList;

public class Fei {

    public void sayHi(){
        System.out.println("Hello! I'm Fei\nWhat can I do for you?");
    }

    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(ArrayList<String> tasks){
        for (int i = 1; i <= tasks.size();i++){
            System.out.println(i +". " + tasks.get(i-1));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fei fei = new Fei();
        ArrayList<String> tasks = new ArrayList<>();

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
            else{
                tasks.add(userInput);
                System.out.println("added: " + userInput);
            }
            
        }
    }
}
