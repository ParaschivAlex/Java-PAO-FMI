import java.util.*;
import Model.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean ok = false;
        Service service = new Service();
        Audit audit = new Audit();

        ClientSingleton.getInstance().loadCSV(); //incarc fisierele
        ContSingleton.getInstance().loadCSV();
        ContEconomiiSingleton.getInstance().loadCSV();
        TranzactieSingleton.getInstance().loadCSV();

        service.setClienti(ClientSingleton.getInstance().getClienti());
        service.setConturi(ContSingleton.getInstance().getConturi());
        service.setConturiEconomii(ContEconomiiSingleton.getInstance().getConturieconomii());
        service.setTranzactii(TranzactieSingleton.getInstance().getTranzactii());

        service.iaConturi();

        while (!ok){
            System.out.println("Inserati comanda: ");
            System.out.println("1: Creare client nou");
            System.out.println("2: Creare cont normal pentru client");
            System.out.println("3: Creare cont de economii pentru client");
            System.out.println("4: Creare card pentru client");
            System.out.println("5: Inchidere cont client");
            System.out.println("6: Tranzactie noua");
            System.out.println("7: Afisati toate tranzactiile unui client");
            System.out.println("8: Depozitare suma in cont");
            System.out.println("9: Afisati date despre clienti");
            System.out.println("10: Afisati soldul total al unui client");
            System.out.println("0: EXIT");
            String command = in.nextLine();
            try{
                switch (command) {
                    case "1":
                        service.creareClient(in);
                        audit.numeActiune("Creare Client Nou");
                        break;
                    case "2":
                        service.creareContClient(in);
                        audit.numeActiune("Creare Cont Normal Pentru Client");
                        break;
                    case "3":
                        service.creareContEconomiiClient(in);
                        audit.numeActiune("Creare Cont de Economii Pentru Client");
                        break;
                    case "4":
                        service.creareCardClient(in);
                        audit.numeActiune("Creare Card Pentru Client");
                        break;
                    case "5":
                        service.inchidereCont(in);
                        audit.numeActiune("Inchidere Cont a Unui Client");
                        break;
                    case "6":
                        service.creareTranzactie(in);
                        audit.numeActiune("Creare Tranzactie Noua");
                        break;
                    case "7":
                        service.afisareTranzactiiClient(in);
                        audit.numeActiune("Afisare Tranzactii a Unui Client");
                        break;
                    case "8":
                        service.depozitContClient(in);
                        audit.numeActiune("Depozitare Suma In Cont Client");
                        break;
                    case "9":
                        service.afisareClient(in);
                        audit.numeActiune("Afisare Date Despre Client");
                        break;
                    case "10":
                        service.afisareSoldClient(in);
                        audit.numeActiune("Afisare Sold Cont Client");
                        break;
                    case "0":
                        ok = true;
                        break;
                }
            }catch (Exception e){
                System.out.println(e.toString());
            }
            System.out.println('\n');
        }

        ClientSingleton.getInstance().setClienti(service.getClienti());
        ContSingleton.getInstance().setConturi(service.getConturi());
        ContEconomiiSingleton.getInstance().setConturieconomii(service.getConturiEconomii());
        TranzactieSingleton.getInstance().setTranzactii(service.getTranzactii());

        ClientSingleton.getInstance().writeCSV();
        ContSingleton.getInstance().writeCSV();
        ContEconomiiSingleton.getInstance().writeCSV();
        TranzactieSingleton.getInstance().writeCSV();

    }
}
