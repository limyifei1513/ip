public class Task {
    String name;
    String done;

    public Task(String name) {
        this.name = name;
        done = "[ ] ";
    }

    public Task(String name, Boolean isDone) {
        this.name = name;

        if (isDone){
            this.mark();
        }
        else{
            this.done = "[ ] ";
        }
    }

    public String print() {
        return done + name;
    }

    public void mark(){
        done = "[X] ";
    }

    public void unmark(){
        done = "[ ] ";
    }
}