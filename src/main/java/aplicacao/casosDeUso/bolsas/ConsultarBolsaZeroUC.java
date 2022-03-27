package aplicacao.casosDeUso.bolsas;

import negocio.entidades.bolsa.Bolsa;
import negocio.servicos.bolsas.BolsasServico;

import java.util.Optional;

public class ConsultarBolsaZeroUC {

    private final BolsasServico bolsasServico;

    public ConsultarBolsaZeroUC(BolsasServico bolsasServico) {
        this.bolsasServico = bolsasServico;
    }

    public Optional<Bolsa> run(int ano) {
        return this.bolsasServico.consultarBolsaZero(ano);
    }
}
