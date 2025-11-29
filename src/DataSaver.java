import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);              // Scanner used by SafeInput
        ArrayList<String> records = new ArrayList<>();
        boolean keepGoing = true;

        while (keepGoing) {
            String first = SafeInput.getNonZeroLenString(in, "Enter First Name: ");
            String last  = SafeInput.getNonZeroLenString(in, "Enter Last Name: ");

            int idNum = SafeInput.getRangedInt(in,
                    "Enter ID Number (1–999999): ", 1, 999999);
            String idFormatted = String.format("%06d", idNum);

            String email = SafeInput.getNonZeroLenString(in, "Enter Email: ");

            int year = SafeInput.getRangedInt(in,
                    "Enter Year of Birth (1900–2024): ", 1900, 2024);

            // Build CSV record
            String record = first + ", " + last + ", " + idFormatted + ", " + email + ", " + year;
            records.add(record);

            keepGoing = SafeInput.getYNConfirm(in, "Add another record");
        }

        // Ask for filename
        String filename = SafeInput.getNonZeroLenString(in,
                "Enter name of CSV file (include .csv): ");

        try (PrintWriter out = new PrintWriter(new FileWriter("src/" + filename))) {
            for (String rec : records) {
                out.println(rec);
            }
            System.out.println("Data saved to src/" + filename);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

        in.close();
    }
}
