package itens;

import itens.coletavel.Chave;
import itens.coletavel.Item;
import java.util.ArrayList;
import java.util.List;

public class Mochila {
    private static final int QTD_MAXIMA_ITENS = 5;
    private final List<Item> ARMAZENAMENTO;

    public Mochila() {
        this.ARMAZENAMENTO = new ArrayList<>(QTD_MAXIMA_ITENS);
    }

    public Chave getChave() {
        for (Item item : ARMAZENAMENTO) {
            if (item instanceof Chave) { 
                return (Chave) item; 
            }
        }
        return null;
    }

    public boolean adicionarItem(Item item) {
        if (this.ARMAZENAMENTO.size() < QTD_MAXIMA_ITENS) {
            return this.ARMAZENAMENTO.add(item);
        }
        return false;
    }

    public boolean removerItem(String nome) {
        for (Item item : ARMAZENAMENTO) {
            if (item.getNome().equals(nome)) {
                ARMAZENAMENTO.remove(item);
                return true;
            }
        }
        return false;
    }

    public boolean verificaExistenciaItem(String nomeItem) {
        for (Item item : ARMAZENAMENTO) {
            if (item.getNome().equals(nomeItem)) {
                return true;
            }
        }
        return false;
    }

    public void printaConteudoMochila() {
        System.out.print("Conteúdo da mochila : | ");
        for (Item itemAtual : this.ARMAZENAMENTO) {
            System.out.print(itemAtual.getNome() + " | ");
        }
        System.out.println();
    }
    
    
}
