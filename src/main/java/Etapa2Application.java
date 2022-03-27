import adaptadores.console.Console;
import adaptadores.drivers.DataDriver;
import adaptadores.repositorios.RepositorioBolsas;
import adaptadores.repositorios.RepositorioBolsistas;
import negocio.repositorios.IRepositorioBolsas;
import negocio.repositorios.IRepositorioBolsistas;
import negocio.servicos.bolsas.BolsasServico;
import negocio.servicos.bolsistas.BolsistasServico;

public class Etapa2Application {
    public static void main(String[] args) {
        IRepositorioBolsas repositorioBolsas = new RepositorioBolsas();
        IRepositorioBolsistas repositorioBolsistas = new RepositorioBolsistas();
        BolsistasServico bolsistasServico = new BolsistasServico(repositorioBolsistas);
        BolsasServico bolsasServico = new BolsasServico(repositorioBolsas);

        DataDriver dataDriver = new DataDriver();
        //Lê arquivo CSV e insere as linhas nos respectivos repositórios.
        dataDriver.carregarDados(repositorioBolsas, repositorioBolsistas);

        Console console = new Console(bolsasServico, bolsistasServico);
        console.start();
    }
}
