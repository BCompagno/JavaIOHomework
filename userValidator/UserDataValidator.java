package userValidator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataValidator {

	public static void main(String[] args) {
			
		try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/user_data.txt"));
            BufferedWriter validWriter = new BufferedWriter(new FileWriter("src/data/valid_data.txt"));
            BufferedWriter errorWriter = new BufferedWriter(new FileWriter("src/data/error_data.txt"));

            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        // Split the line into name, email, and age
                        String[] userData = line.split(",");
                        if (userData.length != 3) {
                            throw new Exception("Missing Data");
                        }

                        // Extract and trim individual pieces of data
                        String name = userData[0].trim();
                        String email = userData[1].trim();
                        int age = Integer.parseInt(userData[2].trim());

                        // Check for invalid age
                        if (age <= 0) {
                            throw new Exception("Invalid Age");
                        }

                        // If all checks passed, write to valid data file
                        validWriter.write(name + ", " + email + ", " + age);
                        validWriter.newLine();
                    } catch (Exception e) {
                        // Write faulty line with error message to error data file
                        errorWriter.write(line + " - " + e.getMessage());
                        errorWriter.newLine();
                    }
                }
            } finally {
                // Close readers and writers in a finally block to ensure they are closed
                try {
                    reader.close();
                    validWriter.close();
                    errorWriter.close();
                } catch (IOException e) {
                    System.err.println("Error closing readers/writers: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            // Handle file-related exceptions
            System.err.println("Error reading/writing files: " + e.getMessage());
        }
    }
}