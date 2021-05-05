package Model;

import java.util.*;

public class Cont implements Comparator<Tranzactie> {
    protected String IBAN;
    protected double suma;
    protected String CNP;
    protected int id_client;

    protected List<Card> carduri = new ArrayList<>();
    static protected final Set<String> IBANuri_folosite = new HashSet<>();

    public Cont(String CNP, int id_unic, int id_client) {
        this.IBAN = this.generare_IBAN(); //Ignor regula lui Luhn

        while (IBANuri_folosite.contains(this.IBAN))
            this.IBAN = this.generare_IBAN();
        IBANuri_folosite.add(this.IBAN);
        this.suma = 0;
        this.CNP = CNP;
        this.id_client = id_client;
    }

    public Cont(String IBAN, double suma, String CNP, int id_client) { // pt csv
        this.IBAN = IBAN;
        this.suma = suma;
        this.CNP = CNP;
        this.id_client = id_client;
    }

    private final CardFactory cardFactory = new CardFactory();

    public void creareCard(String CNP) {
        Card card_nou = cardFactory.creareCard(this.IBAN, CNP);
        carduri.add(card_nou);
        //return card_nou;
    }

    public int compare(Tranzactie tranzactie1, Tranzactie tranzactie2) {
        return tranzactie1.getData_tranzactie().compareTo(tranzactie2.getData_tranzactie());
    }

    public String generare_IBAN() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append("RO99RZBR");//pentru contri create separat de csv am schimbat ibanul
        for (int i = 0; i < 4; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }
        return builder.toString();
    }

    public List<Tranzactie> filterTranzactii(List<Tranzactie> toate_tranzactiile) {
        List<Tranzactie> tranzactii = new ArrayList<>();
        for (var tranzactie : toate_tranzactiile)
            if (tranzactie.getIBAN_trimite().equals(this.IBAN))
                tranzactii.add(tranzactie);
        return tranzactii;
    }

    public List<Tranzactie> filterTranzactii(List<Tranzactie> allTransactions, int an) {
        List<Tranzactie> tranzactii = new ArrayList<>();
        for (var tranzactie : allTransactions)
            if (tranzactie.getIBAN_trimite().equals(this.IBAN) && tranzactie.getData_tranzactie().getYear() == an)
                tranzactii.add(tranzactie);
        return tranzactii;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public List<Card> getCarduri() {
        return carduri;
    }

    public void setCarduri(List<Card> carduri) {
        this.carduri = carduri;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cont cont = (Cont) o;
        return Double.compare(cont.suma, suma) == 0 && id_client == cont.id_client && Objects.equals(IBAN, cont.IBAN) && Objects.equals(CNP, cont.CNP) && Objects.equals(carduri, cont.carduri) && Objects.equals(cardFactory, cont.cardFactory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN, suma, CNP, id_client, carduri, cardFactory);
    }

    @Override
    public String toString() {
        return "Cont{" +
                "IBAN='" + IBAN + '\'' +
                ", suma=" + suma +
                ", CNP='" + CNP + '\'' +
                ", id_client=" + id_client +
                //", carduri=" + carduri +
                //", cardFactory=" + cardFactory +
                '}';
    }

    public String CSV() {
        return IBAN +
                "," + suma +
                "," + CNP +
                "," + id_client;
    }
}
