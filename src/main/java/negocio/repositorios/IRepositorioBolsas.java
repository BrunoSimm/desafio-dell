package negocio.repositorios;

import negocio.entidades.Bolsista;
import negocio.entidades.bolsa.Bolsa;
import negocio.entidades.bolsa.ModalidadePagamento;

import java.util.List;

public interface IRepositorioBolsas {

    Bolsa criarBolsa(Bolsista bolsista, int mesReferencia, int anoReferencia, double valorBolsa,
                     String codigoMoeda, ModalidadePagamento modalidadePagamento);

    List<Bolsa> getBolsasByAnoReferencia(int ano);
    List<Bolsa> get3MaioresBolsas();
    List<Bolsa> get3MenoresBolsas();
}
