package negocio.repositorios;

import negocio.entidades.Bolsista;

public interface IRepositorioBolsistas {

    Bolsista createBolsista(String nome, String cpf, String entidadeEnsino);
    Bolsista getBolsistaByNome(String nome);
}
