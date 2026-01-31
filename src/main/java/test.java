import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class test {

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String data = s.nextLine();
            System.out.println(data);
            String[] dataParts = data.split(" // ");
            for (String x : dataParts){
                System.out.println(x);
            }
        }
    }
    public static void main(String[] args){
        String fileAddress = System.getProperty("user.dir") + "/data.txt";
        try {
            if (!Files.exists(Paths.get(fileAddress))) {
                Files.createFile(Paths.get(fileAddress));
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + e.getMessage());
        }
        File dataFile = new File(fileAddress);
        
        System.out.println(dataFile.exists());
        try {
            appendToFile(fileAddress, "T | 0 | read book" + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        try {
            printFileContents(fileAddress);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}
   
