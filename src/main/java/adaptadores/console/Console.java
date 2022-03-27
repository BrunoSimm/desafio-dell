package adaptadores.console;

import aplicacao.casosDeUso.bolsas.ConsultarBolsaZeroUC;
import aplicacao.casosDeUso.bolsas.ConsultarMediaAnualUC;
import aplicacao.casosDeUso.bolsas.ConsultarRankingMaioresValoresDeBolsaUC;
import aplicacao.casosDeUso.bolsistas.BuscarBolsistaPeloNomeUC;
import aplicacao.casosDeUso.bolsas.ConsultarRankingMenoresValoresDeBolsaUC;
import negocio.entidades.Bolsista;
import negocio.entidades.bolsa.Bolsa;
import negocio.servicos.bolsas.BolsasServico;
import negocio.servicos.bolsistas.BolsistasServico;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Console {
    String resetFonte = "\u001B[0m";
    String fonteAmarela = "\u001B[33m";
    String fonteVerde = "\u001B[42m";
    String fonteVermelha = "\u001B[41m";
    String fonteAzul = "\u001B[46m";

    private final Scanner opcaoInput = new Scanner(System.in);
    private final BolsasServico bolsasServico;
    private final BolsistasServico bolsistasServico;

    public Console(BolsasServico bolsasServico, BolsistasServico bolsistasServico) {
        this.bolsasServico = bolsasServico;
        this.bolsistasServico = bolsistasServico;
    }

    public void start() {
        System.out.println(this.fonteAzul + "CAPES - Indicadores de Bolsas, Auxílios" +
                " e Projetos da DED/CAPES 2013 a 2016," +
                "Bolsistas da Universidade Aberta do Brasil."+ this.resetFonte+"\n");
        int opcao = 0;
        while (opcao != 5) {
            System.out.println(this.fonteAzul + "ESCOLHA UMA DAS SEGUINTES OPÇÕES DE CONSULTA:" + this.resetFonte);
            System.out.println("\t1 -> Consultar Bolsa Zero");
            System.out.println("\t2 -> Consultar Bolsista por Nome");
            System.out.println("\t3 -> Consultar Médial Anual dos Valores das Bolsas");
            System.out.println("\t4 -> Consultar Ranking de Valores de Bolsa");
            System.out.println("\t5 -> Terminar Programa");

            try {
                opcao = this.opcaoInput.nextInt();
                if (opcao < 1 || opcao > 5) {
                    System.out.println(this.fonteVermelha + "Opção inválida, tente novamente." + this.resetFonte);
                } else {
                    this.executarOpcao(opcao);
                }
            } catch (InputMismatchException e) {
                System.out.println(this.fonteVermelha + "Opção inválida, tente novamente." + this.resetFonte+"\n");
                opcao = 0;
                this.opcaoInput.nextLine();

            }
        }
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> this.consultarBolsaZero();
            case 2 -> this.consultarBolsistaPorNome();
            case 3 -> this.consultarMediaAnual();
            case 4 -> this.consultarRankingValoresDeBolsa();
            case 5 -> {
                System.out.println("Encerrando programa...");
                System.out.println("Obrigado!");
            }
        }
    }

    private void consultarBolsaZero() {
        try {
            System.out.print("DIGITE UM ANO PARA BUSCAR: ");
            int ano = this.opcaoInput.nextInt();
            ConsultarBolsaZeroUC consultarBolsaZeroUC = new ConsultarBolsaZeroUC(bolsasServico, ano);
            Optional<Bolsa> bolsaZero = consultarBolsaZeroUC.run();

            if (bolsaZero != null && bolsaZero.isPresent()) {
                Bolsa bolsa = bolsaZero.get();
                Bolsista bolsista = bolsa.getBolsista();

                System.out.printf("\n" + fonteVerde + "BOLSISTA ZERO DE ANO %d:" + resetFonte + "\n", ano);
                System.out.printf("\tNome: %s\n\tCPF: %s \n\tEntidade de Ensino: %s\n\tValor da Bolsa: %s %.2f\n\n",
                        bolsista.getNome(), bolsista.getCpf(), bolsista.getEntidadeEnsino(),
                        bolsa.getCodigoMoeda(), bolsa.getValorBolsa()
                );
            } else {
                System.out.println(fonteAmarela + "Nenhuma bolsa encontrada neste ano!" + resetFonte+"\n");
            }
        } catch (InputMismatchException e) {
            this.opcaoInput.nextLine();
            System.out.println("\n"+this.fonteVermelha + "Ano inválido. Digite um número inteiro." + this.resetFonte);
        }
    }

    private void consultarBolsistaPorNome() {
        try {
            this.opcaoInput.nextLine();
            System.out.print("Digite o nome de um bolsista: ");
            String nome = this.opcaoInput.nextLine();

            BuscarBolsistaPeloNomeUC buscarBolsistaPeloNomeUC = new BuscarBolsistaPeloNomeUC(bolsistasServico);
            List<Bolsista> bolsista = buscarBolsistaPeloNomeUC.run(nome);

            //Encontrou mais de um bolsista com o nome inserido.
            if (bolsista.size() > 1) {
                String novoNome = null;
                do {
                    System.out.println(this.fonteAmarela + "Existem mais de um bolsista com o nome informado!\n" + this.resetFonte + "Por favor, informe um nome com mais detalhes ou 'SAIR' para voltar:");
                    novoNome = opcaoInput.nextLine();
                    if (!novoNome.equalsIgnoreCase("SAIR")) {
                        bolsista = buscarBolsistaPeloNomeUC.run(novoNome);
                    }
                } while (!novoNome.equalsIgnoreCase("SAIR") && bolsista.size() > 1);
            }

            //Não encontrou nenhum bolsista com o nome inserido.
            if (bolsista.size() == 0) {
                System.out.println(this.fonteAmarela + "Nenhum bolsista encontrado com este nome!" + this.resetFonte+"\n");
            }

            //Encontrou um bolsista com o nome inserido. Neste caso, nome do bolsista virá codificado.
            if (bolsista.size() == 1) {
                Bolsa bolsa = bolsista.get(0).getBolsaMaisRecente();
                System.out.println(this.fonteVerde + "BOLSISTA ENCONTRADO(A):" + this.resetFonte);
                System.out.printf("\tNome: %s\n\tAno: %d\n\tEntidade de Ensino: %s\n\tValor da Bolsa: %s %.2f\n\n",
                        bolsista.get(0).getNome(), bolsa.getAnoReferencia(), bolsista.get(0).getEntidadeEnsino(),
                        bolsa.getCodigoMoeda(), bolsa.getValorBolsa()
                );
            }
        } catch (InputMismatchException e) {
            this.opcaoInput.nextLine();
            System.out.println(this.fonteVermelha + "Nome inválido, digite um nome contendo letras." + this.resetFonte);
        }
    }

    private void consultarMediaAnual() {
        try {
            System.out.print("DIGITE UM ANO PARA BUSCAR: ");
            int ano = this.opcaoInput.nextInt();

            ConsultarMediaAnualUC consultarMediaAnualUC = new ConsultarMediaAnualUC(bolsasServico);
            double media = consultarMediaAnualUC.run(ano);

            //Encontrou um bolsista com o nome inserido. Neste caso, nome do bolsista virá codificado.
            if (media > 0.0) {
                System.out.printf("\n" + this.fonteVerde + "MEDIA ANUAL DAS BOLSAS DE %d: R$ %.2f"+ this.resetFonte+ "\n\n", ano, media );
            } else {
                System.out.println("\n" + this.fonteAmarela + "NÃO FORAM ENCONTRADAS BOLSAS NESTE ANO!" + this.resetFonte+"\n");
            }
        } catch (InputMismatchException e) {
            this.opcaoInput.nextLine();
            System.out.println("\n" + this.fonteVermelha + "Ano inválido. Digite um número inteiro." + this.resetFonte);
        }
    }

    private void consultarRankingValoresDeBolsa() {
        ConsultarRankingMaioresValoresDeBolsaUC consultarRankingMaioresValoresDeBolsaUC = new ConsultarRankingMaioresValoresDeBolsaUC(bolsasServico);
        List<Bolsa> top3MaioresBolsas = consultarRankingMaioresValoresDeBolsaUC.run();

        ConsultarRankingMenoresValoresDeBolsaUC consultarRankingMenoresValoresDeBolsaUC = new ConsultarRankingMenoresValoresDeBolsaUC(bolsasServico);
        List<Bolsa> top3MenoresBolsas = consultarRankingMenoresValoresDeBolsaUC.run();

        if(top3MenoresBolsas == null || top3MenoresBolsas == null){
            System.out.println(this.fonteVermelha + "Não existe bolsas cadastradas no sistema." + this.resetFonte);
        } else {
            System.out.println(this.fonteVerde + "TRÊS ALUNOS COM AS MAIORES BOLSAS:" +this.resetFonte);
            int contador = 1;
            for (Bolsa bolsa : top3MaioresBolsas) {
                System.out.printf("\t%d = Nome: %s | Ano: %d | Entidade de Ensino: %s |"+this.fonteAmarela+" Valor da Bolsa: %s %.2f\n"+this.resetFonte,
                        contador, bolsa.getBolsista().getNome(), bolsa.getAnoReferencia(), bolsa.getBolsista().getEntidadeEnsino(),
                        bolsa.getCodigoMoeda(), bolsa.getValorBolsa()
                );
                contador++;
            }

            System.out.println("\n"+this.fonteVerde + "TRÊS ALUNOS COM AS MENORES BOLSAS:" +this.resetFonte);
            contador = 1;
            for (Bolsa bolsa : top3MenoresBolsas) {
                System.out.printf("\t%d = Nome: %s | Ano: %d | Entidade de Ensino: %s |"+this.fonteAmarela+" Valor da Bolsa: %s %.2f\n"+this.resetFonte,
                        contador, bolsa.getBolsista().getNome(), bolsa.getAnoReferencia(), bolsa.getBolsista().getEntidadeEnsino(),
                        bolsa.getCodigoMoeda(), bolsa.getValorBolsa()
                );
                contador++;
            }
            System.out.println();
        }


    }

}
