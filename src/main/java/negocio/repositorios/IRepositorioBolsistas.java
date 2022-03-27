package negocio.repositorios;

import negocio.entidades.Bolsista;

import java.util.List;

public interface IRepositorioBolsistas {

    Bolsista salvarBolsista(Bolsista bolsista);

    List<Bolsista> getBolsistasByNome(String nome);
}
