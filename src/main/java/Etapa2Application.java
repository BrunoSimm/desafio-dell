import negocio.entidades.Bolsista;
import negocio.entidades.bolsa.Bolsa;
import negocio.entidades.bolsa.ModalidadePagamento;

public class Etapa2Application {
    public static void main(String[] args){
        System.out.println("MAIN");

        Bolsista b = new Bolsista("bruno", "0404102312","PUCRS");
        Bolsa bolsa = new Bolsa(1,b,1,2022,1300,"R$", ModalidadePagamento.TUTOR);

        System.out.printf("Bolsista => %s | bolsa id %d", bolsa.getBolsista().getNome(), bolsa.getId());

    }
}
