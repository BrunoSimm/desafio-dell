import adaptadores.console.Console;
import adaptadores.drivers.DataDriver;
import adaptadores.repositorios.RepositorioBolsas;
import adaptadores.repositorios.RepositorioBolsistas;
import negocio.repositorios.IRepositorioBolsas;
import negocio.repositorios.IRepositorioBolsistas;
import negocio.servicos.bolsas.BolsasServico;
import negocio.servicos.bolsistas.BolsistasServico;

/**
 * Entry point da aplicação. Inicia repositórios vazios (sem dados) e serviços que serão utilizadas por toda a aplicação.
 * Apenas uma instância dos repositórios e dos serviços será utilizada por toda a aplicação. Estas dependencies são injetadas (DI) onde necessário.
 */
public class Etapa2Application {
    public static void main(String[] args) {

        //Iniciando repositórios e serviços que serão utilizados por toda a aplicação.
        //Estas instancias são injetadas onde se faz necessário.
        IRepositorioBolsas repositorioBolsas = new RepositorioBolsas();
        IRepositorioBolsistas repositorioBolsistas = new RepositorioBolsistas();
        BolsistasServico bolsistasServico = new BolsistasServico(repositorioBolsistas);
        BolsasServico bolsasServico = new BolsasServico(repositorioBolsas);

        //Lê arquivo CSV e insere as linhas nos respectivos repositórios.
        DataDriver dataDriver = new DataDriver();
        dataDriver.carregarDados(repositorioBolsas, repositorioBolsistas);

        //Inicia console para interação com usuário.
        Console console = new Console(bolsasServico, bolsistasServico);
        console.start();
    }
}
