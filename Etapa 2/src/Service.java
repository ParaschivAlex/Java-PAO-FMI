import Model.*;

import java.text.ParseException;
import java.util.*;

public class Service {

    protected List<Client> clienti = new ArrayList<>();

    //public List<Client> afiseazaClienti() {return clienti;}

    protected List<Cont> conturi = new ArrayList<>();
    protected List<ContEconomii> conturi_economii = new ArrayList<>();
    protected List<Tranzactie> tranzactii = new ArrayList<>();

    protected final Map<String, Cont> mapConturi = new HashMap<>();

    protected final ClientFactory clientFactory = new ClientFactory();
    protected final ContFactory contFactory = new ContFactory();

    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

    public List<Cont> getConturi() {
        return conturi;
    }

    public void setConturi(List<Cont> conturi) {
        this.conturi = conturi;
    }

    public List<ContEconomii> getConturiEconomii() {
        return conturi_economii;
    }

    public void setConturiEconomii(List<ContEconomii> conturi_economii) {
        this.conturi_economii = conturi_economii;
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    public void setTranzactii(List<Tranzactie> tranzactii) {
        this.tranzactii = tranzactii;
    }

    public void iaConturi() {
        for (var cont : this.conturi) {
            this.mapConturi.put(cont.getIBAN(), cont);
        }
    }

    private Client getClientLista(Scanner in) throws Exception {
        if (this.clienti.size() == 0)
            throw new Exception("Nu exista inca clienti");
        if (this.clienti.size() == 1)
            return clienti.get(0);
        System.out.println("Clienti id [0-" + (this.clienti.size() - 1) + "]: ");
        int client_id = Integer.parseInt(in.nextLine());
        return clienti.get(client_id);
    }

    public void creareClient(Scanner in) throws ParseException {
        Client newClient = clientFactory.creareClient(in);
        this.clienti.add(newClient);
        this.conturi.add(contFactory.creareCont(newClient.getCNP(), newClient.getId_client()));
        System.out.println("Client nou creat cu succes!");
    }

    private Cont getContLista(Scanner in, Client client) throws Exception {
        List<Cont> conturiclient = client.filterConturi(this.conturi);
        System.out.println("Conturile clientului: " + conturiclient);
        System.out.println("Alege IBAN-ul: ");
        var IBAN = in.nextLine();
        if (!this.mapConturi.containsKey(IBAN))
            throw new Exception("IBAN gresit!");
        var cont = mapConturi.get(IBAN);
        if (cont.getId_client() != client.getId_client())
            throw new Exception("Nu exista client care sa aiba IBAN-ul dat!");
        return cont;
    }

    public void afisareClient(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        var cont = this.getContLista(in, client);
        System.out.println(cont.toString());
    }

    public void afisareSoldClient(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        var conturiclient = client.filterConturi(this.conturi);
        double suma_totala = 0;
        for (var cont : conturiclient)
            suma_totala += cont.getSuma();
        System.out.println(client.getNume() + " " + client.getPrenume() + " detine suma totala: " + suma_totala + ".");
    }

    public void creareContClient(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        System.out.println("CNP-ul clientului: ");
        String CNP = in.nextLine();
        Cont newCont = this.contFactory.creareCont(CNP, client.getId_client());
        conturi.add(newCont);
        mapConturi.put(newCont.getIBAN(), newCont);
        System.out.println("Cont nou normal creat cu succes!");
    }

    public void creareContEconomiiClient(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        System.out.println("CNP-ul clientului: ");
        String CNP = in.nextLine();
        Cont newCont = this.contFactory.creareContEconomii(CNP, client.getId_client());
        conturi.add(newCont);
        mapConturi.put(newCont.getIBAN(), newCont);
        System.out.println("Cont nou de economii creat cu succes!");
    }

    public void creareCardClient(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        var cont = this.getContLista(in, client);
        System.out.println("CNP-ul clientului: ");
        var CNP = in.nextLine();
        cont.creareCard(CNP);
    }

    public void depozitContClient(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        var contclient = client.filterConturi(this.conturi);
        System.out.println("Suma pentru depozitare: ");
        double suma = Double.parseDouble(in.nextLine());
        System.out.println("In ce cont doriti sa depozitati(Alegeti un numar de la 0 la X)?" + contclient);
        int alegere = Integer.parseInt(in.nextLine());

            contclient.get(alegere).setSuma(suma + contclient.get(alegere).getSuma());
        System.out.println("Contul a fost alimentat!");
    }

    public void creareTranzactie(Scanner in) throws Exception {
        System.out.println("Din contul cu IBAN: ");
        var IBAN1 = in.nextLine();
        System.out.println("Spre contul cu IBAN: ");
        var IBAN2 = in.nextLine();
        System.out.println("Suma de transferat: ");
        double suma = Double.parseDouble(in.nextLine());
        Cont cont1 = null, cont2 = null;
        if (mapConturi.containsKey(IBAN1))
            cont1 = mapConturi.get(IBAN1);
        if (mapConturi.containsKey(IBAN2))
            cont2 = mapConturi.get(IBAN2);
        if (IBAN1.equals(IBAN2))
            throw new Exception("Nu puteti face o tranzactie de la contul X tot la contul X");
        if (cont1 == null || cont2 == null)
            throw new Exception("Nu se pot gasi IBAN-urile!");
        if (cont1.getSuma() < suma)
            throw new Exception("Sold insuficient!");

        cont1.setSuma(cont1.getSuma() - suma);
        cont2.setSuma(cont2.getSuma() + suma);

        Date data_acum = new Date();
        this.tranzactii.add(new Tranzactie(IBAN1, IBAN2, suma, data_acum));
        System.out.println("Tranzactie procesata!");
    }

    public void inchidereCont(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        var cont = this.getContLista(in, client);
        if (client.filterConturi(this.conturi).size() <= 1)
            throw new Exception("Clientul nu are niciun cont deschis!");
        if (cont.getSuma() > 0)
            throw new Exception("Contul mai are bani pe el");
        this.mapConturi.remove(cont.getIBAN());
        this.conturi.remove(cont);
        System.out.println("Cont sters!");
    }

    public void afisareTranzactiiClient(Scanner in) throws Exception {
        var client = this.getClientLista(in);
        System.out.println("Afisati toate tranzactiile? (0 sau 1)");
        var ok = in.nextInt();
        if (ok == 1)
            try {
                System.out.println(client.filterTranzactii(conturi, tranzactii));
            } catch (Exception e) {
                System.out.println("Nu exista tranzactii sau a intervenit alta problema!");
            }

        else {
            try {
                System.out.println("Pentru ce an doriti afisarea?: ");
                var an = in.nextInt();
                System.out.println(client.filterTranzactii(conturi, tranzactii, an));
            } catch (Exception e) {
                System.out.println("Nu exista tranzactii sau a intervenit alta problema!");
            }
        }
        System.out.println();
    }

}