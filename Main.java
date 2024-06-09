class Main {
        public static void main(String[] args) {
                SimuladorSistemaArquivos ssa = new SimuladorSistemaArquivos();

                // Criar diretórios e arquivos
                ssa.criarDiretorio("/", "documentos");
                ssa.criarArquivo("/documentos", "arquivo1.txt");

                // Listar conteúdo do diretório
                ssa.listarConteudoDiretorio("/documentos");

                // Renomear arquivo
                ssa.renomearArquivo("/documentos", "arquivo1.txt", "arquivo2.txt");
                ssa.listarConteudoDiretorio("/documentos");

                // Copiar arquivo
                ssa.criarDiretorio("/documentos", "backup");
                ssa.copiarArquivo("/documentos", "arquivo2.txt", "/documentos/backup", "arquivo2_copia.txt");
                ssa.listarConteudoDiretorio("/documentos/backup");

                // Deletar arquivo
                ssa.deletarArquivo("/documentos", "arquivo2.txt");
                ssa.listarConteudoDiretorio("/documentos");

                // Criar diretório e copiar diretório
                ssa.criarDiretorio("/", "imagens");
                ssa.criarDiretorio("/imagens", "fotos");
                ssa.copiarDiretorio("/imagens", "fotos", "/documentos", "fotos_backup");
                ssa.listarConteudoDiretorio("/documentos");

                // Renomear diretório
                ssa.renomearDiretorio("/documentos", "fotos_backup", "fotos_renomeado");
                ssa.listarConteudoDiretorio("/documentos");

                // Deletar diretório
                ssa.deletarDiretorio("/documentos", "fotos_renomeado");
                ssa.listarConteudoDiretorio("/documentos");

                // Imprimir log do jornal
                ssa.jornal.imprimirLog();

                // Criar um arquivo real no diretório home do usuário
                String homeUsuario = System.getProperty("user.home");
                ssa.criarArquivoReal(homeUsuario, "arquivoTeste.txt");
        }
}