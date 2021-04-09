package Model;

import java.text.*;
import java.util.*;

public class Client {
    protected String nume, prenume, CNP, email, numar_telefon;
    protected Date zi_de_nastere;
    protected Adresa adresa;
    protected int id_client;

    protected final List<Cont> conturi = new ArrayList<>();
    protected final Map<String, Cont> conturiMap = new HashMap<>();
    protected final List<ContEconomii> conturi_economii = new ArrayList<>();

    public Client(int id_client, String nume, String prenume, String cnp, Date zdn, String email, String numar_telefon, Adresa adresa) {
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = cnp;
        this.id_client = id_client;
        this.zi_de_nastere = zdn;
        this.adresa = adresa;
        this.email = email;
        this.numar_telefon = numar_telefon;
    }

    public Client(int id_unic, Scanner in) throws ParseException{
        this.id_client = id_unic;
        this.citire(in);
        this.creareCont(this.CNP);
    }

    public void citire(Scanner in) throws ParseException {
        System.out.println("Nume: ");
        this.nume = in.nextLine();
        System.out.println("Prenume: ");
        this.prenume = in.nextLine();
        System.out.println("CNP: ");
        this.CNP = in.nextLine();
        System.out.println("Zi de nastere (yyyy-mm-dd: ");
        this.zi_de_nastere = new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine());
        System.out.println("Email: ");
        this.email = in.nextLine();
        System.out.println("Numar de telefon: ");
        this.numar_telefon = in.nextLine();
        System.out.println("Adresa: ");
        this.adresa = new Adresa(in);
    }

    private final ContFactory contFactory = new ContFactory();
    private final ContEconomiiFactory contEconomiiFactory = new ContEconomiiFactory();

    public void creareCont(String CNP) {
        Cont cont_nou = this.contFactory.creareCont(CNP);
        this.conturi.add(cont_nou);
        this.conturiMap.put(cont_nou.getIBAN(), cont_nou);
    }

    public void creareContEconomii(String CNP) {
        ContEconomii cont_nou_economii = this.contEconomiiFactory.creareContEconomii(CNP);
        this.conturi_economii.add(cont_nou_economii);
    }

    public double calculareSumaTotala() {
        double suma_totala = 0;
        for (var cont : conturi)
            suma_totala += cont.getSuma();
        return suma_totala;
    }

    public void inchidereCont(String IBAN) throws Exception {
        if (this.conturi.size() <= 1)
            throw new Exception("Nu exista niciun cont pentru acest IBAN!");
        if (!this.conturiMap.containsKey(IBAN))
            throw new Exception("IBAN incorect!");

        Cont cont_aproape_inchis = this.conturiMap.get(IBAN);
        if (cont_aproape_inchis.getSuma() != 0)
            throw new Exception("Doriti sa inchideti un cont in care se afla bani!");
        conturi.remove(cont_aproape_inchis);
        conturiMap.remove(IBAN);
    }

    public List<Tranzactie> istoricTranzactii() {
        List<Tranzactie> tranzactii = new ArrayList<>();
        for (var cont : this.conturi)
            tranzactii.addAll(cont.getIstoricTranzactii());
        return tranzactii;
    }

    public List<Tranzactie> istoricTranzactiiPeAni(int an) {
        List<Tranzactie> tranzactii = new ArrayList<>();
        for (var cont : this.conturi)
            tranzactii.addAll(cont.getIstoricTranzactii(an));
        return tranzactii;
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

    public int getId() {
        return id_client;
    }

    public Date getZi_de_nastere() {
        return zi_de_nastere;
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

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public List<Cont> getConturi() {
        return conturi;
    }

    public Map<String, Cont> getConturiMap() {
        return conturiMap;
    }

    public List<ContEconomii> getConturi_economii() {
        return conturi_economii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(nume, client.nume) && Objects.equals(prenume, client.prenume) && Objects.equals(CNP, client.CNP) && Objects.equals(id_client, client.id_client) && Objects.equals(zi_de_nastere, client.zi_de_nastere) && Objects.equals(email, client.email) && Objects.equals(numar_telefon, client.numar_telefon) && Objects.equals(adresa, client.adresa) && Objects.equals(conturi, client.conturi) && Objects.equals(conturiMap, client.conturiMap) && Objects.equals(conturi_economii, client.conturi_economii);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, CNP, id_client, zi_de_nastere, email, numar_telefon, adresa, conturi, conturiMap, conturi_economii);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", CNP='" + CNP + '\'' +
                ", id client='" + id_client + '\'' +
                ", zi_de_nastere=" + zi_de_nastere +
                ", email='" + email + '\'' +
                ", numar_telefon='" + numar_telefon + '\'' +
                ", adresa=" + adresa +
                ", conturi=" + conturi +
                ", conturiMap=" + conturiMap +
                ", conturi_economii=" + conturi_economii +
                '}';
    }
}