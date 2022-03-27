package aplicacao.casosDeUso.bolsas;

import negocio.entidades.bolsa.Bolsa;
import negocio.servicos.bolsas.BolsasServico;

import java.util.List;

public class ConsultarRankingMenoresValoresDeBolsaUC {

    private final BolsasServico bolsasServico;

    public ConsultarRankingMenoresValoresDeBolsaUC(BolsasServico bolsasServico) {
        this.bolsasServico = bolsasServico;
    }

    public List<Bolsa> run() {
        return this.bolsasServico.get3MenoresBolsas();
    }
}
