package adaptadores.repositorios;

import negocio.entidades.Bolsista;
import negocio.entidades.bolsa.Bolsa;
import negocio.entidades.bolsa.ModalidadePagamento;
import negocio.repositorios.IRepositorioBolsas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que possui responsabilidade de fazer a leitura e resposta de acesso a dados referentes a Bolsas.
 * Implementa a interface IRepositorioBolsas definida na camada de negócio.
 * Camadas inferiores não dependem desta implementação e sim da interface.
 */
public class RepositorioBolsas implements IRepositorioBolsas {

    private final List<Bolsa> bolsas;

    public RepositorioBolsas() {
        this.bolsas = new ArrayList<>();
    }

    @Override
    public Bolsa criarBolsa(Bolsista bolsista, int mesReferencia, int anoReferencia, double valorBolsa, String codigoMoeda, ModalidadePagamento modalidadePagamento) {
        Bolsa bolsa = new Bolsa(bolsista, mesReferencia, anoReferencia, valorBolsa, codigoMoeda, modalidadePagamento);
        this.bolsas.add(bolsa);
        return bolsa;
    }

    @Override
    public List<Bolsa> getBolsasByAnoReferencia(int ano) {
        return bolsas.stream()
                .filter((Bolsa bolsa) -> bolsa.getAnoReferencia() == ano)
                .collect(Collectors.toList());
    }

    public List<Bolsa> get3MaioresBolsas() {
        if (this.bolsas.size() > 0) {
            double top1 = 0;
            double top2 = 0;
            double top3 = 0;
            List<Bolsa> top3Bolsas = new ArrayList<>();
            top3Bolsas.add(this.bolsas.get(0));
            top3Bolsas.add(this.bolsas.get(0));
            top3Bolsas.add(this.bolsas.get(0));

            for (Bolsa bolsa : this.bolsas) {
                if (bolsa.getValorBolsa() > top1) {
                    Bolsa temp = top3Bolsas.get(0);
                    top3Bolsas.set(2, top3Bolsas.get(1));
                    top3Bolsas.set(1, temp);
                    top3Bolsas.set(0, bolsa);
                    top1 = bolsa.getValorBolsa();
                    top2 = top3Bolsas.get(1).getValorBolsa();
                    top3 = top3Bolsas.get(2).getValorBolsa();
                    continue;
                }

                if (bolsa.getValorBolsa() > top2) {
                    Bolsa temp = top3Bolsas.get(1);
                    top3Bolsas.set(2, temp);
                    top3Bolsas.set(1, bolsa);
                    top2 = top3Bolsas.get(1).getValorBolsa();
                    top3 = top3Bolsas.get(2).getValorBolsa();
                    continue;
                }

                if (bolsa.getValorBolsa() > top3) {
                    top3Bolsas.set(2, bolsa);
                    top3 = top3Bolsas.get(2).getValorBolsa();
                }
            }
            return top3Bolsas;
        }
        return null;
    }

    public List<Bolsa> get3MenoresBolsas() {
        if (this.bolsas.size() > 0) {
            List<Bolsa> top3MenoresBolsas = new ArrayList<>();
            top3MenoresBolsas.add(this.bolsas.get(0));
            top3MenoresBolsas.add(this.bolsas.get(0));
            top3MenoresBolsas.add(this.bolsas.get(0));

            double top1 = top3MenoresBolsas.get(0).getValorBolsa();
            double top2 = top3MenoresBolsas.get(0).getValorBolsa();
            double top3 = top3MenoresBolsas.get(0).getValorBolsa();

            for (Bolsa bolsa : this.bolsas) {
                if (bolsa.getValorBolsa() <= top1) {
                    Bolsa temp = top3MenoresBolsas.get(0);
                    top3MenoresBolsas.set(2, top3MenoresBolsas.get(1));
                    top3MenoresBolsas.set(1, temp);
                    top3MenoresBolsas.set(0, bolsa);
                    top1 = bolsa.getValorBolsa();
                    top2 = top3MenoresBolsas.get(1).getValorBolsa();
                    top3 = top3MenoresBolsas.get(2).getValorBolsa();
                    continue;
                }

                if (bolsa.getValorBolsa() <= top2) {
                    Bolsa temp = top3MenoresBolsas.get(1);
                    top3MenoresBolsas.set(2, temp);
                    top3MenoresBolsas.set(1, bolsa);
                    top2 = top3MenoresBolsas.get(1).getValorBolsa();
                    top3 = top3MenoresBolsas.get(2).getValorBolsa();
                    continue;
                }

                if (bolsa.getValorBolsa() <= top3) {
                    top3MenoresBolsas.set(2, bolsa);
                    top3 = top3MenoresBolsas.get(2).getValorBolsa();
                }
            }
            return top3MenoresBolsas;
        }
        return null;
    }
}
