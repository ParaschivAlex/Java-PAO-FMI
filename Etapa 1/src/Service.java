import Model.*;

import java.text.ParseException;
import java.util.*;

public class Service {
    private final List<Client> clienti = new ArrayList<>();

    public List<Client> afiseazaClienti() {
        return clienti;
    }

    private Client getClient(Scanner in) throws Exception{
        if(this.clienti.size()==0)
            throw new Exception("Nu exista inca clienti");
        if(this.clienti.size()==1)
            return clienti.get(0);
        System.out.println("Clienti id [0-"+(this.clienti.size()-1)+"]: ");
        int client_id = Integer.parseInt(in.nextLine());
        return clienti.get(client_id);
    }

    private Cont getCont(Scanner in, Client client) throws Exception{
        System.out.println("Conturile clientului: " + client.getConturi().toString());
        System.out.println("Alegeti IBAN-ul: ");
        var IBAN = in.nextLine();
        return client.getConturiMap().get(IBAN);
    }

    private final ClientFactory clientFactory = new ClientFactory();
    public void creareClient(Scanner in) throws ParseException {
        this.clienti.add(clientFactory.creareClient(in));
        System.out.println("Client nou creat cu succes!");
    }

    public void afisareClient(Scanner in) throws Exception {
        var client = this.getClient(in);
        System.out.println(client.toString());
    }

    public void afisareSumaClient(Scanner in) throws Exception {
        var client = this.getClient(in);
        System.out.println(client.getNume() + " " + client.getPrenume() + " detine suma totala: " + client.calculareSumaTotala() + ".");
    }

    public void creareContClient(Scanner in) throws Exception {
        var client = this.getClient(in);
        System.out.println("CNP-ul clientului: ");
        String CNP = in.nextLine();
        client.creareCont(CNP);
        System.out.println("Cont nou DEFAULT creat cu succes!");
    }

    public void creareContEconomiiClient(Scanner in) throws Exception {
        var client = this.getClient(in);
        System.out.println("CNP-ul clientului: ");
        String CNP = in.nextLine();
        client.creareContEconomii(CNP);
        System.out.println("Cont nou de economii creat cu succes!");
    }

    public void creareCardClient(Scanner in) throws Exception {
        var client = this.getClient(in);
        var cont = this.getCont(in, client);
        System.out.println("CNP-ul clientului: ");
        var CNP = in.nextLine();
        cont.creareCard(CNP);
    }

    public void depozitContClient(Scanner in) throws Exception {
        var client = this.getClient(in);
        System.out.println("Suma pentru depozitare: ");
        int suma = Integer.parseInt(in.nextLine());
        client.getConturi().get(0).setSuma(suma);
        System.out.println("Contul a fost alimentat!");
    }

    public void creareTranzactie(Scanner in) throws Exception {
        System.out.println("Din contul cu IBAN: ");
        var IBAN1 = in.nextLine();
        System.out.println("Spre contul cu IBAN: ");
        var IBAN2 = in.nextLine();
        System.out.println("Suma de transferat: ");
        int suma = Integer.parseInt(in.nextLine());
        Cont cont1 = null, cont2 = null;
        for(var client: clienti)
            if(client.getConturiMap().containsKey(IBAN1))
                cont1 = client.getConturiMap().get(IBAN1);
        for(var client: clienti)
            if(client.getConturiMap().containsKey(IBAN2))
                cont2 = client.getConturiMap().get(IBAN2);
        if(cont1==null || cont2==null)
            throw new Exception("Nu se pot gasi IBAN-urile!");
        cont1.creareTranzactie(cont1, cont2, suma);
        System.out.println("Tranzactie procesata!");
    }

    public void inchidereCont(Scanner in) throws Exception {
        try{
        var client = this.getClient(in);
        var cont = this.getCont(in, client);
        client.inchidereCont(cont.getIBAN());
        System.out.println("Cont sters!");}
        catch (Exception e) {
            System.out.println("A intervenit o problema");
        }

    }

    public void afisareTranzactiiClient(Scanner in) throws Exception{
        var client = this.getClient(in);
        System.out.println("Afisati toate tranzactiile? (0 sau 1)");
        boolean toate = in.nextBoolean();
        if(toate)
            try {
                System.out.println(client.istoricTranzactii());
            }
        catch(Exception e){
            System.out.println("Nu exista tranzactii sau a intervenit alta problema!");
        }

        else{
            try{
            System.out.println("Pentru ce an doriti afisarea?: ");
            var an = in.nextInt();
            System.out.println(client.istoricTranzactiiPeAni(an));}
            catch (Exception e){
                System.out.println("Nu exista tranzactii sau a intervenit alta problema!");
            }
        }
        System.out.println();
    }

}