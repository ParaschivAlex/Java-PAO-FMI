package Model;

import java.io.*;
import java.text.*;
import java.util.*;

public class ContEconomiiSingleton { //https://www.geeksforgeeks.org/singleton-class-java/

    private static ContEconomiiSingleton single_instance = null;

    final private ContFactory contFactory = new ContFactory();

    private List<ContEconomii> conturieconomii = new ArrayList<ContEconomii>();

    public List<ContEconomii> getConturieconomii() {
        return conturieconomii;
    }

    public void setConturieconomii(List<ContEconomii> conturieconomii) {
        this.conturieconomii = conturieconomii;
    }

    public static ContEconomiiSingleton getInstance() {
        if (single_instance == null)
            single_instance = new ContEconomiiSingleton();
        return single_instance;
    }

    private static List<String[]> getCSV(String fileName) {

        List<String[]> columns = new ArrayList<>();

        try (var in = new BufferedReader(new FileReader(fileName))) { //https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

            String line;

            while ((line = in.readLine()) != null) {
                String[] fields = line.replaceAll(" ", "").split(",");
                columns.add(fields);
            }
        } catch (IOException e) {
            System.out.println("Error!");
        }

        return columns;
    }

    public void loadCSV() { //https://www.javatpoint.com/how-to-read-csv-file-in-java
        try {
            var columns = ContEconomiiSingleton.getCSV("data/conturiEconomii.csv");
            for (var fields : columns) {
                var newContEconomii = new ContEconomii(fields[0], Double.parseDouble(fields[1]), fields[2], Integer.parseInt(fields[3]), new SimpleDateFormat("yyyy-MM-dd").parse(fields[4]), new SimpleDateFormat("yyyy-MM-dd").parse(fields[5]), Double.parseDouble(fields[6]));
                conturieconomii.add(newContEconomii);
            }
            ContFactory.increment_id_unic(columns.size());
        } catch (ParseException e) {
            System.out.println(e.toString());
        }

    }

    public void writeCSV() {
        try {
            var writer = new FileWriter("data/conturiEconomii.csv");
            for (var contecon : this.conturieconomii) {
                writer.write(contecon.CSV());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}