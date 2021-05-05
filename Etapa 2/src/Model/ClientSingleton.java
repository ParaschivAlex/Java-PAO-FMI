package Model;

import java.io.*;
import java.text.*;
import java.util.*;

public class ClientSingleton { //https://www.geeksforgeeks.org/singleton-class-java/

    private static ClientSingleton single_instance = null;

    final private ClientFactory clientFactory = new ClientFactory();

    private List<Client> clienti = new ArrayList<Client>();

    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }//generate

    public static ClientSingleton getInstance() {
        if (single_instance == null)
            single_instance = new ClientSingleton();
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
            var columns = ClientSingleton.getCSV("data/clienti.csv");
            for (var fields : columns) {
                var newCustomer = new Client(fields[0], fields[1], fields[2], fields[3], fields[4], new SimpleDateFormat("yyyy-MM-dd").parse(fields[5]), new Adresa(fields[6], fields[7], fields[8], fields[9], Integer.parseInt(fields[10])), Integer.parseInt(fields[11]));
                clienti.add(newCustomer);
            }
            ClientFactory.increment_id_unic(columns.size());
        } catch (ParseException e) {
            System.out.println(e.toString());
        }

    }

    public void writeCSV() {
        try {
            var writer = new FileWriter("data/clienti.csv");
            for (var customer : this.clienti) {
                writer.write(customer.CSV());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}