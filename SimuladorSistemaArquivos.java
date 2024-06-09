import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class SimuladorSistemaArquivos {
    Diretorio raiz;
    Jornal jornal;

    public SimuladorSistemaArquivos() {
        this.raiz = new Diretorio("raiz");
        this.jornal = new Jornal();
    }

    public void criarArquivo(String caminho, String nomeArquivo) {
        Diretorio dir = navegarParaDiretorio(caminho);
        if (dir != null) {
            Arquivo arquivo = new Arquivo(nomeArquivo);
            dir.adicionarArquivo(arquivo);
            jornal.adicionarEntrada("Criado arquivo " + nomeArquivo + " em " + caminho);
        } else {
            System.out.println("Diretório não encontrado!");
        }
    }

    public void criarArquivoReal(String caminhoReal, String nomeArquivo) {
        try {
            Path caminho = Paths.get(caminhoReal, nomeArquivo);
            Files.createFile(caminho);
            jornal.adicionarEntrada("Criado arquivo real " + nomeArquivo + " em " + caminhoReal);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
        }
    }

    public void criarDiretorio(String caminho, String nomeDiretorio) {
        Diretorio dir = navegarParaDiretorio(caminho);
        if (dir != null) {
            Diretorio novoDir = new Diretorio(nomeDiretorio);
            dir.adicionarDiretorio(novoDir);
            jornal.adicionarEntrada("Criado diretório " + nomeDiretorio + " em " + caminho);
        } else {
            System.out.println("Diretório não encontrado!");
        }
    }

    public void deletarArquivo(String caminho, String nomeArquivo) {
        Diretorio dir = navegarParaDiretorio(caminho);
        if (dir != null) {
            dir.removerArquivo(nomeArquivo);
            jornal.adicionarEntrada("Deletado arquivo " + nomeArquivo + " de " + caminho);
        } else {
            System.out.println("Diretório não encontrado!");
        }
    }

    public void deletarDiretorio(String caminho, String nomeDiretorio) {
        Diretorio dir = navegarParaDiretorio(caminho);
        if (dir != null) {
            dir.removerDiretorio(nomeDiretorio);
            jornal.adicionarEntrada("Deletado diretório " + nomeDiretorio + " de " + caminho);
        } else {
            System.out.println("Diretório não encontrado!");
        }
    }

    public void renomearArquivo(String caminho, String nomeAntigo, String novoNome) {
        Diretorio dir = navegarParaDiretorio(caminho);
        if (dir != null) {
            for (Arquivo arquivo : dir.arquivos) {
                if (arquivo.nome.equals(nomeAntigo)) {
                    arquivo.renomear(novoNome);
                    jornal.adicionarEntrada("Renomeado arquivo " + nomeAntigo + " para " + novoNome + " em " + caminho);
                    return;
                }
            }
            System.out.println("Arquivo não encontrado!");
        } else {
            System.out.println("Diretório não encontrado!");
        }
    }

    public void renomearDiretorio(String caminho, String nomeAntigo, String novoNome) {
        Diretorio dir = navegarParaDiretorio(caminho);
        if (dir != null) {
            for (Diretorio subDir : dir.diretorios) {
                if (subDir.nome.equals(nomeAntigo)) {
                    subDir.renomear(novoNome);
                    jornal.adicionarEntrada("Renomeado diretório " + nomeAntigo + " para " + novoNome + " em " + caminho);
                    return;
                }
            }
            System.out.println("Diretório não encontrado!");
        } else {
            System.out.println("Diretório não encontrado!");
        }
    }

    public void listarConteudoDiretorio(String caminho) {
        Diretorio dir = navegarParaDiretorio(caminho);
        if (dir != null) {
            dir.listarConteudo();
        } else {
            System.out.println("Diretório não encontrado!");
        }
    }

    public void copiarArquivo(String caminhoOrigem, String nomeArquivoOrigem, String caminhoDestino, String nomeArquivoDestino) {
        Diretorio dirOrigem = navegarParaDiretorio(caminhoOrigem);
        Diretorio dirDestino = navegarParaDiretorio(caminhoDestino);
        if (dirOrigem != null && dirDestino != null) {
            for (Arquivo arquivo : dirOrigem.arquivos) {
                if (arquivo.nome.equals(nomeArquivoOrigem)) {
                    Arquivo novoArquivo = new Arquivo(nomeArquivoDestino);
                    novoArquivo.conteudo = arquivo.conteudo;
                    dirDestino.adicionarArquivo(novoArquivo);
                    jornal.adicionarEntrada("Copiado arquivo " + nomeArquivoOrigem + " de " + caminhoOrigem + " para " + caminhoDestino + " como " + nomeArquivoDestino);
                    return;
                }
            }
            System.out.println("Arquivo de origem não encontrado!");
        } else {
            System.out.println("Diretório de origem ou destino não encontrado!");
        }
    }

    public void copiarDiretorio(String caminhoOrigem, String nomeDiretorioOrigem, String caminhoDestino, String nomeDiretorioDestino) {
        Diretorio dirOrigem = navegarParaDiretorio(caminhoOrigem);
        Diretorio dirDestino = navegarParaDiretorio(caminhoDestino);
        if (dirOrigem != null && dirDestino != null) {
            for (Diretorio diretorio : dirOrigem.diretorios) {
                if (diretorio.nome.equals(nomeDiretorioOrigem)) {
                    Diretorio novoDiretorio = new Diretorio(nomeDiretorioDestino);
                    copiarConteudoDiretorio(diretorio, novoDiretorio);
                    dirDestino.adicionarDiretorio(novoDiretorio);
                    jornal.adicionarEntrada("Copiado diretório " + nomeDiretorioOrigem + " de " + caminhoOrigem + " para " + caminhoDestino + " como " + nomeDiretorioDestino);
                    return;
                }
            }
            System.out.println("Diretório de origem não encontrado!");
        } else {
            System.out.println("Diretório de origem ou destino não encontrado!");
        }
    }

    private void copiarConteudoDiretorio(Diretorio origem, Diretorio destino) {
        for (Arquivo arquivo : origem.arquivos) {
            Arquivo novoArquivo = new Arquivo(arquivo.nome);
            novoArquivo.conteudo = arquivo.conteudo;
            destino.adicionarArquivo(novoArquivo);
        }
        for (Diretorio subDir : origem.diretorios) {
            Diretorio novoSubDir = new Diretorio(subDir.nome);
            copiarConteudoDiretorio(subDir, novoSubDir);
            destino.adicionarDiretorio(novoSubDir);
        }
    }

    private Diretorio navegarParaDiretorio(String caminho) {
        String[] partes = caminho.split("/");
        Diretorio atual = raiz;
        for (String parte : partes) {
            if (parte.isEmpty()) continue;
            boolean encontrado = false;
            for (Diretorio dir : atual.diretorios) {
                if (dir.nome.equals(parte)) {
                    atual = dir;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) return null;
        }
        return atual;
    }
}