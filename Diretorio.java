import java.util.ArrayList;
import java.util.List;

class Diretorio {
    String nome;
    List<Arquivo> arquivos;
    List<Diretorio> diretorios;

    public Diretorio(String nome) {
        this.nome = nome;
        this.arquivos = new ArrayList<>();
        this.diretorios = new ArrayList<>();
    }

    public void adicionarArquivo(Arquivo arquivo) {
        arquivos.add(arquivo);
    }

    public void adicionarDiretorio(Diretorio diretorio) {
        diretorios.add(diretorio);
    }

    public void removerArquivo(String nomeArquivo) {
        arquivos.removeIf(arquivo -> arquivo.nome.equals(nomeArquivo));
    }

    public void removerDiretorio(String nomeDiretorio) {
        diretorios.removeIf(diretorio -> diretorio.nome.equals(nomeDiretorio));
    }

    public void renomear(String novoNome) {
        this.nome = novoNome;
    }

    public void listarConteudo() {
        System.out.println("Diretório: " + nome);
        for (Diretorio dir : diretorios) {
            System.out.println("Dir: " + dir.nome);
        }
        for (Arquivo arquivo : arquivos) {
            System.out.println("Arquivo: " + arquivo.nome);
        }
    }
}
