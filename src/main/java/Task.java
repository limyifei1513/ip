public class Task {
    String name;
    String done;

    public Task(String name) {
        this.name = name;
        done = "[ ] ";
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