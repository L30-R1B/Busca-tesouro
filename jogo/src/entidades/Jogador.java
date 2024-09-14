package entidades;

import itens.Cofre;
import itens.Mochila;
import itens.coletavel.Chave;
import mapa.Mapa;
import missoes.coletaveis.Diamante;
import missoes.coletaveis.Esmeralda;
import missoes.coletaveis.Rubi;
import missoes.coletaveis.Tesouro;
import missoes.progresso.Missao;

public class Jogador extends Personagem {
    private static final String ASSINATURA_JOGADOR = "JOGADOR";
    private static Jogador instanciaUnica;

    private int sala;
    private final Mochila MOCHILA;
    public final Cofre COFRE;
    
    private Missao missaoAtual;
    private int salaMissao;

    private Jogador() {
        super(ASSINATURA_JOGADOR);
        this.MOCHILA = new Mochila();
        this.COFRE = new Cofre();
        this.sala = 15;
    }

    public static synchronized Jogador getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Jogador();
        }
        return instanciaUnica;
    }

    public Mochila getMochila(){
        return MOCHILA;
    }

    public Cofre getCofre(){
        return COFRE;
    }

    public Chave getChave() {
        return this.getMochila().getChave();
    }

    public void setSalaJogador(int sala) {
        this.sala = sala;
    }

    public int getSalaJogador() {
        return this.sala;
    }

    public Missao getMissaoAtual() {
        return missaoAtual;
    }

    public int getSalaMissao(){
        return this.salaMissao;
    }

    public boolean pegarMissao(Missao missao, int salaMissao) {
        if(missao == null)
            return false;

        if (this.missaoAtual == null) { 
            this.missaoAtual = missao;
            this.missaoAtual.adaptarMissaoMapa();
            this.salaMissao = salaMissao;
            return true;
        }
        return false;
    }

    private void removerItensMissaoCofre(){
        while(true){
            if(this.getCofre().retirarItemMissao(new Tesouro()))
                continue;
            if(this.getCofre().retirarItemMissao(new Diamante()))
                continue;
            if(this.getCofre().retirarItemMissao(new Esmeralda()))
                continue;
            if(this.getCofre().retirarItemMissao(new Rubi()))
                continue;
            
            break;
        }
    }

    public void cumprirMissao() {
        if (this.missaoAtual != null && this.missaoAtual.missaoCumprida()) {
            System.out.println("=====================================");
            System.out.println("          MISSÂO CUMPRIDA");
            System.out.println("=====================================");
            this.missaoAtual.imprimeInformacoesMissao();
            this.adicionarExperiencia(missaoAtual.getExperienciaRecebida());
            for(int i = 0; i < missaoAtual.getOuroRecebido(); i ++){
                this.COFRE.depositarOuro();
            }
            Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaMissao()].removeMissao();
            this.missaoAtual.limparMissaoMapa();
            this.removerItensMissaoCofre();
            this.missaoAtual = null;
        }else{
            System.out.println("=====================================");
            System.out.println("   NÂO FOI POSSIVEL CUMPRIR MISSÂO  ");
            System.out.println("=====================================");
            this.missaoAtual.imprimeInformacoesMissao();
        }
    }

    public void desistirMissao(){
        if(this.missaoAtual != null){
            System.out.println("=====================================");
            System.out.println("          MISSÂO ABANDONADA");
            System.out.println("=====================================");
            this.missaoAtual.imprimeInformacoesMissao();
            Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaMissao()].removeMissao();
            this.removerItensMissaoCofre();
            this.missaoAtual.limparMissaoMapa();
            this.missaoAtual = null;
        }else{
            System.out.println("=====================================");
            System.out.println("      SEM MISSÂO PARA ABANDONAR");
            System.out.println("=====================================");
        }
    }

    public void printaInfoJogador() {
        System.out.println("-----------------------------------------------");
        if(missaoAtual != null)
            missaoAtual.imprimeInformacoesMissao();
        else
            System.out.println("===== NENHUMA MISSÃO ESTÁ ATIVA =====\n");    
        System.out.println("**** JOGADOR ****");
        System.out.println("\tSala atual  : " + this.sala);
        System.out.println("\tNível       : " + this.getNivel());
        System.out.println("\tVida        : (" + this.getQuantidadeVida() + "/" + this.getVidaMaxima() + ")");
        System.out.println("\tExperiencia : (" + this.getExperienciaAcumulada() + "/" + this.getExperienciaProximoNivel() + ")");
        System.out.print("\t");
        this.MOCHILA.printaConteudoMochila();
        System.out.print("\t");
        this.COFRE.printaConteudoCofre();
        System.out.println("-----------------------------------------------");
    }
}
