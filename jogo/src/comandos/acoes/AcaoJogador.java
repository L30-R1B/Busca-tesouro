package comandos.acoes;

import entidades.Jogador;
import entidades.Troll;
import itens.coletavel.Chave;
import itens.coletavel.Item;
import itens.coletavel.Machado;
import itens.coletavel.Ouro;
import itens.coletavel.Pocao;
import mapa.Mapa;
import mapa.Porta;
import mapa.Sala;
import missoes.progresso.EliminarTrolls;
import missoes.progresso.Missao;

public class AcaoJogador {

    private String objetoProximidade;

    public AcaoJogador() {
        this.objetoProximidade = "NULL";
    }

    public boolean verSala() {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        sala.printaInfoSala();
        return true;
    }

    public boolean moverParaItem(String itemNome) {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        if (sala.verificaExistenciaItem(itemNome)) {
            this.objetoProximidade = itemNome;
            System.out.println("==== Jogador se movimenta até " + itemNome + " ====");
            return true;
        }
        System.out.println("==== Impossivel se movimentar até " + itemNome + " ====");
        return false;
    }

    public boolean moveParaPorta(char identificadorPorta) {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        if (sala.verificaExistenciaPorta(identificadorPorta)) {
            objetoProximidade = Character.toString(identificadorPorta);
            System.out.println("==== Jogador vai até a porta " + identificadorPorta + " ====");
            return true;
        }
        System.out.println("==== Impossivel ir até a porta " + identificadorPorta + " ====");
        return false;
    }

    public boolean atravessarPorta(char identificadorPorta) {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];

