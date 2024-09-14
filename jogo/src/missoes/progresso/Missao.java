package missoes.progresso;

import itens.coletavel.Item;
import missoes.MissaoInterface;
import missoes.coletaveis.Diamante;
import missoes.coletaveis.Esmeralda;
import missoes.coletaveis.Rubi;
import missoes.coletaveis.Tesouro;

public class Missao implements MissaoInterface{
    private int progresso;
    private final int TAMANHO_TAREFA;
    private final String OBJETIVO;
    private final int EXPERIENCIA_RECEBIDA;
    private final int OURO_RECEBIDO;

    public Missao(int tamanhoTarefa, String objetivo, int experienciaRecebida, int ouroRecebido){
        this.progresso = 0;
        this.TAMANHO_TAREFA = tamanhoTarefa;
        this.OBJETIVO = objetivo;
        this.EXPERIENCIA_RECEBIDA = experienciaRecebida;
        this.OURO_RECEBIDO = ouroRecebido;
    }

    public String getObjetivo(){
        return this.OBJETIVO;
    }

    public int getTamanhoTarefa() {
        return this.TAMANHO_TAREFA;
    }

    public int getExperienciaRecebida(){
        return this.EXPERIENCIA_RECEBIDA;
    }

    public int getOuroRecebido(){
        return this.OURO_RECEBIDO;
    }

    public int getProgresso() {
        return this.progresso;
    }

    public boolean missaoCumprida(){
        return progresso >= TAMANHO_TAREFA;
    }

    public boolean getMissaoCumprida() {
        return progresso >= TAMANHO_TAREFA;
    }

    public void adicionaProgresso(){
        if (progresso < TAMANHO_TAREFA) {
            progresso += 1;
        }
    }

    public void imprimeInformacoesMissao() {
        System.out.println("=====================================");
        System.out.println("          INFORMAÇÕES DA MISSÃO          ");
        System.out.println("=====================================");
        System.out.println("Objetivo da Missão: " + OBJETIVO);
        System.out.println("Progresso: " + progresso + " / " + TAMANHO_TAREFA);
        System.out.println("Experiência ao Completar: " + EXPERIENCIA_RECEBIDA);
        System.out.println("Ouro ao Completar: " + OURO_RECEBIDO);
        if (missaoCumprida()) {
            System.out.println("Status: Missão cumprida!");
        } else {
            System.out.println("Status: Em progresso...");
        }
        System.out.println("=====================================");
    }

    public Item retornaItemMissaoCorrespondente(String itemNome) {
        if(itemNome.equals(new Tesouro().getNome())){
            return new Tesouro();
        }else if(itemNome.equals(new Diamante().getNome())){
            return new Diamante();
        }else if(itemNome.equals(new Esmeralda().getNome())){
            return new Esmeralda();
        }else if(itemNome.equals(new Rubi().getNome())){
            return new Rubi();
        }
        
        return null;
    }    

    @Override
    public void adaptarMissaoMapa(){
    }

    @Override    
    public void limparMissaoMapa(){
    }
}
