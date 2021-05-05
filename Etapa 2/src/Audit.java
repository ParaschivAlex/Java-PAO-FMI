import Model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Audit {
    FileWriter writer;

    //timestamp
    final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void numeActiune(String action) throws IOException {
        writer.append(action);
        writer.append(",");
        writer.append(format.format(LocalDateTime.now()));
        writer.append("\n");
        writer.flush();
    }


    public Audit() {
        try{
            this.writer = new FileWriter("data/audit.csv");
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