        if (sala.verificaExistenciaPorta(identificadorPorta)){
            if(!objetoProximidade.equals(Character.toString(identificadorPorta))){
                System.out.println("==== Porta " + identificadorPorta + " distante do jogador ====");
                return false;
            }
            Porta porta = sala.getPorta(identificadorPorta);
            if (!porta.isAberta()) {
                System.out.println("==== Porta " + identificadorPorta + " fechada ====");
                return false;
            }
            Jogador.getInstancia().setSalaJogador(porta.getSalaDestino());
            System.out.println("==== Porta " + identificadorPorta + " atravessada com sucesso ====");
            return true;
        }
        System.out.println("==== Porta " + identificadorPorta + "inexistente na sala ====");
        return false;
    }

    public boolean pegarOuro() {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        Ouro ouro = new Ouro();
        
        if (sala.verificaExistenciaItem(ouro.getNome())){
            if(!objetoProximidade.equals(ouro.getNome())){
                System.out.println("==== Ouro distante do jogador ====");
                return false;
            }
            if(sala.verificaExistenciaTroll()){
                System.out.println("==== Troll na sala está protejendo o ouro ====");
                return false;
            }
            sala.removeItemSala(ouro.getNome());
            
            Jogador.getInstancia().getCofre().depositarOuro();

            objetoProximidade = "NULL";
            System.out.println("==== Ouro coletado com sucesso ====");
            return true;
        }
        System.out.println("==== Não existe ouro na sala ====");

        return false;
    }

    public boolean pegarItemMissao(Item itemMissao){
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        Jogador jogador = Jogador.getInstancia();
        if (sala.verificaExistenciaItem(itemMissao.getNome())){
            if(!objetoProximidade.equals(itemMissao.getNome())){
                System.out.println("==== Item distante do jogador ====");
                return false;
            }
            jogador.getCofre().depositarItemMissao(itemMissao);
            jogador.getMissaoAtual().adicionaProgresso();
            sala.removeItemSala(itemMissao.getNome());
            System.out.println("==== Item da missão \"" + jogador.getMissaoAtual().getObjetivo() + "\" coletado");
            return true;
        }
        System.out.println("==== Não foi possível coletar o item de missão ====");
        return false;
    }

    public boolean pegarItem(String nomeItem) {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        Jogador jogador = Jogador.getInstancia();
    
        if(sala.verificaExistenciaItem(nomeItem)) {
            if(!objetoProximidade.equals(nomeItem)){
                System.out.println("==== Item distante do jogador ====");
                return false;
            }
            Item item = new Item(nomeItem);
            if (jogador.getMochila().adicionarItem(item)) {
                sala.removeItemSala(nomeItem);
                objetoProximidade = "NULL";
                System.out.println("==== " + nomeItem + " coletado ====");
                return true;
            }else{
                System.out.println("==== Mochila lotada ====");
                return false;
            }
        }
        System.out.println("==== Não existe " + nomeItem + " na sala ====");
        return false;
    }

    public boolean largarOuro() {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        if (Jogador.getInstancia().getCofre().retirarOuro()) {
            sala.adicionaItemSala(new Ouro());
            System.out.println("==== Ouro deixado pelo jogador na sala ====");
            return true;
        }
        System.out.println("==== Jogador não possui ouro ====");
        return false;
    }

    public boolean largarItemMissao(Item itemMissao){
        Jogador jogador = Jogador.getInstancia();
        if(jogador.getCofre().retirarItemMissao(itemMissao)){
            Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
            sala.adicionaItemSala(itemMissao);
            System.out.println("==== " + itemMissao.getNome() + " deixado pelo jogador na sala ====");
            return true;
        }
        System.out.println("==== Jogador não possuí aaaa" + itemMissao.getNome() + " ====");
        return false;
    }

    public boolean largarItem(String nomeItem) {
        System.out.println("NOME " + nomeItem);
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        if (Jogador.getInstancia().getMochila().removerItem(nomeItem)) {
            sala.adicionaItemSala(new Item(nomeItem));
            System.out.println("==== " + nomeItem + " deixado pelo jogador na sala ====");
            return true;
        }
        System.out.println("==== Jogador não possuí " + nomeItem + " ====");
        return false;
    }

    public boolean arremessarMachado() {
        Machado machado = new Machado();
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        Jogador jogador = Jogador.getInstancia();
        
        if (sala.verificaExistenciaTroll()) {
            if (jogador.getMochila().removerItem(machado.getNome())){
                Troll troll = sala.getTroll();
                int danoCausado = (int)jogador.getMultiplicadorDano() * new Machado().getDanoBase(); 
                troll.reduzirVida(danoCausado);
                System.out.println("==== Jogador lançou machado causando " + (danoCausado) + " de dano em um troll ====");
                System.out.println("==== Machado quebra ====");
                if(!troll.isVivo()){
                    System.out.println("==== Jogador matou um troll ====");
                    jogador.adicionarExperiencia(troll.getExperienciaProximoNivel()/2);
                    Missao missaoAtual = jogador.getMissaoAtual();
                    if(missaoAtual != null){
                        if(missaoAtual instanceof EliminarTrolls){
                            System.out.println("==== Jogador progrediu na missão \"" + missaoAtual.getObjetivo() + "\" ====");
                            missaoAtual.adicionaProgresso();
                        }
                    }
                    sala.removeTrollSala();
                }
                return true;
            }else{
                System.out.println("==== Jogador não possuí machado ====");
            }
        }else{
            System.out.println("==== Não existem trolls na sala ====");
        }
        return false;
    }

    public boolean fecharPorta(char identificadorPorta) {
        Jogador jogador = Jogador.getInstancia();
        if (!Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()].verificaExistenciaPorta(identificadorPorta)){
            System.out.println("==== Porta " + identificadorPorta + " inexistente na sala ====");
        }
        if (objetoProximidade.equals(Character.toString(identificadorPorta))) {
            Pocao pocao = new Pocao();
            if (jogador.getMochila().verificaExistenciaItem(pocao.getNome())) {
                jogador.getMochila().removerItem(pocao.getNome());
                Mapa mapa = Mapa.getInstancia();
                int salaUm = jogador.getSalaJogador();
                int salaDois = mapa.SALAS[salaUm].getPorta(identificadorPorta).getSalaDestino();
                mapa.fecharPortaBidirecional(salaUm, identificadorPorta, salaDois);
                System.out.println("==== Pocao consumida ====");
                System.out.println("==== Porta " + identificadorPorta + " fechada ====");
                return true;
            }else{
                System.out.println("==== Jogador não possuí pocao para fechar porta ====");
            }
        }else{
            System.out.println("==== Porta " + identificadorPorta + " longe do jogador ====");
        }
        return false;
    }

    public boolean abrirPorta(char identificadorPorta) {
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        Jogador jogador = Jogador.getInstancia();

        if (sala.verificaExistenciaPorta(identificadorPorta)) {
            if(!objetoProximidade.equals(Character.toString(identificadorPorta))){
                System.out.println("==== Porta " + identificadorPorta + " longe do jogador ====");
                return false;
            }
            Chave chave = new Chave(identificadorPorta);
            if (jogador.getMochila().verificaExistenciaItem(chave.getNome())) {
                Mapa mapa = Mapa.getInstancia();
                int salaUm = jogador.getSalaJogador();
                int salaDois = mapa.SALAS[salaUm].getPorta(identificadorPorta).getSalaDestino();
                mapa.abrirPortaBidirecional(salaUm, identificadorPorta, salaDois);
                System.out.println("==== Chave consumida ====");
                System.out.println("==== Porta " + identificadorPorta + " aberta ====");
                sala.getPorta(identificadorPorta).abrePorta();
                jogador.getMochila().removerItem(chave.getNome());
                return true;
            }else{
                System.out.println("==== Jogador não possuí chave correspondente ====");
            }
        }else{
            System.out.println("==== Porta " + identificadorPorta + " inexistente na sala ====");
        }
        return false;
    }
}
