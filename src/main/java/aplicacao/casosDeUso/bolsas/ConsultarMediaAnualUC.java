package aplicacao.casosDeUso.bolsas;

import negocio.servicos.bolsas.BolsasServico;

public class ConsultarMediaAnualUC {

    private BolsasServico bolsasServico;

    public ConsultarMediaAnualUC(BolsasServico bolsasServico) {
        this.bolsasServico = bolsasServico;
    }

    public double run(int ano) {
        return this.bolsasServico.consultarMediaAnual(ano);
    }
}
