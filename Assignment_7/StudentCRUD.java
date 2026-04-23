import java.io.*;
import java.util.*;

public class StudentCRUD {

    static String fileName = "C:\\Users\\mailv\\Desktop\\SIT\\SEM4\\Labs\\Java Lab\\7\\Students.csv";

    public static void main(String[] args) {

        try {
            displayFile("Initial File");

            addMoreStudents();
            displayFile("After Adding 3 Students");

            updateMarks();
            displayFile("After Updating Marks");

            calculatePercentage();
            displayFile("After Calculating Percentage");

            deleteStudent(3); // delete student with ID 3
            displayFile("After Deleting Student with ID 3");

            // Exception demo
            readNonExistingFile();

        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }

    // Add 3 rows
    static void addMoreStudents() throws IOException {
        FileWriter fw = new FileWriter(fileName, true);

        fw.write("5,Rahul,IT,70,75,80,0,0,0\n");
        fw.write("6,Priya,CS,85,90,88,0,0,0\n");
        fw.write("7,Karan,MECH,60,65,70,0,0,0\n");

        fw.close();
    }

    // Update marks4 & marks5
    static void updateMarks() throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = br.readLine();
        lines.add(line);

        Random rand = new Random();

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            data[6] = String.valueOf(60 + rand.nextInt(40));
            data[7] = String.valueOf(60 + rand.nextInt(40));

            lines.add(String.join(",", data));
        }
        br.close();

        FileWriter fw = new FileWriter(fileName);
        for (String l : lines) {
            fw.write(l + "\n");
        }
        fw.close();
    }

    // Calculate percentage
    static void calculatePercentage() throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = br.readLine();
        lines.add(line);

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            int sum = 0;
            for (int i = 3; i <= 7; i++) {
                sum += Integer.parseInt(data[i]);
            }

            double percentage = sum / 5.0;
            data[8] = String.format("%.2f", percentage);

            lines.add(String.join(",", data));
        }
        br.close();

        FileWriter fw = new FileWriter(fileName);
        for (String l : lines) {
            fw.write(l + "\n");
        }
        fw.close();
    }

    // Delete row
    static void deleteStudent(int id) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = br.readLine();
        lines.add(line);

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (Integer.parseInt(data[0]) != id) {
                lines.add(line);
            }
        }
        br.close();

        FileWriter fw = new FileWriter(fileName);
        for (String l : lines) {
            fw.write(l + "\n");
        }
        fw.close();
    }

    // Display file
    static void displayFile(String msg) throws IOException {
        System.out.println("\n--- " + msg + " ---");

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    // Exception demo
    static void readNonExistingFile() throws IOException {
        FileReader fr = new FileReader("NoFile.csv");
        fr.close();
    }
}