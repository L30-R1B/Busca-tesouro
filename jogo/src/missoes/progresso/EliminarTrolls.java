package missoes.progresso;

import entidades.Jogador;
import itens.coletavel.Machado;
import java.util.Random;
import mapa.Mapa;

public class EliminarTrolls extends Missao{
    private static final String OBJETIVO = "Elimine trolls";
    private static final int RECOMPENSA_EXPERIENCIA = 300;
    private static final int RECOMPENSA_OURO = 2;
    private static final int NUMERO_TROLLS = 3;

    public EliminarTrolls(){
        super(NUMERO_TROLLS, OBJETIVO, RECOMPENSA_EXPERIENCIA, RECOMPENSA_OURO);
    }

    @Override
    public void adaptarMissaoMapa(){
        Mapa mapa = Mapa.getInstancia();
        Random rand = new Random();
        for(int i = 0; i < 5; i ++){
            int indiceSala = rand.nextInt(mapa.getNumSalas());
            while(indiceSala == Jogador.getInstancia().getSalaJogador()){
                indiceSala = rand.nextInt(mapa.getNumSalas());
            }
            mapa.SALAS[indiceSala].adicionaTrollSala();
        }
        for(int i = 0; i < 10; i ++){
            int indiceSala = rand.nextInt(mapa.getNumSalas());
            while(indiceSala == Jogador.getInstancia().getSalaJogador()){
                indiceSala = rand.nextInt(mapa.getNumSalas());
            }
            mapa.SALAS[indiceSala].adicionaItemSala(new Machado());
        }
    }
}
