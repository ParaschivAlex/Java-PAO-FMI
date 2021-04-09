package Model;

public class ContFactory {
    private static int id_unic = 0;

    public Cont creareCont(String CNP){
        return new Cont(CNP, id_unic++);
    }
}
