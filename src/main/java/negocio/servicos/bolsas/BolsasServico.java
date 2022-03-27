package negocio.servicos.bolsas;

import negocio.entidades.bolsa.Bolsa;
import negocio.repositorios.IRepositorioBolsas;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por executar a lógica de negócio necessária para casos de uso que envolvam 'Bolsas'.
 */
public class BolsasServico {

    private final IRepositorioBolsas iRepositorioBolsas;

    /**
     * Possui apenas dependência da interface (contrato) do repositório de Bolsas, respeitando o padrão de arquitetura limpa
     * e evitando acoplamento com camadas superiores.
     */
    public BolsasServico(IRepositorioBolsas iRepositorioBolsas) {
        this.iRepositorioBolsas = iRepositorioBolsas;
    }

    /**
     * Busca a primeira bolsa de um determinado ano.
     *
     * @param ano Ano a ser buscado.
     */
    public Optional<Bolsa> consultarBolsaZero(int ano) {
        List<Bolsa> bolsas = this.iRepositorioBolsas.getBolsasByAnoReferencia(ano);

        //Caso não existam bolsas no ano informado.
        if (bolsas.size() == 0) {
            return null;
        }
        Bolsa bolsaZero = bolsas.get(0); //Recuperando a primeira bolsa para utilizar como referência 'inicial'.
        for (Bolsa bolsa : bolsas) {
            //Caso exista uma bolsa com mes de referência menor, então esta será a 'bolsa zero'.
            if (bolsa.getMesReferencia() < bolsaZero.getMesReferencia()) {
                bolsaZero = bolsa;
            }
        }
        return Optional.of(bolsaZero);
    }

    public double consultarMediaAnual(int ano) {
        List<Bolsa> bolsas = this.iRepositorioBolsas.getBolsasByAnoReferencia(ano);
        if (bolsas.size() > 0) {
            double somaBolsas = bolsas.stream()
                    .reduce(0.0, (somaBolsasParcial, bolsa) -> somaBolsasParcial + bolsa.getValorBolsa(), Double::sum);
            return somaBolsas / bolsas.size();
        }
        return 0.0;
    }

    public List<Bolsa> get3MaioresBolsas() {
        return this.iRepositorioBolsas.get3MaioresBolsas();
    }

    public List<Bolsa> get3MenoresBolsas() {
        return this.iRepositorioBolsas.get3MenoresBolsas();
    }
}
