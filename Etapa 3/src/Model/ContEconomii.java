package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ContEconomii extends Cont {
    protected final Date data_inceput, data_incheiere;
    protected final double dobanda;
    //protected static int id = 0;

    public ContEconomii(String CNP, int id_unic, int id_client) {
        super(CNP, id_unic, id_client);
        this.data_inceput = new Date();

        Calendar c = Calendar.getInstance(); //https://stackoverflow.com/questions/11642701/adding-years-to-a-random-date-from-date-class
        c.setTime(new Date());
        c.add(Calendar.YEAR, 18); // pe 18 ani
        this.data_incheiere = c.getTime();

        this.dobanda = 0.15; //15%
    }

    public ContEconomii(String IBAN, double suma, String CNP, int id_client, Date data_inceput, Date data_incheiere, double dobanda) {
        super(IBAN, suma, CNP, id_client);
        this.data_inceput = data_inceput;
        this.data_incheiere = data_incheiere;
        this.dobanda = dobanda;
    }

    public ContEconomii(ResultSet in) throws SQLException {
        super(in);
        this.data_inceput = in.getDate("data_inceput");
        this.data_incheiere = in.getDate("data_incheiere");
        this.dobanda = in.getInt("dobanda");
    }

    public Date getData_inceput() {
        return data_inceput;
    }

    public Date getData_incheiere() {
        return data_incheiere;
    }

    public double getDobanda() {
        return dobanda;
    }

    @Override
    public String toString() {
        return "ContEconomii{" +
                "IBAN='" + IBAN + '\'' +
                ", suma=" + suma +
                ", CNP='" + CNP + '\'' +
                ", id_client=" + id_client +
                ", data_inceput=" + data_inceput +
                ", data_incheiere=" + data_incheiere +
                ", dobanda=" + dobanda +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContEconomii that = (ContEconomii) o;
        return Double.compare(that.dobanda, dobanda) == 0 && Objects.equals(data_inceput, that.data_inceput) && Objects.equals(data_incheiere, that.data_incheiere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data_inceput, data_incheiere, dobanda);
    }

    public String CSV() {
        return IBAN +
                "," + suma +
                "," + CNP +
                "," + id_client +
                "," + carduri +
                "," + data_inceput +
                "," + data_incheiere +
                "," + dobanda;
    }
}
