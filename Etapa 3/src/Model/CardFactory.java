package Model;

public class CardFactory {
    private static int id_unic = 0;

    public Card creareCard(String IBAN, String CNP) {
        return new Card(id_unic++, IBAN, CNP);
    }

    public CardMastercard creareMasterCardCard(String IBAN, String CNP) {
        return new CardMastercard(id_unic++, IBAN, CNP);
    }

    public CardVisa creareVisaCard(String IBAN, String CNP) {
        return new CardVisa(id_unic++, IBAN, CNP);
    }
}
