package aplicacao.casosDeUso.bolsas;

import negocio.entidades.bolsa.Bolsa;
import negocio.servicos.bolsas.BolsasServico;

import java.util.List;


public class ConsultarRankingMaioresValoresDeBolsaUC {

    private final BolsasServico bolsasServico;

    public ConsultarRankingMaioresValoresDeBolsaUC(BolsasServico bolsasServico) {
        this.bolsasServico = bolsasServico;
    }

    public List<Bolsa> run() {
        return this.bolsasServico.get3MaioresBolsas();
    }

}
