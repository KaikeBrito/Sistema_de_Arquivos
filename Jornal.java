import java.util.ArrayList;
import java.util.List;

class Jornal {
    List<String> log;

    public Jornal() {
        this.log = new ArrayList<>();
    }

    public void adicionarEntrada(String entrada) {
        log.add(entrada);
    }

    public void imprimirLog() {
        for (String entrada : log) {
            System.out.println(entrada);
        }
    }
}
