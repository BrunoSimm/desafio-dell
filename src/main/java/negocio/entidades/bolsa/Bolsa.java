package negocio.entidades.bolsa;

import negocio.entidades.Bolsista;

public class Bolsa {

    private int id;
    private Bolsista bolsista;
    private int mesReferencia;
    private int anoReferencia;

    private double valorBolsa;
    private String codigoMoeda;
    private ModalidadePagamento modalidadePagamento;

    public Bolsa(int id, Bolsista bolsista, int mesReferencia, int anoReferencia, double valorBolsa,
                 String codigoMoeda, ModalidadePagamento modalidadePagamento) {
        this.id = id;
        this.bolsista = bolsista;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.valorBolsa = valorBolsa;
        this.codigoMoeda = codigoMoeda;
        this.modalidadePagamento = modalidadePagamento;
    }

    public int getId() { return id; }

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
}
