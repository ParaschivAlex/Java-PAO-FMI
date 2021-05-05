package Model;

import java.io.*;
import java.text.*;
import java.util.*;

//prima clasa singleton creata a fost la client si la restul am dat copy paste de acolo si am modificat putin

public class ContSingleton { //https://www.geeksforgeeks.org/singleton-class-java/

    private static ContSingleton single_instance = null;

    final private ContFactory contFactory = new ContFactory();

    private List<Cont> conturi = new ArrayList<Cont>();

    public List<Cont> getConturi() {
        return conturi;
    }

    public void setConturi(List<Cont> conturi) {
        this.conturi = conturi;
    }

    public static ContSingleton getInstance() {
        if (single_instance == null)
            single_instance = new ContSingleton();
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
        var columns = ContSingleton.getCSV("data/conturi.csv");
        for (var fields : columns) {
            var newCont = new Cont(fields[0], Double.parseDouble(fields[1]), fields[2], Integer.parseInt(fields[3]));
            conturi.add(newCont);
        }
        ContFactory.increment_id_unic(columns.size());

    }

    public void writeCSV() {
        try {
            var writer = new FileWriter("data/conturi.csv");
            for (var cont : this.conturi) {
                writer.write(cont.CSV());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}