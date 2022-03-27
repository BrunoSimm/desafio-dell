package adaptadores.repositorios;

import negocio.entidades.Bolsista;
import negocio.repositorios.IRepositorioBolsistas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                .filter((Bolsista bolsista) -> {
                    return bolsista.getNome().contains(nome.toUpperCase());
                })
                .collect(Collectors.toList());
    }
}
