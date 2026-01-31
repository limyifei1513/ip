public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public Event(String description, String from, String to, Boolean isDone) {
        super(description,isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String print() {
        return "[E]" + super.print() + " (from: " + from + " to: " + to +")";
    }
}
