package negocio.entidades;

import negocio.entidades.bolsa.Bolsa;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Bolsista {

    private String nome;
    private String cpf;
    private String entidadeEnsino;
    private Set<Bolsa> bolsas;

    public Bolsista(String nome, String cpf, String entidadeEnsino) {
        this.nome = nome;
        this.cpf = cpf;
        this.entidadeEnsino = entidadeEnsino;
        this.bolsas = new HashSet<Bolsa>();
    }

    public Bolsista() {
        this.bolsas = new HashSet<Bolsa>();
    }

    public Set<Bolsa> getBolsas() {
        return bolsas;
    }

    public Bolsa getBolsaMaisRecente() {
        if(this.bolsas.size() > 0){
            int anoMaisRecente = 0;
            int mesMaisRecente = 0;
            Bolsa bolsaMaisRecente = null;
            for (Bolsa bolsa: this.bolsas) {
                if(bolsa.getAnoReferencia() > anoMaisRecente && bolsa.getMesReferencia() > mesMaisRecente) {
                    bolsaMaisRecente = bolsa;
                }
            }
            return bolsaMaisRecente;
        }
        return null;
    }

    public void addBolsa(Bolsa bolsa) {
        this.bolsas.add(bolsa);
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEntidadeEnsino(String entidadeEnsino) {
        this.entidadeEnsino = entidadeEnsino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bolsista bolsista = (Bolsista) o;
        return Objects.equals(nome, bolsista.nome) && Objects.equals(cpf, bolsista.cpf) && Objects.equals(entidadeEnsino, bolsista.entidadeEnsino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, entidadeEnsino);
    }
}
