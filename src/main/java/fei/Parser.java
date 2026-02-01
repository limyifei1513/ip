package fei;

public class Parser {

    /**
     * Validates that the task number is within range.
     */
    public void validateTaskNumber(int num, TaskList tasks) throws FeiException {
        if (num < 1 || num > tasks.getSize()) {
            throw new FeiException("That task number is out of range.");
        }
    }

    /**
     * Validates that the task description is not empty.
     *
     * @param description The description to validate.
     * @param task        The type of task (e.g., "todo", "deadline", "event").
     * @throws FeiException if the description is empty.
     */
    public void validateNotEmpty(String description, String taskType) throws FeiException {
        if (description.isEmpty()) {
            throw new FeiException("A/An " + taskType + " must have a description.");
        }
    }

    /**
     * Validates the format of the deadline task input.
     *
     * @param parts The parts of the deadline input split by " /by ".
     * @throws FeiException if the format is incorrect.
     */
    public void validateDeadlineFormat(String[] parts) throws FeiException {
        if (parts.length < 2) {
            throw new FeiException(
                "Must follow this format:\ndeadline <description> /by <date>"
            );
        }
    }

    /**
     * Validates the format of the event task input.
     *
     * @param parts The parts of the event input split by " /from ".
     * @throws FeiException if the format is incorrect.
     */
    public void validateEventFormat(String[] parts) throws FeiException {
        if (parts.length < 2) {
            throw new FeiException(
                "Must follow this format:\nevent <description> /from <date> /to <date>"
            );
        }
    }

    /**
     * Validates that the task number is within the valid range of the task list.
     *
     * @param num   The task number to validate.
     * @param tasks The list of tasks.
     * @throws FeiException if the task number is out of range.
     */
    public void validateMarkNumber(int num, TaskList tasks) throws FeiException {
        if (num > tasks.getSize() || num < 1) {
            throw new FeiException("That task number is out of range.");
        }
    }
}
