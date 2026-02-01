package fei;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file into the task list.
     *
     * @param filePath The path to the data file.
     * @param tasks    The task list to load tasks into.
     * @throws FileNotFoundException if the file cannot be found.
     */
    TaskList loadContents() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String data = s.nextLine();
            String[] dataParts = data.split(" // ");
            Boolean isDone = Boolean.parseBoolean(dataParts[1]);

            if (dataParts[0].equals("T")) {
                tasks.add(new Todo(dataParts[2], isDone));
            } else if (dataParts[0].equals("D")) {
                tasks.add(new Deadline(dataParts[2], dataParts[3], isDone));
            } else if (dataParts[0].equals("E")) {
                tasks.add(new Event(dataParts[2], dataParts[3], dataParts[4], isDone));
            } else {
                System.out.println("File is corrupted, can't read the file");
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Appends the given text to the specified file.
     *
     * @param filePath     The path to the file.
     * @param textToAppend The text to append.
     * @throws IOException if an I/O error occurs.
     */
    void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
    
    /**
     * Rewrites the entire file with the current list of tasks.
     *
     * @param filePath The path to the file.
     * @param tasks    The list of tasks to write.
     * @throws IOException if an I/O error occurs.
     */
    void rewriteFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (Task task : tasks.getTasks()) {
            fw.write(task.toFileString());
        }
        fw.close();
    }
    
}
