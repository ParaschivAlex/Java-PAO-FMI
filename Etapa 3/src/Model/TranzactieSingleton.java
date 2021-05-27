package Model;

import java.io.*;
import java.text.*;
import java.util.*;

public class TranzactieSingleton { //https://www.geeksforgeeks.org/singleton-class-java/

    private static TranzactieSingleton single_instance = null;

    final private ContFactory contFactory = new ContFactory();
    private List<Tranzactie> tranzactii = new ArrayList<Tranzactie>();

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    public void setTranzactii(List<Tranzactie> tranzactii) {
        this.tranzactii = tranzactii;
    }

    public static TranzactieSingleton getInstance() {
        if (single_instance == null)
            single_instance = new TranzactieSingleton();
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
            var columns = TranzactieSingleton.getCSV("data/tranzactii.csv");
            for (var fields : columns) {
                var newTranzactie = new Tranzactie(fields[0], fields[1], Double.parseDouble(fields[2]), new SimpleDateFormat("yyyy-MM-dd").parse(fields[3]));
                tranzactii.add(newTranzactie);
            }
            //TrazactieFactory.increment_id_unic(columns.size());
        } catch (ParseException e) {
            System.out.println(e.toString());
        }

    }

    public void writeCSV() {
        try {
            var writer = new FileWriter("data/tranzactii.csv");
            for (var tranzactie : this.tranzactii) {
                writer.write(tranzactie.CSV());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}