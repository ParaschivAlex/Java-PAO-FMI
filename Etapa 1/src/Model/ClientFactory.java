package Model;

import java.text.ParseException;
import java.util.*;

public class ClientFactory {
    private static int id_unic = 0;

    public Client creareClient(String nume, String prenume, String CNP, Date zi_de_nastere, String email, String numar_telefon, Adresa adresa){
        return new Client(id_unic++, nume, prenume, CNP, zi_de_nastere, email, numar_telefon,adresa);
    }

    public Client creareClient(Scanner in) throws ParseException {
        return new Client(id_unic++, in);
    }

}
