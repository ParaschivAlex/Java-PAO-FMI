package Model;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Random;

public class Card {
    protected String numar_card, CNP, IBAN;
    protected static int id_card;
    protected final int CVV;
    protected final Date data_expirare;

    static protected final Set<String> numere_card_folosite = new HashSet<>();

    public Card(int id_card, String IBAN, String CNP) {
        this.numar_card = this.generare_numar_card(); //Ignor regula lui Luhn

        while (numere_card_folosite.contains(this.numar_card))
            this.numar_card = this.generare_numar_card();
        numere_card_folosite.add(this.numar_card);

        this.CNP = CNP;
        this.IBAN = IBAN;
        this.id_card = id_card;

        this.CVV = this.generare_CVV();

        Calendar c = Calendar.getInstance(); //https://stackoverflow.com/questions/11642701/adding-years-to-a-random-date-from-date-class
        c.setTime(new Date());
        c.add(Calendar.YEAR, 4);
        this.data_expirare = c.getTime();
    }

    private String generare_numar_card() { //https://gist.github.com/josefeg/5781824
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }
        return builder.toString();
    }

    private int generare_CVV() { //https://stackoverflow.com/questions/32534601/java-getting-a-random-number-from-100-to-999
        Random random = new Random();
        return (random.nextInt(900) + 100);
    }

    public void citire(Scanner in) {
        System.out.println("IBAN: ");
        this.IBAN = in.nextLine();
        System.out.println("Nume: ");
        this.CNP = in.nextLine();
    }

    public Card createCard(String IBAN, String CNP){
        return new Card(id_card++, IBAN, CNP);
    }

    public String getNumar_card() {
        return numar_card;
    }

    public void setNumar_card(String numar_card) {
        this.numar_card = numar_card;
    }

    public String getNume() {
        return CNP;
    }

    public void setNume(String CNP) {
        this.CNP = CNP;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public int getId_card() {
        return id_card;
    }

    public int getCVV() {
        return CVV;
    }

    public Date getData_expirare() {
        return data_expirare;
    }

    public static Set<String> getNumere_card_folosite() {
        return numere_card_folosite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id_card == card.id_card && CVV == card.CVV && Objects.equals(numar_card, card.numar_card) && Objects.equals(CNP, card.CNP) && Objects.equals(IBAN, card.IBAN) && Objects.equals(data_expirare, card.data_expirare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numar_card, CNP, IBAN, id_card, CVV, data_expirare);
    }

    @Override
    public String toString() {
        return "Card{" +
                "numar_card='" + numar_card + '\'' +
                ", nume='" + CNP + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", id_card=" + id_card +
                ", CVV=" + CVV +
                ", data_expirare=" + data_expirare +
                '}';
    }
}
