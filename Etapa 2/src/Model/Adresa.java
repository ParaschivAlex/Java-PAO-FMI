package Model;

import java.util.*;

public class Adresa {
    protected String tara, oras, strada, cod_postal;
    protected int nr;

    public Adresa(String tara, String oras, String strada, String cod_postal, int nr) {
        this.tara = tara;
        this.oras = oras;
        this.strada = strada;
        this.cod_postal = cod_postal;
        this.nr = nr;
    }

    public Adresa(Scanner in) {
        this.citire(in);
    }

    public void citire(Scanner in) {
        System.out.println("Tara: ");
        this.tara = in.nextLine();
        System.out.println("Oras: ");
        this.oras = in.nextLine();
        System.out.println("Strada: ");
        this.strada = in.nextLine();
        System.out.println("Cod Postal: ");
        this.cod_postal = in.nextLine();
        System.out.println("Numarul: ");
        this.nr = Integer.parseInt(in.nextLine());
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return "Adresa{" +
                "tara='" + tara + '\'' +
                ", oras='" + oras + '\'' +
                ", strada='" + strada + '\'' +
                ", cod_postal='" + cod_postal + '\'' +
                ", nr=" + nr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresa adresa = (Adresa) o;
        return nr == adresa.nr && Objects.equals(tara, adresa.tara) && Objects.equals(oras, adresa.oras) && Objects.equals(strada, adresa.strada) && Objects.equals(cod_postal, adresa.cod_postal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tara, oras, strada, cod_postal, nr);
    }

    public String CSV() {
        return tara +
                "," + oras +
                "," + strada +
                "," + cod_postal +
                "," + nr;
    }


}
