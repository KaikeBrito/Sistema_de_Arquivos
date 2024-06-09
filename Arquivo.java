class Arquivo {
    String nome;
    String conteudo;

    public Arquivo(String nome) {
        this.nome = nome;
        this.conteudo = "";
    }

    public void renomear(String novoNome) {
        this.nome = novoNome;
    }
}
