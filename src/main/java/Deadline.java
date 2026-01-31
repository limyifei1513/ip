public class Deadline extends Task {

    protected String by;
    
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, Boolean isDone) {
        super(description,isDone);
        this.by = by;
    }

    @Override
    public String print() {
        return "[D]" + super.print() + " (by: " + by + ")";
    }
}
