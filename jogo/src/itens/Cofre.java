package itens;

import itens.coletavel.Item;
import itens.coletavel.Ouro;
import java.util.ArrayList;
import java.util.List;

public class Cofre {
    private static final int QTD_MAXIMA_ITENS_VALOR = 999;
    public final List<Item> CONTEUDO;

    public Cofre(){
        this.CONTEUDO = new ArrayList<>(QTD_MAXIMA_ITENS_VALOR);
    }

    public List<Item> getPatrimonio(){
        return CONTEUDO;
    }

    public boolean depositarOuro(){
        if (this.CONTEUDO.size() < QTD_MAXIMA_ITENS_VALOR) {
            this.CONTEUDO.add(new Ouro());
            return true;
        }
        return false;
    }

    public boolean depositarItemMissao(Item itemMissao){
        if (this.CONTEUDO.size() < QTD_MAXIMA_ITENS_VALOR) {
            this.CONTEUDO.add(itemMissao);
            return true;
        }
        return false;
    }

    public int getQuantidadeOuro(){
        int quantidadeOuro = 0;
        for(Item item : CONTEUDO){
            if(item.getNome().equals(new Ouro().getNome())){
                quantidadeOuro += 1;
            }
        }
        return quantidadeOuro;
    }

    public boolean retirarOuro(){
        if (!this.CONTEUDO.isEmpty()) {
            this.CONTEUDO.remove(this.CONTEUDO.size() - 1);
            return true;
        }
        return false;
    }

    public boolean retirarItemMissao(Item itemMissao){
        for(Item item : CONTEUDO){
            if(item.getNome().equals(itemMissao.getNome())){
                CONTEUDO.remove(item);
                return true;
            }
        }
        return false;
    }

    public void printaConteudoCofre(){
        System.out.print("Conteúdo do cofre   : | ");
        for(Item moedaAtual : this.CONTEUDO){
            System.out.print(moedaAtual.getNome() + " | ");
        }
        System.out.println();
    }
}
