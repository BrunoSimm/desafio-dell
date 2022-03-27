package aplicacao.casosDeUso.bolsistas;

import negocio.entidades.Bolsista;
import negocio.servicos.bolsistas.BolsistasServico;

import java.util.List;

public class BuscarBolsistaPeloNomeUC {

    private final BolsistasServico bolsistasServico;

    public BuscarBolsistaPeloNomeUC(BolsistasServico bolsistasServico) {
        this.bolsistasServico = bolsistasServico;
    }

    public List<Bolsista> run(String nome) {
        return this.bolsistasServico.buscarBolsistaPeloNome(nome);
    }
}
