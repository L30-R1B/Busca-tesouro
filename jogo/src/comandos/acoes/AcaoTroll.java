package comandos.acoes;

import entidades.Jogador;
import entidades.Troll;
import itens.coletavel.Machado;
import itens.coletavel.Pocao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mapa.Mapa;
import mapa.Porta;
import mapa.Sala;

public class AcaoTroll {
    private final double PROBABILIDADE_MOVIMENTACAO = 0.5;

    public void tentarMovimentarTroll() {
        Mapa mapa = Mapa.getInstancia();

        Random rand = new Random();
        
        for (int salaAtual = 0; salaAtual < 20; salaAtual ++) {
            for(int trollAtual = 0; trollAtual < 3; trollAtual ++){
                if(mapa.getSala(salaAtual).verificaExistenciaTroll()){
                    if (rand.nextDouble() < PROBABILIDADE_MOVIMENTACAO) {
                        moverTroll(mapa.getSala(salaAtual));
                    }
                }
            }
        }
    }

    public boolean tentarPegarMachado(){
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        Machado machado = new Machado();

        if(sala.verificaExistenciaTroll() && sala.verificaExistenciaItem(machado.getNome())){
            sala.getTroll().seguraMachado();
            sala.removeItemSala(machado.getNome());
            return true;
        }
        return false;
    }

    public boolean tentarLancarMachado(){
        Jogador jogador = Jogador.getInstancia();
        Sala sala = Mapa.getInstancia().SALAS[Jogador.getInstancia().getSalaJogador()];
        List<Troll> trolls = sala.getTrolls();
        
        Pocao pocao = new Pocao();
        for(Troll troll : trolls){
            if(troll.getSegurandoMachado()){
                System.out.println("==== Troll segurando machado encontrado ====");
                if(jogador.getMochila().verificaExistenciaItem(pocao.getNome())){
                    jogador.getMochila().removerItem(pocao.getNome());
                    System.out.println("==== O jogador perdeu uma poção ====");
                    return false;
                }
                System.out.println("==== O jogador perdeu " + (int) troll.getMultiplicadorDano() * new Machado().getDanoBase() + " de vida ====");
                jogador.reduzirVida(new Machado().getDanoBase());
                return true;
            }
        }
        return false;
    }

    private void moverTroll(Sala salaAtual) {
        Mapa mapa = Mapa.getInstancia();
        Random rand = new Random();
        List<Porta> portas = new ArrayList<>();
        
        for (char identificador : new char[] {'A', 'B', 'C'}) {
            Porta porta = salaAtual.getPorta(identificador);
            if (porta != null && porta.isAberta()) { 
                portas.add(porta);
            }
        }
        
        if (!portas.isEmpty()) {
            Porta portaEscolhida = portas.get(rand.nextInt(portas.size()));
            Sala salaDestino = mapa.getSala(portaEscolhida.getSalaDestino());
            salaAtual.removeTrollSala();
            salaDestino.adicionaTrollSala();
        }
    }
}
