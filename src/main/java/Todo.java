public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public Todo(String description,Boolean isDone) {
        super(description,isDone);
    }

    @Override
    public String print() {
        return "[T]" + super.print();
    }
}
