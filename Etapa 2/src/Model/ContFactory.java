package Model;

public class ContFactory {
    private static int id_unic = 0;

    public static void increment_id_unic (int i){
        ContFactory.id_unic += i;
    }

    public ContEconomii creareContEconomii(String CNP, int clientId){
        return new ContEconomii(CNP, clientId, id_unic++);
    }

    public Cont creareCont(String CNP, int clientId){
        return new Cont(CNP, clientId, id_unic++);
    }
}
