package Model;

import java.util.*;

public class ContEconomii extends Cont {
    protected final Date data_inceput, data_incheiere;
    protected final double dobanda;
    protected static int id = 0;

    public ContEconomii(String CNP, int id) {
        super(CNP, id++);
        this.data_inceput = new Date();

        Calendar c = Calendar.getInstance(); //https://stackoverflow.com/questions/11642701/adding-years-to-a-random-date-from-date-class
        c.setTime(new Date());
        c.add(Calendar.YEAR, 18); // pe 18 ani
        this.data_incheiere = c.getTime();

        this.dobanda = 0.15; //15%
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
}
