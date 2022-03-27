package adaptadores.drivers;

import com.opencsv.CSVReader;
import negocio.entidades.Bolsista;
import negocio.entidades.bolsa.Bolsa;
import negocio.entidades.bolsa.ModalidadePagamento;
import negocio.repositorios.IRepositorioBolsas;
import negocio.repositorios.IRepositorioBolsistas;

import java.io.FileReader;
import java.io.IOException;

/**
 * Classe utilizada como "driver" de acesso ao dados do arquivo CSV.
 * Caso a fonte de dados for alterada se faz necessário apenas a modificação da implementação desta classe, preservando o restante da aplicação.
 */
public class DataDriver {

    /**
     * Realiza a leitura do arquivo CSV e popula os repositórios de Bolsas e Bolsistas.
     *
     * @param iRepositorioBolsas    Repositorio contendo todas as Bolsas.
     * @param iRepositorioBolsistas Repositorio contendo todos os Bolsistas.
     */
    public void carregarDados(IRepositorioBolsas iRepositorioBolsas, IRepositorioBolsistas iRepositorioBolsistas) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/main/java/br-capes-bolsistas-uab.csv"), ';');
            String[] linha;
            // Para cada linha do arquivo, crie um novo bolsista e sua respectiva bolsa.
            while ((linha = csvReader.readNext()) != null) {
                //Ignora a primeira linha, que contem apenas cabeçalho.
                if (!(linha[0].contains("NM_BOLSISTA"))) {
                    Bolsa bolsa = this.getDadosLinha(linha);
                    Bolsista bolsista = iRepositorioBolsistas.salvarBolsista(bolsa.getBolsista());
                    bolsa = iRepositorioBolsas.criarBolsa(
                            bolsa.getBolsista(), bolsa.getMesReferencia(), bolsa.getAnoReferencia(),
                            bolsa.getValorBolsa(), bolsa.getCodigoMoeda(), bolsa.getModalidadePagamento()
                    );
                    bolsista.addBolsa(bolsa);

                }
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza a criação de um Bolsista e de sua respectiva Bolsa através dos dados de uma linha do arquivo CSV.
     *
     * @param linha Linha do arquivo sendo processado.
     * @return bolsa
     */
    private Bolsa getDadosLinha(String[] linha) {

        Bolsa bolsa = new Bolsa();
        Bolsista bolsista = new Bolsista();
        int contador = 0;

        while (contador <= 10) {
            switch (contador) {
                case 0 -> {
                    bolsista.setNome(linha[contador]);
                    contador++;
                }
                case 1 -> {
                    bolsista.setCpf(linha[contador]);
                    contador++;
                }
                case 2 -> {
                    bolsista.setEntidadeEnsino(linha[contador]);
                    contador++;
                }
                case 3 -> {
                    bolsa.setMesReferencia(Integer.parseInt(linha[contador]));
                    contador++;
                }
                case 4 -> {
                    bolsa.setAnoReferencia(Integer.parseInt(linha[contador]));
                    contador++;
                }
                case 5, 6, 7 -> contador++;
                case 8 -> {
                    bolsa.setModalidadePagamento(this.getModalidadePagamentoDeUmaString(linha[contador]));
                    contador++;
                }
                case 9 -> {
                    bolsa.setCodigoMoeda(linha[contador]);
                    contador++;
                }
                case 10 -> {
                    bolsa.setValorBolsa(Double.parseDouble(linha[contador]));
                    contador++;
                }
            }
        }
        bolsa.setBolsista(bolsista);
        return bolsa;
    }

    /**
     * Realiza a conversão da Modalidade de Pagamento em forma de String para seu respectivo ENUM.
     *
     * @param modalidade Modalidade de Pagamento
     * @return modalidade
     */
    private ModalidadePagamento getModalidadePagamentoDeUmaString(String modalidade) {
        switch (modalidade) {
            case "TUTOR":
                return ModalidadePagamento.TUTOR;
            case "COORDENADOR DE TUTORIA":
                return ModalidadePagamento.COORDENADOR_DE_TUTORIA;
            case "PROFESSOR FORMADOR I":
                return ModalidadePagamento.PROFESSOR_FORMADOR_I;
            case "PROFESSOR FORMADOR II":
                return ModalidadePagamento.PROFESSOR_FORMADOR_II;
            case "COORDENADORIA DE POLO":
                return ModalidadePagamento.COORDENADORIA_DE_POLO;
            case "COORDENADORIA DE CURSO I":
                return ModalidadePagamento.COORDENADORIA_DE_CURSO_I;
            case "COORDENADORIA DE CURSO II":
                return ModalidadePagamento.COORDENADORIA_DE_CURSO_II;
            case "COORDENADORIA DE TUTORIA I":
                return ModalidadePagamento.COORDENADORIA_DE_TUTORIA_I;
            case "COORDENADORIA DE TUTORIA II":
                return ModalidadePagamento.COORDENADORIA_DE_TUTORIA_II;
            case "COORDENADORIA DE GERAL":
                return ModalidadePagamento.COORDENADORIA_DE_GERAL;
            case "COORDENADORIA ADJUNTA":
                return ModalidadePagamento.COORDENADORIA_ADJUNTA;
            case "PROFESSOR CONTEUDISTA I":
                return ModalidadePagamento.PROFESSOR_CONTEUDISTA_I;
            case "PROFESSOR CONTEUDISTA II":
                return ModalidadePagamento.PROFESSOR_CONTEUDISTA_II;
            default:
                System.out.println("ERRO!!! MODALIDADE NÃO PREVISTA -> " + modalidade);
                return null;
        }
    }
}
