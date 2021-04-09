package Model;

public class CardFactory {
    private static int id_unic = 0;
    public Card creareCard(String IBAN, String CNP){
        return new Card(id_unic++, IBAN, CNP);
    }
}
