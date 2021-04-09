package Model;

import java.util.*;
public class Cont implements Comparator<Tranzactie>{
    protected String IBAN;
    protected double suma;
    protected String CNP;

    protected List<Tranzactie> istoric_tranzactii = new ArrayList<>();
    protected List<Card> carduri = new ArrayList<>();

    public Cont(String CNP, int id_unic) {
        this.IBAN = generare_IBAN(id_unic);
        this.suma = 0;
        this.CNP = CNP;
    }


    public Tranzactie creareTranzactie(Cont IBAN_trimite, Cont IBAN_primeste, double suma) throws Exception{
        Tranzactie tranzactie = new Tranzactie(IBAN_trimite, IBAN_primeste, suma);
        IBAN_trimite.istoric_tranzactii.add(tranzactie);
        IBAN_primeste.istoric_tranzactii.add(tranzactie);//adaug tranzactia in lista de tranzactii
        return tranzactie;
    }

    private final CardFactory cardFactory = new CardFactory();
    public Card creareCard(String CNP){
        Card card_nou = cardFactory.creareCard(this.IBAN, CNP);
        carduri.add(card_nou);
        return card_nou;
    }

    public List<Tranzactie> getIstoricTranzactii() {
        return istoric_tranzactii;
    }

    public int compare(Tranzactie tranzactie1, Tranzactie tranzactie2){
        return tranzactie1.getData_tranzactie().compareTo(tranzactie2.getData_tranzactie());
    }

    public List<Tranzactie> getIstoricTranzactii(int an) {
        List<Tranzactie> tranzactii_sortate = new ArrayList<>();
        for(var transaction: istoric_tranzactii)
            if(transaction.getData_tranzactie().getYear()==an)
                tranzactii_sortate.add(transaction);
        tranzactii_sortate.sort(this);
        return tranzactii_sortate;
    }

    public String generare_IBAN(int id_unic){
        //generare banala de iban
        return "RO73RZBR" + id_unic;
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

    public List<Tranzactie> getIstoric_tranzactii() {
        return istoric_tranzactii;
    }

    public void setIstoric_tranzactii(List<Tranzactie> istoric_tranzactii) {
        this.istoric_tranzactii = istoric_tranzactii;
    }

    public List<Card> getCarduri() {
        return carduri;
    }

    public void setCarduri(List<Card> carduri) {
        this.carduri = carduri;
    }

    @Override
    public String toString() {
        return "Cont{" +
                "IBAN='" + IBAN + '\'' +
                ", suma=" + suma +
                ", CNP='" + CNP + '\'' +
                ", istoric_tranzactii=" + istoric_tranzactii +
                ", carduri=" + carduri +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cont cont = (Cont) o;
        return Double.compare(cont.suma, suma) == 0 && Objects.equals(IBAN, cont.IBAN) && Objects.equals(CNP, cont.CNP) && Objects.equals(istoric_tranzactii, cont.istoric_tranzactii) && Objects.equals(carduri, cont.carduri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN, suma, CNP, istoric_tranzactii, carduri);
    }


}
