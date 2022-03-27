package negocio.entidades.bolsa;

import negocio.entidades.Bolsista;

import java.util.Objects;

public class Bolsa {

    private Bolsista bolsista;
    private int mesReferencia;
    private int anoReferencia;

    private double valorBolsa;
    private String codigoMoeda;
    private ModalidadePagamento modalidadePagamento;

    public Bolsa(Bolsista bolsista, int mesReferencia, int anoReferencia, double valorBolsa,
                 String codigoMoeda, ModalidadePagamento modalidadePagamento) {
        this.bolsista = bolsista;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.valorBolsa = valorBolsa;
        this.codigoMoeda = codigoMoeda;
        this.modalidadePagamento = modalidadePagamento;
    }

    public Bolsa() {
    }

    public Bolsista getBolsista() {
        return bolsista;
    }

    public int getMesReferencia() {
        return mesReferencia;
    }

    public int getAnoReferencia() {
        return anoReferencia;
    }

    public double getValorBolsa() {
        return valorBolsa;
    }

    public String getCodigoMoeda() {
        return codigoMoeda;
    }

    public ModalidadePagamento getModalidadePagamento() {
        return modalidadePagamento;
    }

    public void setBolsista(Bolsista bolsista) {
        this.bolsista = bolsista;
    }

    public void setMesReferencia(int mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public void setAnoReferencia(int anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public void setValorBolsa(double valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public void setCodigoMoeda(String codigoMoeda) {
        this.codigoMoeda = codigoMoeda;
    }

    public void setModalidadePagamento(ModalidadePagamento modalidadePagamento) {
        this.modalidadePagamento = modalidadePagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bolsa bolsa = (Bolsa) o;
        return mesReferencia == bolsa.mesReferencia && anoReferencia == bolsa.anoReferencia && Double.compare(bolsa.valorBolsa, valorBolsa) == 0 && Objects.equals(bolsista, bolsa.bolsista) && Objects.equals(codigoMoeda, bolsa.codigoMoeda) && modalidadePagamento == bolsa.modalidadePagamento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bolsista, mesReferencia, anoReferencia, valorBolsa, codigoMoeda, modalidadePagamento);
    }
}
