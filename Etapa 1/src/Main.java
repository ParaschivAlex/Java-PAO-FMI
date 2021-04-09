/*conturi, extras de cont, tranzactii, carduri, servicii

Obiectele (8 obiecte si clase factory care nu stiu daca se pun ca si obiecte):
    -Cont
    --Cont de economii
    -Card
    --Visa
    --Mastercard
    -Tranzactie
    --Client
    --Adresa client

Actiuni (10 actiuni):
    -Creare client
    -Creare cont normal
    -Creare card
    -Creare cont de economii
    -Inchidere cont
    -Creare tranzactie
    -Afisare toate tranzactiile unui client
    -Afisare conturi pentru o persoana
    -Afisare date persoana
    -Depozitare suma in cont
    -Afisare sold total client

 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean ok = false;
        Service service = new Service();

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
                    case "1" -> service.creareClient(in);
                    case "2" -> service.creareContClient(in);
                    case "3" -> service.creareContEconomiiClient(in);
                    case "4" -> service.creareCardClient(in);
                    case "5"-> service.inchidereCont(in);
                    case "6" -> service.creareTranzactie(in);
                    case "7" -> service.afisareTranzactiiClient(in);
                    case "8" -> service.depozitContClient(in);
                    case "9" -> service.afisareClient(in);
                    case "10" -> service.afisareSumaClient(in);
                    case "0" -> ok = true;
                }
            }catch (Exception e){
                System.out.println(e.toString());
            }
            System.out.println('\n');
        }
    }
}
