package aplicacao.casosDeUso.bolsas;

import negocio.entidades.Bolsista;
import negocio.entidades.bolsa.Bolsa;
import negocio.servicos.bolsas.BolsasServico;

import java.util.Optional;

public class ConsultarBolsaZeroUC {

    private BolsasServico bolsasServico;
    private int ano;

    public ConsultarBolsaZeroUC(BolsasServico bolsasServico, int ano) {
        this.bolsasServico = bolsasServico;
        this.ano = ano;
    }

    public Optional<Bolsa> run() {
        return this.bolsasServico.consultarBolsaZero(this.ano);
    }
}
