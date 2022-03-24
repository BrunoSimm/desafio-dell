package negocio.entidades;

public class Bolsista {

    private String nome;
    private String cpf;
    private String entidadeEnsino;

    public Bolsista(String nome, String cpf, String entidadeEnsino) {
        this.nome = nome;
        this.cpf = cpf;
        this.entidadeEnsino = entidadeEnsino;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEntidadeEnsino() {
        return entidadeEnsino;
    }
}
