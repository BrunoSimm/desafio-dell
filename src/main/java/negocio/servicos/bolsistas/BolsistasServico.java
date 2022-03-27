package negocio.servicos.bolsistas;

import negocio.entidades.Bolsista;
import negocio.repositorios.IRepositorioBolsistas;

import java.text.Normalizer;
import java.util.*;

public class BolsistasServico {

    private final IRepositorioBolsistas iRepositorioBolsistas;

    public BolsistasServico(IRepositorioBolsistas iRepositorioBolsistas) {
        this.iRepositorioBolsistas = iRepositorioBolsistas;
    }

    public List<Bolsista> buscarBolsistaPeloNome(String nome) {
        List<Bolsista> bolsista = this.iRepositorioBolsistas.getBolsistasByNome(nome);

        if (bolsista.size() == 1) {
            bolsista.get(0).setNome(this.codificarNome(bolsista.get(0).getNome()));
        }
        return bolsista;
    }

    private String codificarNome(String nome) {
        //Regex para manter apenas letras e espaços no nome. Removendo caracteres especiais.
        nome = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^0-9A-Záéíóúàèìòùâêîôûãõç \\\\s]", "");
        char[] letras = nome.toCharArray();

        ArrayList<String> listaLetras = new ArrayList<String>();
        for (char letra : letras) {
            listaLetras.add(String.valueOf(letra));
        }

        //Passo 1: Inverter primeiro e ultimo character do nome.
        Collections.swap(listaLetras, 0, listaLetras.size() - 1 ); //https://www.delftstack.com/pt/howto/java/the-swap-method-in-java/

        //Passo 2: Realiza a troca de posição de elementos.
        // Troca-se o 1 elemento com o último, o 2 com o penúltimo, o 3 com o antepenúltimo e assim sucessivamente.
        // Caso o tamanho do nome for < 4, não executa esta parte, pois resultaria no nome original.
        if (nome.length() >= 4) {
            int inicioLista = 0;
            int finalLista = listaLetras.size() - 1;

            //Par
            if(nome.length() % 2 == 0){
                while (finalLista - inicioLista >= 1){
                    Collections.swap(listaLetras, inicioLista, finalLista);
                    inicioLista++;
                    finalLista--;
                }
            } else { //Impar
                while (finalLista - inicioLista > 2){
                    Collections.swap(listaLetras, inicioLista, finalLista);
                    inicioLista++;
                    finalLista--;
                }
            }

            //PASSO 3: Em cada letra deve-se "mover" a letra para o próximo character do alfabeto. Ex: (A -> B, Z -> A)
            for (int i = 0; i < listaLetras.size(); i++) {
                int ascii = (int) listaLetras.get(i).charAt(0); //Converte o character para seu respective número na tabela ASCII.

                //Se for a letra Z então converte para A.
                if(ascii == 90){
                    listaLetras.set(i, "A");
                } else if(ascii == 32){ //Character referente ao espaço, não modifica.

                } else {
                    ascii++; //Move uma unidade na tabela ASCII (ex: A -> B)
                    listaLetras.set(i, String.valueOf(((char) ascii))); //Converte o número da tabela ASCII para char e substitui no nome.
                }
            }
        }
        nome = String.join("", listaLetras); //Converte o array em uma string única.
        return nome;
    }
}
