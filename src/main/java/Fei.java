import java.util.Scanner;

public class Fei {

    public void sayHi(){
        System.out.println("Hello! I'm Fei\nWhat can I do for you?");
    }

    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fei fei = new Fei();
        fei.sayHi();
        while (true){
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")){
                fei.sayBye();
                break;
            }
            else{
                System.out.println("You said: " + userInput);
            }
            
        }
    }
}
