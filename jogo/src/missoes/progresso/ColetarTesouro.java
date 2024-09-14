package missoes.progresso;

import entidades.Jogador;
import java.util.Random;
import mapa.Mapa;
import mapa.Sala;
import missoes.coletaveis.Tesouro;

public class ColetarTesouro extends Missao{
    private static final String OBJETIVO = "Encontrar tesouro perdido";
    private static final int RECOMPENSA_EXPERIENCIA = 200;
    private static final int RECOMPENSA_OURO = 25;
    private static final int NUMERO_TESOURO = 1;

    public ColetarTesouro(){
        super(NUMERO_TESOURO, OBJETIVO, RECOMPENSA_EXPERIENCIA, RECOMPENSA_OURO);
    }

    @Override
    public void adaptarMissaoMapa(){
        Random rand = new Random();
        Mapa mapa = Mapa.getInstancia();
        int indiceSala = rand.nextInt(mapa.getNumSalas());
        while(indiceSala == Jogador.getInstancia().getSalaJogador()){
            indiceSala = rand.nextInt(mapa.getNumSalas());
        }
        mapa.SALAS[indiceSala].adicionaItemSala(new Tesouro());
    }

    @Override
    public void limparMissaoMapa(){
        for(Sala salaAtual : Mapa.getInstancia().SALAS){
            if(salaAtual.removeItemSala(new Tesouro().getNome()))
                break;
        }
    }
}
