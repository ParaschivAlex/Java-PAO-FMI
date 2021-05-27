package Model;

import java.util.*;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Tranzactie {
    protected final String IBAN_trimite, IBAN_primeste;
    protected final double suma;
    protected final Date data_tranzactie;

    public double getSuma() {
        return suma;
    }

    public Tranzactie(String IBAN_trimite, String IBAN_primeste, double suma) throws Exception {
        this.IBAN_trimite = IBAN_trimite;
        this.IBAN_primeste = IBAN_primeste;
        //if (IBAN_trimite.getSuma() < suma)
            //throw new Exception("Sold insuficient!");
        if (suma < 0)
            throw new Exception("Suma trimisa trebuie sa fie mai mare ca 0!");
        this.suma = suma;
        this.data_tranzactie = new Date();
    }

    public Tranzactie(String IBAN_trimite, String IBAN_primeste, double suma, Date data_tranzactie) {
        this.IBAN_trimite = IBAN_trimite;
        this.IBAN_primeste = IBAN_primeste;
        this.suma = suma;
        this.data_tranzactie = data_tranzactie;
    }

    public Tranzactie(ResultSet in) throws SQLException {
        this.IBAN_trimite = in.getString("IBAN_trimite");
        this.IBAN_primeste = in.getString("IBAN_primeste");
        this.suma = in.getDouble("suma");
        this.data_tranzactie = in.getDate("data_tranzactie");
    }

    public String getIBAN_trimite() {
        return IBAN_trimite;
    }

    public String getIBAN_primeste() {
        return IBAN_primeste;
    }

    public Date getData_tranzactie() {
        return data_tranzactie;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "IBAN_trimite='" + IBAN_trimite + '\'' +
                ", IBAN_primeste='" + IBAN_primeste + '\'' +
                ", suma=" + suma +
                ", data_tranzactie=" + data_tranzactie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tranzactie that = (Tranzactie) o;
        return Double.compare(that.suma, suma) == 0 && Objects.equals(IBAN_trimite, that.IBAN_trimite) && Objects.equals(IBAN_primeste, that.IBAN_primeste) && Objects.equals(data_tranzactie, that.data_tranzactie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN_trimite, IBAN_primeste, suma, data_tranzactie);
    }

    public String CSV() {
        return IBAN_primeste +
                "," + IBAN_trimite +
                "," + suma +
                "," + data_tranzactie;
    }
}
