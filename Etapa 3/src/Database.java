import Model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

    Connection connection;
    CallableStatement spro = null;

    public Database() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect", "root", "parola");
    }

    public void creareTabele() throws Exception {
        List<String> tabele = new ArrayList<>();
        tabele.add("CREATE TABLE Clienti (nume varchar(255), prenume varchar(255), CNP varchar(13), email varchar(255), " +
                "numar_telefon varchar(10), zi_de_nastere varchar(10), tara varchar(255), oras varchar(255)" +
                "strada varchar(255), cod_postal varchar(6), nr varchar(4), id_client varchar(255) primary key))");
        tabele.add("CREATE TABLE Conturi (IBAN varchar(255) primary key, suma varchar(255), CNP varchar(13), id_client varchar(255))");
        tabele.add("CREATE TABLE ConturiEconomii (data_inceput varchar(255), data_incheiere varchar(255), dobanda varchar(255))");
        tabele.add("CREATE TABLE Tranzactii (IBAN_trimite varchar(255), IBAN_primeste varchar(255), suma varchar(255), data_tranzactie varchar(255))");
        connection.createStatement().execute(tabele.get(0));
        connection.createStatement().execute(tabele.get(1));
        connection.createStatement().execute(tabele.get(2));
        connection.createStatement().execute(tabele.get(3));
    }

    //------------------Client-----------------

    ClientFactory clientFactory = new ClientFactory();

    public List<Client> readClient() {
        List<Client> clienti = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from Clienti";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client curent = clientFactory.creareClient(rs);
                clienti.add(curent);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return clienti;
    }

    public void updateClient(Client creareClient) {
        PreparedStatement ps = null;
        try {
            String query = "update Clienti set nume = ?, nume = ?, CNP = ?, zi_de_nastere = ?, email = ?, numar_telefon = ?, tara = ?, oras = ?, strada = ?, cod_postal = ?, nr = ? where id_client = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, creareClient.getNume());
            ps.setString(2, creareClient.getPrenume());
            ps.setString(3, creareClient.getCNP());
            ps.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(creareClient.getZi_de_nastere()));
            ps.setString(5, creareClient.getEmail());
            ps.setString(6, creareClient.getNumar_telefon());
            ps.setString(7, creareClient.getAdresa().getTara());
            ps.setString(8, creareClient.getAdresa().getOras());
            ps.setString(9, creareClient.getAdresa().getStrada());
            ps.setString(10, creareClient.getAdresa().getCod_postal());
            ps.setInt(11, creareClient.getAdresa().getNr());
            ps.setInt(12, creareClient.getId_client());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createClient(Client client) {
        PreparedStatement ps = null;
        try {
            String query = "insert into Clienti (nume, nume, CNP, zi_de_nastere, email, numar_telefon, tara, oras, strada, cod_postal, nr) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, client.getNume());
            ps.setString(2, client.getPrenume());
            ps.setString(3, client.getCNP());
            ps.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(client.getZi_de_nastere()));
            ps.setString(5, client.getEmail());
            ps.setString(6, client.getNumar_telefon());
            ps.setString(7, client.getAdresa().getTara());
            ps.setString(8, client.getAdresa().getOras());
            ps.setString(9, client.getAdresa().getStrada());
            ps.setString(10, client.getAdresa().getCod_postal());
            ps.setInt(11, client.getAdresa().getNr());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void deleteClient(Client client) {
        PreparedStatement ps = null;
        try {
            String query = "delete from Clienti where id_client = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, client.getId_client());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //----------------------------Cont---------------------------

    public List<Cont> readCont() {
        List<Cont> conturi = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from Conturi";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cont curent = new Cont(rs);
                conturi.add(curent);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return conturi;
    }

    public void updateCont(Cont creareCont) {
        PreparedStatement ps = null;
        try {
            String query = "update Conturi set suma = ?, CNP = ?, id_client = ? where IBAN = ?";
            ps = connection.prepareStatement(query);
            ps.setDouble(1, creareCont.getSuma());
            ps.setString(2, creareCont.getCNP());
            ps.setInt(3, creareCont.getId_client());
            ps.setString(4, creareCont.getIBAN());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createCont(Cont cont) {
        PreparedStatement ps = null;
        try {
            String query = "insert into Conturi (IBAN, suma, CNP, id_client) values (?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, cont.getIBAN());
            ps.setDouble(2, cont.getSuma());
            ps.setString(3, cont.getCNP());
            ps.setInt(4, cont.getId_client());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void deleteCont(Cont cont) {
        PreparedStatement ps = null;
        try {
            String query = "delete from Conturi where IBAN = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, cont.getIBAN());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //-------------Tranzactie--------

    public List<Tranzactie> readTranzactie() {
        List<Tranzactie> tranzactii = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from Tranzactii";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tranzactie curent = new Tranzactie(rs);
                tranzactii.add(curent);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return tranzactii;
    }

    public void updateTranzactie(Tranzactie creareTranzactie) {
        PreparedStatement ps = null;
        try {
            String query = "update Tranzactii set IBAN_trimite = ?, IBAN_primeste = ?, suma = ? where data_tranzactie = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, creareTranzactie.getIBAN_primeste());
            ps.setString(2, creareTranzactie.getIBAN_trimite());
            ps.setDouble(3, creareTranzactie.getSuma());
            ps.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(creareTranzactie.getData_tranzactie()));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createTranzactie(Tranzactie tranzactie) {
        PreparedStatement ps = null;
        try {
            String query = "insert into Tranzactii (IBAN_trimite, IBAN_primeste, suma, data_tranzactie) values (?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, tranzactie.getIBAN_trimite());
            ps.setString(2, tranzactie.getIBAN_primeste());
            ps.setDouble(3, tranzactie.getSuma());
            ps.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(tranzactie.getData_tranzactie()));
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void deleteTranzactie(Tranzactie tranzactie) {
        PreparedStatement ps = null;
        try {
            String query = "delete from Tranzactii where IBAN_trimite = ?,IBAN_primeste = ?, data_tranzactie = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, tranzactie.getIBAN_trimite());
            ps.setString(2, tranzactie.getIBAN_primeste());
            ps.setString(3, (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(tranzactie.getData_tranzactie()));
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //---------ContEconomii---------

    public List<ContEconomii> readContEconomii() {
        List<ContEconomii> conturieconomii = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from ConturiEconomii";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ContEconomii curent = new ContEconomii(rs);
                conturieconomii.add(curent);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return conturieconomii;
    }

    public void updateContEconomii(ContEconomii creareContEconomii) {
        PreparedStatement ps = null;
        try {
            String query = "update ConturiEconomii set suma = ?, CNP = ?, id_client = ?, data_inceput = ?, data_incheiere = ?, dobanda = ? where IBAN = ?";
            ps = connection.prepareStatement(query);
            ps.setDouble(1, creareContEconomii.getSuma());
            ps.setString(2, creareContEconomii.getCNP());
            ps.setInt(3, creareContEconomii.getId_client());
            ps.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(creareContEconomii.getData_inceput()));
            ps.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(creareContEconomii.getData_incheiere()));
            ps.setDouble(6, creareContEconomii.getDobanda());
            ps.setString(7, creareContEconomii.getIBAN());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createContEconomii(ContEconomii contEconomii) {
        PreparedStatement ps = null;
        try {
            String query = "insert into ConturiEconpmii (IBAN, suma, CNP, id_client, data_inceput, data_incheiere, dobanda) values (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, contEconomii.getIBAN());
            ps.setDouble(2, contEconomii.getSuma());
            ps.setString(3, contEconomii.getCNP());
            ps.setInt(4, contEconomii.getId_client());
            ps.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(contEconomii.getData_inceput()));
            ps.setString(6, (new SimpleDateFormat("yyyy-MM-dd")).format(contEconomii.getData_incheiere()));
            ps.setDouble(7, contEconomii.getDobanda());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void deleteContEconomii(ContEconomii contEconomii) {
        PreparedStatement ps = null;
        try {
            String query = "delete from ConturiEconpmii where IBAN = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, contEconomii.getIBAN());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
