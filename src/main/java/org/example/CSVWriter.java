package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

class CSVToProtobuf {

    // A constant for the comma delimiter
    public static final String COMMA_DELIMITER = ",";

    // A method to convert a CSV file to a protobuf file
    public static void convert(String csvFileName, String protoFileName) throws IOException {
        // Create an input stream for reading the CSV file
        FileInputStream fis = new FileInputStream(new File(csvFileName));

        // Create a buffered reader for reading lines from the input stream
        BufferedReader br = new BufferedReader(new FileReader(csvFileName));

        // Create an output stream for writing the protobuf file
        FileOutputStream fos = new FileOutputStream(new File(protoFileName));

        // Read the first line of the CSV file (header)
        String line = br.readLine();

        // Loop through the remaining lines of the CSV file (data)
        while ((line = br.readLine()) != null) {
            // Split the line by comma delimiter
            String[] values = line.split(COMMA_DELIMITER);

            // Create a Person.Builder object
            PersonOuterClass.Person.Builder personBuilder;
            personBuilder = PersonOuterClass.Person.newBuilder();

            // Set the fields of the Person.Builder object with the values from the CSV row
            personBuilder.setName(values[0]);
            personBuilder.setAge(Integer.parseInt(values[1]));
            personBuilder.setOccupation(values[2]);

            // Build a Person message from the Person.Builder object
            PersonOuterClass.Person person;
            person = personBuilder.build();

            // Write the Person message to the output stream
            person.writeTo(fos);
        }

        // Close the input and output streams
        br.close();
        fis.close();
        fos.close();
    }

    // A main method to test the conversion
    public static void main(String[] args) throws IOException {
        // Convert a CSV file named "input.csv" to a protobuf file named "output.pb"
        convert("/home/javad/IdeaProjects/javaTest/test2/src/main/java/org/example/input.csv", "output.pb");
    }
}