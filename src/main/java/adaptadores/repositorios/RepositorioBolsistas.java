package adaptadores.repositorios;

import negocio.entidades.Bolsista;
import negocio.repositorios.IRepositorioBolsistas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe que possui responsabilidade de fazer a leitura e resposta de acesso a dados referentes a Bolsistas.
 * Implementa a interface IRepositorioBolsistas definida na camada de negócio.
 * Camadas inferiores não dependem desta implementação e sim da interface.
 */
public class RepositorioBolsistas implements IRepositorioBolsistas {

    private final Set<Bolsista> bolsistas;

    public RepositorioBolsistas() {
        this.bolsistas = new HashSet<Bolsista>();
    }

    @Override
    public Bolsista salvarBolsista(Bolsista bolsista) {
        this.bolsistas.add(bolsista);
        return bolsista;
    }

    @Override
    public List<Bolsista> getBolsistasByNome(String nome) {
        return bolsistas.stream()
                .filter((Bolsista bolsista) -> bolsista.getNome().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }
}
