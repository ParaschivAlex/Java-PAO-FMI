package Model;

import java.text.*;
import java.util.*;

public class Client {
    protected String nume, prenume, CNP, email, numar_telefon;
    protected Date zi_de_nastere;
    protected Adresa adresa;
    protected final int id_client;

    public Client(String nume, String prenume, String CNP, String email, String numar_telefon, Date zi_de_nastere, Adresa adresa, int id_client) {
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.email = email;
        this.numar_telefon = numar_telefon;
        this.zi_de_nastere = zi_de_nastere;
        this.adresa = adresa;
        this.id_client = id_client;
    }

    public Client(int id_client, Scanner in) throws ParseException {
        this.id_client = id_client;
        this.citire(in);
    }

    public void citire(Scanner in) throws ParseException {
        System.out.println("Nume: ");
        this.nume = in.nextLine();
        System.out.println("Prenume: ");
        this.prenume = in.nextLine();
        System.out.println("CNP: ");
        this.CNP = in.nextLine();
        System.out.println("Zi de nastere (yyyy-mm-dd): ");
        this.zi_de_nastere = new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine());
        System.out.println("Email: ");
        this.email = in.nextLine();
        System.out.println("Numar de telefon: ");
        this.numar_telefon = in.nextLine();
        System.out.println("Adresa: ");
        this.adresa = new Adresa(in);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumar_telefon() {
        return numar_telefon;
    }

    public void setNumar_telefon(String numar_telefon) {
        this.numar_telefon = numar_telefon;
    }

    public Date getZi_de_nastere() {
        return zi_de_nastere;
    }

    public void setZi_de_nastere(Date zi_de_nastere) {
        this.zi_de_nastere = zi_de_nastere;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public int getId_client() {
        return id_client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id_client == client.id_client && Objects.equals(nume, client.nume) && Objects.equals(prenume, client.prenume) && Objects.equals(CNP, client.CNP) && Objects.equals(email, client.email) && Objects.equals(numar_telefon, client.numar_telefon) && Objects.equals(zi_de_nastere, client.zi_de_nastere) && Objects.equals(adresa, client.adresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, CNP, email, numar_telefon, zi_de_nastere, adresa, id_client);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", CNP='" + CNP + '\'' +
                ", email='" + email + '\'' +
                ", numar_telefon='" + numar_telefon + '\'' +
                ", zi_de_nastere=" + zi_de_nastere +
                ", adresa=" + adresa +
                ", id_client=" + id_client +
                '}';
    }

    public String CSV() {
        return id_client +
                "," + nume +
                "," + prenume +
                "," + CNP +
                "," + zi_de_nastere +
                "," + email +
                "," + numar_telefon +
                "," + adresa.CSV();
    }

    public List<Cont> filterConturi(List<Cont> toate_conturile) { //caut conturile unei persoane
        var conturi = new ArrayList<Cont>();
        for (var cont : toate_conturile)
            if (cont.getId_client() == this.getId_client())
                conturi.add(cont);
        return conturi;
    }

    public List<Tranzactie> filterTranzactii(List<Cont> toate_conturile, List<Tranzactie> toate_tranzactiile) {
        var tranzactii = new ArrayList<Tranzactie>();
        var conturi = this.filterConturi(toate_conturile);
        for (var cont : conturi)
            tranzactii.addAll(cont.filterTranzactii(toate_tranzactiile));
        return tranzactii;
    }

    public List<Tranzactie> filterTranzactii(List<Cont> toate_conturile, List<Tranzactie> toate_tranzactiile, int an) {
        var tranzactii = new ArrayList<Model.Tranzactie>();
        var conturi = this.filterConturi(toate_conturile);
        for (var cont : conturi)
            tranzactii.addAll(cont.filterTranzactii(toate_tranzactiile, an));
        return tranzactii;
    }

}