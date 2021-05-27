package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class ClientFactory {
    private static int id_unic = 0;

    public static void increment_id_unic(int i) {
        ClientFactory.id_unic += i;
    }

    public Client creareClient(Scanner in) throws ParseException {
        return new Client(id_unic++, in);
    }

    public Client creareClient(ResultSet in) throws SQLException {
        return new Client(id_unic++, in);
    }
}
