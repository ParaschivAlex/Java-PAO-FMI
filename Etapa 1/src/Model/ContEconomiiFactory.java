package Model;

public class ContEconomiiFactory {

    private static int id_unic = 0;

    public ContEconomii creareContEconomii(String CNP){
        return new ContEconomii(CNP, id_unic++);
    }
}
